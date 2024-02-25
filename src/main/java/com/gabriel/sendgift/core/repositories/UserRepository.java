package com.gabriel.sendgift.infrastructure.persistence.repository;


import com.gabriel.sendgift.core.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
