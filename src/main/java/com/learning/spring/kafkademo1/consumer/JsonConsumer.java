package com.learning.spring.kafkademo1.consumer;

import com.learning.spring.kafkademo1.bean.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * compared with {@link StringConsumer}, only containerFactory is not set, it will use default as kafkaListenerContainerFactory
 */
@Component
@Slf4j
public class JsonConsumer {

    /**
     * If not set containerFactory it will use kafkaListenerContainerFactory by default,
     * which is created by default properties which will
     * use json as value serialization
     *
     * @param cr
     * @throws Exception
     */
    @KafkaListener(topics = "myTopicJsonRaw")
    public void listenOnRaw(ConsumerRecord<?, ?> cr) throws Exception {
        log.info("listen on raw:" + cr.toString());
    }

    @KafkaListener(topics = "myTopicJsonValue")
    public void listenOnValue(TestCase value) throws Exception {
        log.info("listen on value: " + value.toString());
    }
}
