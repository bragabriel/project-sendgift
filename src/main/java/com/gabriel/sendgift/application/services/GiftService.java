package com.gabriel.sendgift.application.services;

import com.gabriel.sendgift.application.exceptions.GiftNotFoundException;
import com.gabriel.sendgift.core.domain.gift.Gift;
import com.gabriel.sendgift.core.domain.gift.dto.GiftUpdateDto;
import com.gabriel.sendgift.core.repositories.GiftRepository;
import com.gabriel.sendgift.core.usecases.Gift.GiftBasicsUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftService implements GiftBasicsUseCase {
    private final GiftRepository giftRepository;
    private final UserService userService;

    public GiftService(
            GiftRepository giftRepository,
            UserService userService
    ) {
        this.giftRepository = giftRepository;
        this.userService = userService;
    }

    public List<Gift> getAll(){
        List<Gift> gifts = giftRepository.findAll();

        if(gifts.isEmpty())
            throw new GiftNotFoundException("Nenhum presente cadastrado");

        return gifts;
    }

    public Gift getById(String id){
        Gift gift = giftRepository.findById(id)
                .orElseThrow(() -> new GiftNotFoundException("Presente não encontrado para o ID: " + id));

        return gift;
    }

    public Gift registerGift(Gift gift) {
        userService.getById(gift.getSenderId(), "Remetente não encontrado!");
        userService.getById(gift.getRecipientId(), "Destinatário não encontrado!");

        giftRepository.save(gift);

        return gift;
    }

    public Gift updateGift(String id, GiftUpdateDto giftUpdateDto){
        Gift gift = giftRepository.findById(id)
                .orElseThrow(() -> new GiftNotFoundException("Presente não encontrado para o ID: " + id));

        if(!giftUpdateDto.getName().isEmpty()) gift.setName(giftUpdateDto.getName());
        if(!giftUpdateDto.getDescription().isEmpty()) gift.setDescription(giftUpdateDto.getDescription());
        if (giftUpdateDto.getRecipientId() != null) {
            gift.setRecipientId(giftUpdateDto.getRecipientId());
        }

        giftRepository.save(gift);

        return gift;
    }

    public void deleteGift(String id){
        Gift gift = giftRepository.findById(id)
                .orElseThrow(() -> new GiftNotFoundException("Presente não encontrado para o ID: " + id));

        giftRepository.delete(gift);
    }
}
