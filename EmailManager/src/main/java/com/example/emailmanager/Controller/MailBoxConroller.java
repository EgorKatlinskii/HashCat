package com.example.emailmanager.Controller;

import com.example.emailmanager.Model.MailBox;
import com.example.emailmanager.Repository.MailBoxRepository;
import com.example.emailmanager.Service.MailBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class MailBoxConroller {

    @Autowired
    MailBoxService emailService;

    @Autowired
    MailBoxRepository mailBoxRepository;

    @GetMapping(value = "/{email}")
    public Mono<Boolean> sendMessage(@PathVariable("email") String email){
        return emailService.sendEmail(email);

    }

    @PostMapping
    public Mono<Boolean> saveMail(@RequestBody MailBox mailBox){
        return emailService.saveLogin(mailBox);

    }

    @GetMapping("/{hashes}/{email}")
    public Mono<Boolean> sendHashesEmail(@PathVariable("hashes") String hashes,@PathVariable("email") String email){
        return emailService.sendHashesEmail(hashes,email);
    }

}
