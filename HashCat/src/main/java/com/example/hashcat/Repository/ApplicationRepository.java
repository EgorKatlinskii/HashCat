package com.example.hashcat.Repository;

import com.example.hashcat.Model.Application;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ApplicationRepository extends ReactiveCrudRepository <Application, ObjectId > {
}
