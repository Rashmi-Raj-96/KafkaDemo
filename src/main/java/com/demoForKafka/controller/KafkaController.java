package com.demoForKafka.controller;

import com.demoForKafka.services.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public final class KafkaController {
    private final ProducerService producerService;

    public KafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam String message) {
        producerService.sendMessage(message);
    }

    @PostMapping("/publishes")
    public void testInPostman(@RequestParam(value = "message",
            defaultValue = "Demo Message to Send") String message) {
        producerService.sendMessage(message);
    }
}