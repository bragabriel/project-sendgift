package com.gabriel.sendgift.core.usecases.Gift;

import com.gabriel.sendgift.core.domain.gift.Gift;

public interface GiftEmailServiceUseCase {
    void sendEmail(Gift gift);
}
