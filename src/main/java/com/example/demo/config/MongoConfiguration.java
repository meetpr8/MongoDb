package com.example.demo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration {
    @Bean
    public MongoClient mongoClient()
    {
        return MongoClients.create("mongodb+srv://meet2301:meet-2301-2508@mflix.w6tx3.mongodb.net/sample_mflix?retryWrites=true&w=majority");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "sample_mflix");
    }

    @Override
    protected String getDatabaseName() {
        return "sample_mflix";
    }
}
