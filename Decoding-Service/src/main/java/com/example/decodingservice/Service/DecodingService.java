package com.example.decodingservice.Service;

import com.example.decodingservice.Model.CurrentApplication;
import com.example.decodingservice.Model.RequestDTOMailBox;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DecodingService {

    final int HTTP_STATUS_OK = 200;

    @Autowired
    private WebClient.Builder webClientBuilder;


/*
    public Mono<Void> decodingHashes(String email) {
        RequestDTOMailBox requestDTOMailBox =new RequestDTOMailBox(new ObjectId(),email);
        Mono<Boolean> saveStatus = webClientBuilder.build().post()
                .uri("http://localhost:8081/").bodyValue(requestDTOMailBox)
                .retrieve().bodyToMono(Boolean.class);
        saveStatus.subscribe(System.out::println);
        return getCurrentApplication(email)
                .flatMapIterable(CurrentApplication::getHashList)
                .flatMap(this::sendHashDecodingApi)
                .collectList()
                .map(arrayList -> String.join("\n", arrayList))
                .flatMap(string -> webClientBuilder.build().get().uri("http://localhost:8081/{hashes}/{email}", string,email)
                        .retrieve()
                        .toEntity(Boolean.class))
                .flatMap(responseStatus -> responseStatus.getStatusCodeValue() == HTTP_STATUS_OK
                        ?  webClientBuilder.build().get().uri("http://localhost:8082//{email}", email)
                        .retrieve().bodyToMono(Void.class).subscribe()
                        : Mono.when());
    }
*/




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
