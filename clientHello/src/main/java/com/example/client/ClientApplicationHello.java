package com.example.client;

import org.bouncycastle.asn1.dvcs.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableEurekaClient
@SpringBootApplication
public class ClientApplicationHello {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplicationHello.class, args);
    }

}
