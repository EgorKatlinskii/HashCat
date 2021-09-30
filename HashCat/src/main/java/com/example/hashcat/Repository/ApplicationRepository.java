package com.example.hashcat.Repository;

import com.example.hashcat.Model.ReguestDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@EnableReactiveMongoRepositories
public interface ApplicationRepository extends ReactiveCrudRepository<ReguestDTO, ObjectId> {

    Mono<ReguestDTO> findTopByEmail(String email);

    /*Mono<Void> deleteReguestDTO(ReguestDTO reguestDTOMono);*/

}
