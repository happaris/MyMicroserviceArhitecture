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
    private long byeNumb = -1;

    @KafkaListener(topics = "bye")
    public void setByeNumb(ConsumerRecord<String, Long> record) {
        this.byeNumb = record.offset();
        kafka.send("countBreakpoint","bye", byeNumb);
    }
    @KafkaListener(topics = "hello")
    public void setHelloNumb(ConsumerRecord<String, Long> record) {
        this.helloNumb = record.offset();
        kafka.send("countBreakpoint","hello", helloNumb);
    }

    @KafkaListener(topics = "returnHello")
    public void helper(Long helloNumb) {
        setHelloNumb(helloNumb);
    }

    public long getByeNumb() {
        return byeNumb;
    }

    public void setByeNumb(long byeNumb) {
        this.byeNumb = byeNumb;
    }

    public long getHelloNumb() {
        return helloNumb;
    }

    public void setHelloNumb(long helloNumb) {
        this.helloNumb = helloNumb;
    }
}
