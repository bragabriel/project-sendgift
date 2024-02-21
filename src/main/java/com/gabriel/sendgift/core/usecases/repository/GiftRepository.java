package com.gabriel.sendgift.core.usecases.repository;

import com.gabriel.sendgift.core.domain.entity.Gift;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftRepository extends MongoRepository<Gift, String> {
}