package com.oauth_test.back.service.Implement;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.oauth_test.back.common.CertificationNumber;
import com.oauth_test.back.dto.request.auth.CheckCertificationRequestDto;
import com.oauth_test.back.dto.request.auth.EmailCertificationRequestDto;
import com.oauth_test.back.dto.request.auth.IdCheckRequestDto;
import com.oauth_test.back.dto.request.auth.SignUpRequestDto;
import com.oauth_test.back.dto.response.ResponseDto;
import com.oauth_test.back.dto.response.auth.CheckCertificationResponseDto;
import com.oauth_test.back.dto.response.auth.EmailCertificationResponseDto;
import com.oauth_test.back.dto.response.auth.IdCheckResponseDto;
import com.oauth_test.back.dto.response.auth.SignUpResponseDto;
import com.oauth_test.back.entity.CertificationEntity;
import com.oauth_test.back.entity.UserEntity;
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

    private PasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();

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

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {

        try {

            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);

            if (certificationEntity != null) {
                return CheckCertificationResponseDto.certificationFail();
            }

            boolean isMatched = certificationEntity.getEmail().equals(email)
                    && certificationEntity.getCertificationNumber().equals(certificationNumber);

            if (!isMatched)
                return CheckCertificationResponseDto.certificationFail();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {

            String userId = dto.getId();
            boolean isExistId = userRepository.existsById(userId);
            if (isExistId)
                return SignUpResponseDto.duplicateId();

            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            boolean isMatched = certificationEntity.getEmail().equals(email)
                    && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched)
                return SignUpResponseDto.certificationFail();

            String password = dto.getPassword();
            String encodedPassword = PasswordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            certificationRepository.deleteByUserId(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

}
