package com.personal.Kafka_project.KafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
@Service
public class KafkaMessagePublisher {
    @Autowired
    private KafkaTemplate<String,String> template;

    public Boolean sendMessageToTopic(String message){
        AtomicReference<Boolean> status = new AtomicReference<>(false);
        CompletableFuture<SendResult<String, String>> future = template.send("kafkaTopic", message);
        future.whenComplete((result,exception)->{
            if(exception == null){
                System.out.println("Sent message = [ "+message+" ] with offset = [ "+result.getRecordMetadata().offset()+" ]");
                status.set(true);

            }else{
                System.out.println("Some error has occurred while publishing the message to topic = "+result.getRecordMetadata().topic());
            }
        });

      return status.get();
    }
}
