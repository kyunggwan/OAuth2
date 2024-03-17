package com.oauth_test.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth_test.back.entity.CertificationEntity;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String> {

}
