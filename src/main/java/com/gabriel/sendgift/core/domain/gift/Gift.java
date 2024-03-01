package com.gabriel.sendgift.core.domain.gift;

import com.gabriel.sendgift.core.domain.gift.dto.GiftDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "gifts")
public class Gift {
    @Id
    private String id;
    private String name;
    private String description;
    private String senderId;
    private String recipientId;

    public static Gift mapToGift(GiftDto giftDto) {
        Gift gift = new Gift();
        gift.setName(giftDto.getName());
        gift.setDescription(giftDto.getDescription());
        gift.setSenderId(giftDto.getSenderId());
        gift.setRecipientId(giftDto.getRecipientId());
        return gift;
    }
}