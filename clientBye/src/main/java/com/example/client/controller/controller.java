package com.example.client.controller;

import com.example.client.service.MessageService;
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
    @Autowired
    private MessageService msgService;

    @GetMapping("/api/bye")
    public String byePage() throws InterruptedException{
        kafkaTemplate.send("bye", 1L);
        if (msgService.getHelloNumb() == -2) {
            return "Что-то пошло не так =( (NO CONNECTION TO THE DATABASE)";
        }
        return "Всего доброго! Вы здоровались " + msgService.getHelloNumb() + " раз.";
    }
}
