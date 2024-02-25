package com.gabriel.sendgift.core.repositories;


import com.gabriel.sendgift.core.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
