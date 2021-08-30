package com.example.emailmanager.Controller;

import com.example.emailmanager.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class EmailConroller {

    @Autowired
    EmailService emailService;

    @GetMapping(value = "/{email}")
    public Mono<Boolean> sendMessage(@PathVariable("email") String email){
        return emailService.sendEmail(email,"Mailbox confirmation");
    }
}
