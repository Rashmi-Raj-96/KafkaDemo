package com.demoForKafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public final class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    //you can add here multiple topic names with separated commas
    @KafkaListener( groupId = "demo-cons-2",
            topics = "demo-for-3"
            //topicPartitions = {@TopicPartition(topic = "demo-for-3",partitions = {"2a"})}
            )
    public void consume(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID ) int partitionNo,  @Header (KafkaHeaders.RECEIVED_TOPIC) String topic  ) {
        logger.info(String.format("$$$$ => Consumed message: %s ", message));
        System.out.println("Received message from partition :" + partitionNo + " and topic name"+ topic);
    }
}
