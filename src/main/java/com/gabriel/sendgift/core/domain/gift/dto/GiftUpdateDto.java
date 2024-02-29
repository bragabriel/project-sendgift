package com.gabriel.sendgift.core.domain.gift.dto;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class GiftUpdateDto {
    private String name;
    private String description;
    private String recipientId;
}
