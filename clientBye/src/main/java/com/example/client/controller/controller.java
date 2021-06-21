package com.example.client.controller;

import com.example.client.service.MessageService;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
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
    public String byePage() {
        kafkaTemplate.send("bye", "groupId2", 2L);
        while (msgService.getHelloNumb() == -1 ) {
            kafkaTemplate.send("givHello",  2L);
        }
        return "Всего доброго! Вы здоровались " + msgService.getHelloNumb() + " раз.";
    }
}
