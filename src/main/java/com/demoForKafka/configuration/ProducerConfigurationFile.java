package com.demoForKafka.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class ProducerConfigurationFile {
        @Bean
        public ProducerFactory<String, String> producerFactoryString() {

            //responsible for creating Kafka Producer instances.

            Map<String, Object> configProps = new HashMap<>();
            //basically maps all the properties which you can either mention in application.yaml/application.properties
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.ACKS_CONFIG,"ALL");
            configProps.put(ProducerConfig.RETRIES_CONFIG,"3");
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<String, String> kafkaTemplateString() {
            //helps us to send messages to their respective topic
            return new KafkaTemplate<>(producerFactoryString());
        }


}
