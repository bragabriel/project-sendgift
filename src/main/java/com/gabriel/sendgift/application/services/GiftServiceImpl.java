package com.gabriel.sendgift.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.sendgift.application.exceptions.GiftNotFoundException;
import com.gabriel.sendgift.core.domain.gift.Gift;
import com.gabriel.sendgift.core.domain.gift.dto.GiftUpdateDto;
import com.gabriel.sendgift.core.repositories.GiftRepository;
import com.gabriel.sendgift.core.usecases.Gift.GiftBasicsUseCase;
import com.gabriel.sendgift.core.usecases.Gift.GiftDeliveryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftServiceImpl implements GiftBasicsUseCase, GiftDeliveryUseCase {
    private final GiftRepository giftRepository;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    public GiftServiceImpl(
            GiftRepository giftRepository,
            UserServiceImpl userServiceImpl
    ) {
        this.giftRepository = giftRepository;
        this.userServiceImpl = userServiceImpl;
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
        userServiceImpl.getById(gift.getSenderId(), "Remetente não encontrado!");
        userServiceImpl.getById(gift.getRecipientId(), "Destinatário não encontrado!");

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

    @KafkaListener(topics = "deliveryGift-topic", groupId = "group-1")
    public void receiveGift(String messageJson) { //Gift Consumer
        try {
            Gift gift = objectMapper.readValue(messageJson, Gift.class);
            System.out.println(
                    "Consumer gift: " + gift.getName() + "\n" +
                    "Descrição: " + gift.getDescription() + "\n" +
                    "Remetente: " + gift.getSenderId() + "\n" +
                    "Destinatário: " + gift.getRecipientId()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
