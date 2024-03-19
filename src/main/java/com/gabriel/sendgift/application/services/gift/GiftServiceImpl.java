package com.gabriel.sendgift.application.services.gift;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.sendgift.application.exceptions.GiftNotFoundException;
import com.gabriel.sendgift.application.services.user.UserServiceImpl;
import com.gabriel.sendgift.core.adapter.EmailClient;
import com.gabriel.sendgift.core.domain.email.EmailDTO;
import com.gabriel.sendgift.core.domain.gift.Gift;
import com.gabriel.sendgift.core.domain.gift.dto.GiftUpdateDto;
import com.gabriel.sendgift.core.domain.user.dto.UserResponse;
import com.gabriel.sendgift.core.repositories.GiftRepository;
import com.gabriel.sendgift.core.usecases.gift.GiftBasicsUseCase;
import com.gabriel.sendgift.core.usecases.gift.GiftDeliveryUseCase;
import com.gabriel.sendgift.infrastructure.messaging.kafka.GiftProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftServiceImpl implements GiftBasicsUseCase, GiftDeliveryUseCase {
    private final GiftRepository giftRepository;
    private final UserServiceImpl userServiceImpl;
    public final EmailClient emailClient;
    private final GiftProducer giftProducer;

    @Autowired
    private ObjectMapper objectMapper;

    public GiftServiceImpl(
            GiftRepository giftRepository,
            UserServiceImpl userServiceImpl,
            EmailClient emailClient,
            GiftProducer giftProducer
    ) {
        this.giftRepository = giftRepository;
        this.userServiceImpl = userServiceImpl;
        this.emailClient = emailClient;
        this.giftProducer = giftProducer;
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

    @Override
    public List<Gift> getGiftsSentByIdUser(String idUser) {
        List<Gift> gifts = giftRepository.findAllBySenderId(idUser);
        if (gifts.isEmpty()) {
            throw new GiftNotFoundException("Nenhum presente encontrado para o ID do remetente: " + idUser);
        }
        return gifts;
    }

    @Override
    public List<Gift> getGiftsReceivedByIdUser(String idUser) {
        List<Gift> gifts = giftRepository.findAllByRecipientId(idUser);
        if (gifts.isEmpty()) {
            throw new GiftNotFoundException("Nenhum presente encontrado para o ID do destinatário: " + idUser);
        }
        return gifts;
    }

    @Override
    public void sendGift(String idGift) {
        Gift gift = giftRepository.findById(idGift)
                .orElseThrow(() -> new GiftNotFoundException("Presente não encontrado para o ID: " + idGift));

        giftProducer.sendGift("giftReadyToDelivery-topic", gift);
    }

    @KafkaListener(topics = "giftReadyToDelivery-topic", groupId = "group-1")
    public void processGift(String messageJson) { //Gift Consumer
        try {
            Gift gift = objectMapper.readValue(messageJson, Gift.class);
            System.out.println(
                    "Consumer gift: " + gift.getName() + "\n" +
                    "Descrição: " + gift.getDescription() + "\n" +
                    "Remetente: " + gift.getSenderId() + "\n" +
                    "Destinatário: " + gift.getRecipientId()
            );

            EmailDTO email = constructGiftConfirmationEmail(gift, true);
            emailClient.giftSentEmailConfirmation(email);
            //Outros serviços: chamada do serviço de embalagem do gift

            // Marcando a mensagem como concluída
            //acknowledgment.acknowledge();

            //Enviando para o tópico de "Presente Entregue"
            giftProducer.sendGift("giftDeliveredy-topic", gift);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "giftDeliveredy-topic", groupId = "group-1")
    public void isDelivered(String messageJson) { //Gift Consumer
        try {
            Gift gift = objectMapper.readValue(messageJson, Gift.class);
            System.out.println(
                    "Consumer gift: " + gift.getName() + "\n" +
                            "Descrição: " + gift.getDescription() + "\n" +
                            "Remetente: " + gift.getSenderId() + "\n" +
                            "Destinatário: " + gift.getRecipientId()
            );

            EmailDTO email = constructGiftConfirmationEmail(gift, false);
            emailClient.giftReceivedEmailConfirmation(email);

            // Marcando a mensagem como concluída
            //acknowledgment.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private EmailDTO constructGiftConfirmationEmail(Gift gift, boolean isSender) {
        UserResponse sender = userServiceImpl.getById(gift.getSenderId());
        UserResponse recipient = userServiceImpl.getById(gift.getRecipientId());

        String recipientEmail = isSender ? sender.getEmail() : recipient.getEmail();

        return new EmailDTO(sender.getName(), recipient.getName(), recipientEmail);
    }
}
