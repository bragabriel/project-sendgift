package com.gabriel.sendgift.infrastructure.persistence.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration //Load before initialization
public class MongoDBConfig {

    @Bean
    public MongoDatabaseFactory mongoConfigure(){
        //Returning the instance
        return new SimpleMongoClientDatabaseFactory("mongodb://root:example@localhost:27017/sendgift");
    }

    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoConfigure());
    }
}
