package com.gabriel.sendgift.infrastructure.messaging.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GiftProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    //              <mensagem, tópico>

    @Autowired
    private ObjectMapper objectMapper;

    public GiftProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendGift(String topic, Object gift){
        try {
            String messageJson = objectMapper.writeValueAsString(gift); // Transforma o objeto em JSON
            kafkaTemplate.send(topic, messageJson); // Envia a mensagem para o tópico do Kafka
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
