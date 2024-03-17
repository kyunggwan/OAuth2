-- Active: 1710672745723@@database-1.cncsoy0ikk5m.ap-northeast-2.rds.amazonaws.com@3306@oauth
CREATE TABLE user(
    user_id VARCHAR(30) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    type VARCHAR(10) NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE certification(
    user_id VARCHAR(30) PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    certification_number VARCHAR(4) NOT NULL
);

CREATE USER 'developer'@'%' IDENTIFIED BY 'tiger';