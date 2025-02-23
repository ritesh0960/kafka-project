package com.personal.Kafka_project.KafkaConsumer;

import com.personal.Kafka_project.Entity.Student;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {

    @KafkaListener(topics = "kafkaTopic",groupId = "practiceGroup")
    public void consumer(String message){

        System.out.println("Listening the message from consumer: " + message);
    }

    //If the average marks of any student is less than 60 then, Mark it as fail otherwise pass

    @KafkaListener(topics = "studentTopic",groupId = "practiceGroup")
    public void studentConsumer(Student student){
         Integer[] marks = student.getMarks();
         Integer average = 0;
         Integer totalMarks = 0;
         for (int i=0;i<marks.length;i++){
            totalMarks+=marks[i];
         }
         average = totalMarks/5;
         if(average<60){
             System.out.println("***********************************************************");
             System.out.println("**********   "+student.getName()+"  ************************");
             System.out.println("Marks : " + marks[0] + " " +marks[1] + " " +marks[2] + " " +marks[3]+ " "+marks[4]);
             System.out.println("Average Marks = "+average);
             System.out.println("FAILED!!!!");
         }else {
             System.out.println("***********************************************************");
             System.out.println("**********   "+student.getName()+"  ************************");
             System.out.println("Marks : " + marks[0] + " " +marks[1] + " " +marks[2] + " " +marks[3]+ " "+marks[4]);
             System.out.println("Average Marks = "+average);
             System.out.println("PASSED!!!!");
         }
    }


}
