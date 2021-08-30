package com.example.hashcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HashCatApplication {

    public static void main(String[] args) {
        SpringApplication.run(HashCatApplication.class, args);
    }

}
