package com.personal.Kafka_project.Controllers;

import com.personal.Kafka_project.KafkaProducer.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController("/")
public class ServiceController {

    @Autowired
    KafkaMessagePublisher kafkaMessagePublisher;
    @GetMapping("sendMessage/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message){
        Boolean status = kafkaMessagePublisher.sendMessageToTopic(message);
      if(status==true) {

          return new ResponseEntity<>("Message published successfully", HttpStatus.OK);
      }
      else {
          return new ResponseEntity<>("Some error occurred",HttpStatus.BAD_REQUEST);
      }
    }
}
