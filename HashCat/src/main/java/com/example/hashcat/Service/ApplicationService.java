package com.example.hashcat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApplicationService {


    @Autowired
    private WebClient.Builder webClientBuilder;


    public Mono<Boolean> acceptanceOfApplication(String email){
       return webClientBuilder.build().get()
               .uri("http://localhost:8081/{email}", email)
               .retrieve().bodyToMono(Boolean.class).onErrorReturn(false);
    }

}
