package com.oauth_test.back.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailCertificationRequestDto {

    @NotBlank
    private String id;

    @Email
    @NotBlank
    private String email;
}
