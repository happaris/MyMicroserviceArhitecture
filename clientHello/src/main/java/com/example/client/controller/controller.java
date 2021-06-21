package com.example.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableKafka
public class controller {
    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;


    @GetMapping("/api/hello")
    public String helloPage() {
        kafkaTemplate.send("hello", "groupId", 1L);
        return "Добрый день! =0_o=";
    }

}
