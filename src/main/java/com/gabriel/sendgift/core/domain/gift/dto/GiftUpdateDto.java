package com.gabriel.sendgift.core.domain.gift.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class GiftUpdateDto {
    private String name;
    private String description;
    private String recipientId;
}
