package com.gabriel.sendgift.core.adapter;

import com.gabriel.sendgift.core.domain.email.EmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-microservice", url = "http://localhost:8082/api/email")
public interface EmailClient {

    @PostMapping("/giftSent")
    void giftSentEmailConfirmation(@RequestBody EmailDTO emailDTO);
    @PostMapping("/giftReceived")
    void giftReceivedEmailConfirmation(@RequestBody EmailDTO emailDTO);
}