package com.learning.spring.kafkademo1.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringConsumer {

    @KafkaListener(topics = "myTopicRawConsumerRecord", containerFactory = "stringKafkaListenerContainerFactory")
    public void listenOnRaw(ConsumerRecord<?, ?> cr) throws Exception {
        log.info("listen on Raw: "+cr.toString());
    }

    @KafkaListener(topics = "myTopicStringValue", containerFactory = "stringKafkaListenerContainerFactory")
    public void listenOnValue(String value) throws Exception {
        log.info("listen on value: "+value);
    }
}
