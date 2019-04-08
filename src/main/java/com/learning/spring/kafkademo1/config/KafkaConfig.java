package com.learning.spring.kafkademo1.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    /**
     * This consumer must explicit name here as by default it is defined in {@link KafkaAutoConfiguration},
     * but when there is already ConsumerFactory exist, the default one will not initialize
     * so need add this default one
     *
     * @return
     */
    @Bean
    public <T> ConsumerFactory<String, T> kafkaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<String, T>(kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public <k, v> ConcurrentKafkaListenerContainerFactory<k, v> kafkaListenerContainerFactory(ConsumerFactory<k, v> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<k, v> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory);
        return factory;
    }


    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        return new DefaultKafkaConsumerFactory<String, String>(kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }
}
