package com.gabriel.sendgift.core.repositories;

import com.gabriel.sendgift.core.domain.gift.Gift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends MongoRepository<Gift, String> {
}