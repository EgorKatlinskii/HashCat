package com.example.hashcat.Service;

import com.example.hashcat.Model.ReguestDTO;
import com.example.hashcat.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApplicationService {


    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ApplicationRepository applicationRepository;

    public Mono<Boolean> acceptanceOfApplication(String email) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8081/{email}", email)
                .retrieve().bodyToMono(Boolean.class);
    }

    public Mono<ReguestDTO> getCurrentApplication(String email) {
        return applicationRepository.findTopByEmail(email);
    }

    public Mono<Boolean> save(ReguestDTO application) {
        return applicationRepository.save(application).map(responseEntity -> true)
                .onErrorReturn(false);
    }

    public Mono<Void> customDeleteApplication(String email){
        Mono<ReguestDTO> reguestDTOMono = applicationRepository.findTopByEmail(email);
        return applicationRepository.delete(((ReguestDTO) reguestDTOMono.subscribe()));
    }

}
