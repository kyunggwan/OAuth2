package com.oauth_test.back.service.Implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oauth_test.back.common.CertificationNumber;
import com.oauth_test.back.dto.request.auth.EmailCertificationRequestDto;
import com.oauth_test.back.dto.request.auth.IdCheckRequestDto;
import com.oauth_test.back.dto.response.ResponseDto;
import com.oauth_test.back.dto.response.auth.EmailCertificationResponseDto;
import com.oauth_test.back.dto.response.auth.IdCheckResponseDto;
import com.oauth_test.back.entity.CertificationEntity;
import com.oauth_test.back.provider.EmailProvider;
import com.oauth_test.back.repository.CertificationRepository;
import com.oauth_test.back.repository.UserRepository;
import com.oauth_test.back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplements implements AuthService {

    private final UserRepository userRepository;

    private final CertificationRepository certificationRepository;

    private final EmailProvider emailProvider;

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {

        try {
            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);

            if (isExistId)
                return IdCheckResponseDto.duplicateId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {

        try {

            String userId = dto.getId();
            String email = dto.getEmail();

            // userId 확인
            boolean isExistId = userRepository.existsById(userId);
            if (isExistId)
                return EmailCertificationResponseDto.duplicateId();

            // Number 생성
            String certificationNumber = CertificationNumber.getCertificationNumber();

            // 메일 전송
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed)
                return EmailCertificationResponseDto.mailSendFail();

            // 메일 전송 결과 저장
            CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

}
