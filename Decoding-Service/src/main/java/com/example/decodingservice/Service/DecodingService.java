package com.example.decodingservice.Service;

import com.example.decodingservice.Model.CurrentApplication;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DecodingService {

    final int HTTP_STATUS_OK = 200;

    @Autowired
    private WebClient.Builder webClientBuilder;


    public Mono<ResponseEntity<?>> decodingHashes(String email) {
        return getCurrentApplication(email)
                .flatMapIterable(CurrentApplication::getHashList)
                .flatMap(this::sendHashDecodingApi)
                .collectList()
                .map(arrayList -> String.join("\n", arrayList))
                .flatMap(string -> webClientBuilder.build().get().uri("http://localhost:8081/{hashes}", string)
                        .retrieve()
                        .toEntity(Boolean.class))
                .map(responseStatus -> responseStatus.getStatusCodeValue() == HTTP_STATUS_OK
                        ? new ResponseEntity<>(HttpStatus.OK)
                        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }


    private Mono<String> sendHashDecodingApi(String hash) {
        return WebClient.builder()
                .baseUrl("https://md5decrypt.net/en/Api/api")
                .build().get()
                .uri("?hash=" + hash + "&hash_type=md5&email=egor.katlinscky@yandex.ru&code=0ce5823d479bff2b")
                .retrieve()
                .bodyToMono(String.class).map(html -> Jsoup.parse(html).body()
                        .getElementsByTag("body").text());
    }


    private Mono<CurrentApplication> getCurrentApplication(String email) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8082/{email}", email)
                .retrieve().bodyToMono(CurrentApplication.class);
    }

}
