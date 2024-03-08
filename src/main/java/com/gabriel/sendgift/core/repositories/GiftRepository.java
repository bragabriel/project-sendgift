package com.gabriel.sendgift.core.repositories;

import com.gabriel.sendgift.core.domain.gift.Gift;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends MongoRepository<Gift, String> {
    List<Gift> findAllBySenderId(String senderId);
    List<Gift> findAllByRecipientId(String recipientId);
}