package com.oauth_test.back.filter;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.oauth_test.back.entity.UserEntity;
import com.oauth_test.back.provider.JwtProvider;
import com.oauth_test.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository UserRepository;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);
            if (token == null) { // Header가 Authorization이 없거나, Bearer가 아닌 경우
                filterChain.doFilter(request, response);
                return; // 다음 필터로 넘김
            }

            String userId = jwtProvider.validate(token);

            if (userId == null) { // signKey가 안맞거나, expired됐을 때,
                filterChain.doFilter(request, response);
                return; // 다음 필터로 넘김
            }

            UserEntity userEntity = UserRepository.findByUserId(userId);
            String role = userEntity.getRole(); // role : ROLE_USER, ROLE_ADMIN

            // ROLE_DEVELPOPER, ROLE_BOSS
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));

            // 빈 Context 생성
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

            // Context에 담을 Token 생성(접근 주체 정보)
            AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null,
                    authorities);

            // 웹 인증 세부 설정, 디테일한 값을 넣겠다.
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Context에 토큰을 담음
            securityContext.setAuthentication(authenticationToken);

            // 만든 Context를 외부에서 사용하도록 설정(등록)
            SecurityContextHolder.setContext(securityContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response); // 다음 필터 실행

    }

    private String parseBearerToken(HttpServletRequest request) {
        // Header에서 정보를 가져옴
        String authorization = request.getHeader("Authorization");

        boolean hasAuthorization = StringUtils.hasText(authorization); // hasText는 null이거나 공백, 길이가 0이면 false를 반환
        if (!hasAuthorization)
            return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer)
            return null;

        String token = authorization.substring(7);
        return token;

    }

}