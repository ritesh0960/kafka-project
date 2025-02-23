package com.personal.Kafka_project.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Kafkaconfiguration {

    @Bean
    public NewTopic newTopic(){
        return new NewTopic("studentTopic",4, (short) 1);
    }

}
