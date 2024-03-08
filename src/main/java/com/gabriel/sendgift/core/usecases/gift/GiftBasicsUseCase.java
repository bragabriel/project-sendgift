package com.gabriel.sendgift.core.usecases.gift;

import com.gabriel.sendgift.core.domain.gift.Gift;
import com.gabriel.sendgift.core.domain.gift.dto.GiftUpdateDto;

import java.util.List;

public interface GiftBasicsUseCase {
    List<Gift> getAll();
    Gift getById(String id);
    Gift registerGift(Gift gift);
    Gift updateGift(String id, GiftUpdateDto giftUpdateDto);
    void deleteGift(String id);
    List<Gift> getGiftsSentByIdUser(String idUser);
    List<Gift> getGiftsReceivedByIdUser(String idUser);
}
