package com.gabriel.sendgift.core.usecases.gift;

import org.springframework.kafka.support.Acknowledgment;

public interface GiftDeliveryUseCase {
    void sendGift(String idGift);
    void processGift(String messageJson);
    void isDelivered(String messageJson);
}
