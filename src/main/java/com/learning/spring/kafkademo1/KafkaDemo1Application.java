package com.learning.spring.kafkademo1;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@Slf4j
public class KafkaDemo1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemo1Application.class, args);
    }

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void run(String... args) throws Exception {
        this.template.send("myTopic", "foo1");
        this.template.send("myTopic", "foo2");
        this.template.send("myTopic", "foo3");
        log.info("All sent");
    }

    @KafkaListener(topics = "myTopic")
    public void listenOnRaw(ConsumerRecord<?, ?> cr) throws Exception {
        log.info(cr.toString());
    }

    @KafkaListener(topics = "myTopic")
    public void listenOnValue(String value) throws Exception {
        log.info(value);
    }

}

