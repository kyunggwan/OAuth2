package com.oauth_test.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth_test.back.entity.UserEntity;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> { // 엔티티와, PK의 타입 명시

    UserEntity findByUserId(String userId);

}
