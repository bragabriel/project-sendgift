package com.gabriel.sendgift.core.usecases.gift;

import com.gabriel.sendgift.core.domain.gift.Gift;

public interface GiftEmailServiceUseCase {
    void sendEmail(Gift gift);
}
