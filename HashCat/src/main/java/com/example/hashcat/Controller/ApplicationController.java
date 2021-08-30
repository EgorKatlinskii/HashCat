package com.example.hashcat.Controller;


import com.example.hashcat.Model.Application;
import com.example.hashcat.Repository.ApplicationRepository;
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


/*    @GetMapping
    public Flux<Application> getAllApplication(){
        return applicationRepos.findAll();
    }


    @PostMapping
    public Mono<Application> saveProduct(@Valid @RequestBody  Application appl){
        System.out.println("controller method called ...");
        return applicationRepos.save(appl);
    }*/
    @PostMapping
    public Mono<ResponseEntity<Void>> acceptanceofApplication(@Valid @RequestBody Application application){

        return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));
    }


}
