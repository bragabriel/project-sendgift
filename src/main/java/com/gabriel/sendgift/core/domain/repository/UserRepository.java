package com.gabriel.sendgift.core.domain.repository;


import com.gabriel.sendgift.core.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
