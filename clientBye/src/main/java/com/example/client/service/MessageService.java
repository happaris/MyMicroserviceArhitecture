package com.example.client.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class MessageService {
    @Autowired
    KafkaTemplate<String, Long> kafka;
    private long helloNumb = -1;

    @KafkaListener(topics = "hello")
    public void setHelloNumb(ConsumerRecord<String, Long> record) {
        this.helloNumb = record.offset() + 1;
        kafka.send("countBreakpointHello", "hello", helloNumb);
    }

    @KafkaListener(topics = "returnHello")
    public void helper(Long helloNumb) {
        this.helloNumb = helloNumb;
    }

    public void setHelloNumb(long helloNumb) {
        this.helloNumb = helloNumb;
    }

    public long getHelloNumb() throws InterruptedException {
        while (helloNumb == -1) {
            kafka.send("givHello",  2L);
            Thread.sleep(1000);
            if (helloNumb == -1) {
                helloNumb = -2L;
            }
        }
        return helloNumb;
    }
}
