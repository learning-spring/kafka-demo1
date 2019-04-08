package com.learning.spring.kafkademo1.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.spring.kafkademo1.bean.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class StringProducer implements CommandLineRunner {

    @Resource
    private KafkaTemplate<String, String> template;

    @Override
    public void run(String... args) throws Exception {
        //below is for string
        this.template.send("myTopicRawConsumerRecord", "foo1");
        this.template.send("myTopicRawConsumerRecord", "foo2");
        this.template.send("myTopicRawConsumerRecord", "foo3");

        this.template.send("myTopicStringValue", "foo1");
        this.template.send("myTopicStringValue", "foo2");
        this.template.send("myTopicStringValue", "foo3");

        //below is for json
        publichJsonRequest("myTopicJsonRaw");

        publichJsonRequest("myTopicJsonValue");
        log.info("All sent");
    }

    private void publichJsonRequest(String topic) {
        publishTestCase(topic, "testCase1");
        publishTestCase(topic, "testCase2");
        publishTestCase(topic, "testCase3");
    }

    private void publishTestCase(String topic, String name) {
        TestCase testCase = generateTestCase(name);
        try {
            this.template.send(topic, objectMapper.writeValueAsString(testCase));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private int id = 1;
    private ObjectMapper objectMapper = new ObjectMapper();

    private TestCase generateTestCase(String name) {
        TestCase testcase = new TestCase();
        testcase.setName(name);
        testcase.setId(id++);
        return testcase;

    }
}
