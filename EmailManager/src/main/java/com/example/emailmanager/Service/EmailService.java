package com.example.emailmanager.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;


    ///!!!!!!!!!!
    public Mono<Boolean> sendEmail(String toAddress, String subject)  {

        return Mono.fromCallable(() -> {
            try {
                MimeMessage mimeMessage = emailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setTo(toAddress);
                messageHelper.setSubject(subject);
                messageHelper.setText("Сlick on the link to confirm your identity: " + "ссылка");

                emailSender.send(mimeMessage);
                return true;
            } catch (Exception e) {
                log.warn("Failed to send email with subject {}, due to {}", subject, e.getMessage(), e);
                return false;
            }
        }).onErrorReturn(false);

    }
}
