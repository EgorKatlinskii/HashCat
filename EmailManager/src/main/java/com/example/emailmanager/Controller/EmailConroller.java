package com.example.emailmanager.Controller;

import com.example.emailmanager.Service.MailBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class EmailConroller {

    @Autowired
    MailBoxService emailService;

    @GetMapping(value = "/{email}")
    public Mono<Boolean> sendMessage(@PathVariable("email") String email){
        return emailService.sendEmail(email,"Mailbox confirmation");

    }


}
