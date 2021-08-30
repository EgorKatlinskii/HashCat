package com.example.hashcat.Controller;


import com.example.hashcat.Model.Application;
import com.example.hashcat.Repository.ApplicationRepository;
import com.example.hashcat.Service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
public class ApplicationController {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ApplicationService applicationService;

    @PostMapping
    public Mono<ResponseEntity<Void>> acceptanceOfApplication(@Valid @RequestBody Application application) {
        return applicationService.acceptanceOfApplication(application.getEmail())
                .map(responseStatus -> responseStatus
                        ? new ResponseEntity<Void>(HttpStatus.OK)
                        : new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR));

        /*      return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));*/
    }

}
