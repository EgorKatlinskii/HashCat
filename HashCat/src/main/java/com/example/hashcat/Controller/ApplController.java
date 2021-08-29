package com.example.hashcat.Controller;


import com.example.hashcat.Model.Application;
import com.example.hashcat.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
public class ApplController {

    @Autowired
    ApplicationRepository applicationRepos;


    @GetMapping
    public Flux<Application> getAllApplication(){
        return applicationRepos.findAll();
    }


    @PostMapping
    public Mono<Application> saveProduct(@Valid @RequestBody  Application appl){
        System.out.println("controller method called ...");
        return applicationRepos.save(appl);
    }

}
