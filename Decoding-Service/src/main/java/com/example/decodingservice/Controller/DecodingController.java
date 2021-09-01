package com.example.decodingservice.Controller;

import com.example.decodingservice.Service.DecodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DecodingController {

    @Autowired
    private DecodingService decodingService;

    @PostMapping
    public Mono<ResponseEntity<?>> decodingHashes(@PathVariable("email") String email){
        return decodingService.decodingHashes(email);
    }
}
