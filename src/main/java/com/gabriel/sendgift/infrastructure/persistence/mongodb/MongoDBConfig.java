package com.gabriel.sendgift.infrastructure.persistence.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.gabriel.sendgift.core.repositories")
public class MongoDBConfig {

    @Bean
    public MongoDatabaseFactory mongoConfigure(){
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        //Returning the instance
        String uri = "mongodb://" + user + ":" + password + "@localhost:27017/admin";
        return new SimpleMongoClientDatabaseFactory(uri);
    }

    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoConfigure());
    }
}
