package com.example.emailmanager.Service;

import com.example.emailmanager.Model.MailBox;
import com.example.emailmanager.Repository.MailBoxRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
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


    final String ACTIVE_STATUS = "ACTIVE";
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    MailBoxRepository mailBoxRepository;


    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Boolean> getStatus(String login) {
        return mailBoxRepository.existsByLogin(login);
    }



    public Mono<Boolean> saveLogin(MailBox mailBox) {
        return mailBoxRepository.existsByLogin(mailBox.getLogin())
                .flatMap(response -> !response
                        ? mailBoxRepository.save(mailBox).map(entity -> true).onErrorReturn(false)
                        : Mono.just(false));
    }


    public Mono<Boolean> sendEmail(String toAddress) {
        return getStatus(toAddress).flatMap(status ->
                status
                        ? webClientBuilder.build().get()
                        .uri("http://localhost:8083/"+ toAddress)
                        .retrieve().bodyToMono(Boolean.class)
                        : sendLetterEmail(toAddress,"Follow the link to confirm your mail:","http://localhost:8083/"+toAddress));

    }

    private Mono<Boolean> sendLetterEmail(String toAddress,String message,String other){
        return Mono.fromCallable(() -> {

            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject("Service HashCat");
            messageHelper.setText(message + other);
            emailSender.send(mimeMessage);
            return true;
        }).onErrorReturn(false);
    }


    public Mono<Boolean> sendHashesEmail(String hashes,String email){
        return sendLetterEmail(email,"Your hashes:\n",hashes);
    }
}
