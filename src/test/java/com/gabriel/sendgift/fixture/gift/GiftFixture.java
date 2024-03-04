package com.gabriel.sendgift.fixture.gift;

import com.gabriel.sendgift.core.domain.gift.Gift;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GiftFixture {
    public static Gift createGift(){

        Gift gift = new Gift();
        gift.setId("1");
        gift.setName("Bolo");
        gift.setDescription("Bolo de chocolate");
        gift.setSenderId("b1");
        gift.setRecipientId("c2");

        return gift;
    }
}
