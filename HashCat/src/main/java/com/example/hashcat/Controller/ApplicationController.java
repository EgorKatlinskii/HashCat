package com.example.hashcat.Controller;


import com.example.hashcat.Model.ReguestDTO;
import com.example.hashcat.Service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
public class ApplicationController {


    @Autowired
    ApplicationService applicationService;

    @PostMapping
    public Mono<ResponseEntity<Void>> acceptanceOfApplication(@Valid @RequestBody ReguestDTO reguestDTO) {
         applicationService.save(reguestDTO).subscribe();
        /*saveStatus.subscribe(System.out::println);*/
        return applicationService.acceptanceOfApplication(reguestDTO.getEmail())
                .map(responseStatus -> responseStatus
                        ? new ResponseEntity<Void>(HttpStatus.OK)
                        : new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR));

        /*      return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));*/
    }


    @GetMapping("/{email}")
    public Mono<?> getApplicationByEmail(@PathVariable("email") String email){
        return applicationService.getCurrentApplication(email);
    }

    /*@PostMapping(value = "/save")
    public Mono<ReguestDTO> saveProduct(@Valid @RequestBody ReguestDTO appl){
        System.out.println("controller method called ...");
        return (Mono<ReguestDTO>) applicationService.save(appl);
    }*/



}
