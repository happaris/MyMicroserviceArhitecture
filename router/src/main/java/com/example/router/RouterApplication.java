package com.example.router;

import com.example.router.model.Count;
import com.example.router.service.CountServiceInterface;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableKafka
public class RouterApplication {
    @Autowired
    private CountServiceInterface service;
    @Autowired
    private KafkaTemplate<String, Long> kafka;

    public static void main(String[] args) {
        SpringApplication.run(RouterApplication.class, args);
    }

    @KafkaListener(topics = "countBreakpointHello")
    public void update(ConsumerRecord<String, Long> record) {
        Count obj = new Count("hello", record.value());
        obj.setId(1L);
        service.save(obj);
    }
    @KafkaListener(topics = "bye")
    public void updateBye(ConsumerRecord<String, Long> record) {
        Count obj = new Count("Bye", record.offset() + 1);
        obj.setId(2L);
        service.save(obj);
    }
    @KafkaListener(topics = "givHello")
    public void helpHello() {
        List<Count> list = service.getAllCount();
        for (Count count : list) {
            if (count.getName().equals("hello")) {
                kafka.send("returnHello", count.getCount());
            }
        }
    }
}
