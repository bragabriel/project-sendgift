package com.gabriel.sendgift.core.domain.repository;

import com.gabriel.sendgift.core.domain.model.Gift;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftRepository extends MongoRepository<Gift, String> {
}