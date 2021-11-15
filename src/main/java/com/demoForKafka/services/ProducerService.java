package com.demoForKafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public final class ProducerService {
    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "demo-for-3";

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String message) {
        logger.info(String.format("$$$$ => Producing message: %s", message));
        for(int i=0;i<100;i++){
            System.out.println(i);
            kafkaTemplate.send(TOPIC,Integer.toString(i),"test message - "+i);
        }
        //if we dont want to work with future we can create kafkaListener
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(TOPIC, message);
        //sendMessageAPI, makes the thread wait to get the response of the message sent, but kafka is fast streaming processs, and waiting for the response slows down the producer
        //so to make its asynchronous we use callback
        future.addCallback(new ListenableFutureCallback<SendResult<String,String>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message=[ {} ] due to : {}", message, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
            }
        });
    }
}
