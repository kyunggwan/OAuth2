package com.oauth_test.back.service;

import org.springframework.http.ResponseEntity;

import com.oauth_test.back.dto.request.auth.CheckCertificationRequestDto;
import com.oauth_test.back.dto.response.auth.CheckCertificationResponseDto;
import com.oauth_test.back.dto.request.auth.EmailCertificationRequestDto;
import com.oauth_test.back.dto.response.auth.EmailCertificationResponseDto;
import com.oauth_test.back.dto.request.auth.IdCheckRequestDto;
import com.oauth_test.back.dto.response.auth.IdCheckResponseDto;
import com.oauth_test.back.dto.request.auth.SignUpRequestDto;
import com.oauth_test.back.dto.response.auth.SignUpResponseDto;

public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);

    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
}
