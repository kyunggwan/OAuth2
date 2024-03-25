package com.oauth_test.back.provider;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailProvider {

    private final JavaMailSender javaMailSender;

    private final String SUBJECT = "[no-reply][테스트 서비스] 인증 메일입니다."; // 메일 제목

    public boolean sendCertificationMail(String email, String certificationNumber) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email); // 이메일 수신자
            messageHelper.setSubject(SUBJECT); // 이메일 제목
            messageHelper.setText(htmlContent, true); // 이메일 본문

            javaMailSender.send(message); // 이메일 전송

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;

        }
        return true;
    }

    private String getCertificationMessage(String certificationNumber) {

        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'> [테스트 서비스] 인증메일</h1>";
        certificationMessage += "<h1 style='text-align: center;'> 인증 코드: <strong style='font-size: 32px; letter-spacing: 8px:'>"
                + certificationNumber + "</strong></h3>";
        return certificationMessage;
    }
}
