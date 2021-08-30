package com.example.emailmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmailManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailManagerApplication.class, args);
    }

}
