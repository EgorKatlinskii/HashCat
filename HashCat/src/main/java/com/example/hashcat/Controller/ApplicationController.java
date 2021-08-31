package com.example.hashcat.Controller;


import com.example.hashcat.Model.ReguestDTO;
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
    ApplicationService applicationService;

    @PostMapping
    public Mono<ResponseEntity<Void>> acceptanceOfApplication(@Valid @RequestBody ReguestDTO reguestDTO) {
        return applicationService.acceptanceOfApplication(reguestDTO.getEmail())
                .map(responseStatus -> responseStatus
                        ? new ResponseEntity<Void>(HttpStatus.OK)
                        : new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR));

        /*      return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));*/
    }

}
