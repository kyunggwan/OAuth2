package com.oauth_test.back.entity;

import com.oauth_test.back.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user") // 지금 이 클래스를 Entity 클래스로 사용함, user로 명명함
@Table(name = "user") // DB의 어떤 테이블과 연결할까
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String password;
    private String email;
    private String type;
    private String role;

    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.type = "app";
        this.role = "ROLE_USER";

    }
}
