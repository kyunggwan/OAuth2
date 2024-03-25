package com.oauth_test.back.service;

import org.springframework.http.ResponseEntity;

import com.oauth_test.back.dto.request.auth.EmailCertificationRequestDto;
import com.oauth_test.back.dto.response.auth.EmailCertificationResponseDto;
import com.oauth_test.back.dto.request.auth.IdCheckRequestDto;
import com.oauth_test.back.dto.response.auth.IdCheckResponseDto;

public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
}
