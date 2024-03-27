package com.oauth_test.back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.oauth_test.back.dto.request.auth.CheckCertificationRequestDto;
import com.oauth_test.back.dto.response.auth.CheckCertificationResponseDto;
import com.oauth_test.back.dto.request.auth.EmailCertificationRequestDto;
import com.oauth_test.back.dto.response.auth.EmailCertificationResponseDto;
import com.oauth_test.back.dto.request.auth.IdCheckRequestDto;
import com.oauth_test.back.dto.response.auth.IdCheckResponseDto;
import com.oauth_test.back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck(
            @RequestBody @Valid IdCheckRequestDto requestBody) {

        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertificatioEntity(
            @RequestBody @Valid EmailCertificationRequestDto requestBody) {
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertificatioEntity(
            @RequestBody @Valid CheckCertificationRequestDto requestBody) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

}
