package com.example.emailmanager.Repository;


import com.example.emailmanager.Model.MailBox;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


///!!!!!
@EnableReactiveMongoRepositories
public interface MailBoxRepository extends ReactiveCrudRepository<MailBox, ObjectId> {
    Mono<Boolean> existsByLogin(String login);
}
