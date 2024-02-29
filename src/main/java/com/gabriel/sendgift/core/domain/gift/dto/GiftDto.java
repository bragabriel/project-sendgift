package com.gabriel.sendgift.core.domain.gift.dto;

import lombok.Data;

@Data
public class GiftDto {
    private String name;
    private String description;
    private String recipientId;
    private String senderId;
}
