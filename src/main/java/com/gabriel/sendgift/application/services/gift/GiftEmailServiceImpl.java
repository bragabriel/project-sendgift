package com.gabriel.sendgift.application.services.gift;

import com.gabriel.sendgift.core.domain.gift.Gift;
import com.gabriel.sendgift.core.repositories.GiftRepository;
import com.gabriel.sendgift.core.usecases.Gift.GiftEmailServiceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiftEmailServiceImpl implements GiftEmailServiceUseCase {

    @Autowired
    private GiftRepository giftRepository;

    @Override
    public void sendEmail(Gift gift) {
        //Serviço de envio de e-mail ao usuário, com a confirmação de que o presente foi enviado
    }
}
