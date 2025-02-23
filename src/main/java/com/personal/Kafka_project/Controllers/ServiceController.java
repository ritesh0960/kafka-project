package com.personal.Kafka_project.Controllers;

import com.personal.Kafka_project.Entity.Student;
import com.personal.Kafka_project.KafkaProducer.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/")
public class ServiceController {

    @Autowired
    KafkaMessagePublisher kafkaMessagePublisher;
    @GetMapping("sendMessage/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message){
        Boolean status = false;
        for(int i=0;i<1000;i++){
            status = kafkaMessagePublisher.sendMessageToTopic(message);
        }
        System.out.println(status);
      if(status) {

          return new ResponseEntity<>("Message published successfully", HttpStatus.OK);
      }
      else {
          return new ResponseEntity<>("Some error occurred",HttpStatus.BAD_REQUEST);
      }
    }
    @GetMapping("showResult")
    public ResponseEntity<String> checkResult(@RequestBody Student student){
        for(int i =0;i<1000;i++){
            student = kafkaMessagePublisher.sendMessageToStudentTopic(student,i);
        }
        return new ResponseEntity<>("Message Published Successfully",HttpStatus.OK);
    }
}
