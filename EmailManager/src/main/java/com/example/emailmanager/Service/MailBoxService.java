package com.example.emailmanager.Service;

import com.example.emailmanager.Model.MailBox;
import com.example.emailmanager.Repository.MailBoxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class MailBoxService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    MailBoxRepository mailBoxRepository;


    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Boolean> getStatus(String login) {
        return mailBoxRepository.existsByLogin(login);
    }


    //Вызывается после перехода по ссылке на почтовом ящике
    public Mono<Boolean> saveLogin(MailBox mailBox) {
        return mailBoxRepository.save(mailBox)
                .map(entity -> true)
                .onErrorReturn(false);
    }


    public Mono<Boolean> sendEmail(String toAddress, String subject) {
        return getStatus(toAddress).flatMap(status ->
                status
                        ? webClientBuilder.build().post()
                        .uri("http://localhost:8083/", toAddress)
                        .retrieve().bodyToMono(Boolean.class).onErrorReturn(false)//!!!!! запуск микросервиса по отправке хешей для расшифровки

                        : Mono.fromCallable(() -> {

                        MimeMessage mimeMessage = emailSender.createMimeMessage();
                        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                        messageHelper.setTo(toAddress);
                        messageHelper.setSubject(subject);
                        messageHelper.setText("Сlick on the link to confirm your identity: " + "hhtp");
                        emailSender.send(mimeMessage);
                        return true;
                }).onErrorReturn(false));

    }



    public Mono<Boolean> sendHashesEmail(String hashes){

    }
}
