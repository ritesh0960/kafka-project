package com.personal.Kafka_project.KafkaProducer;

import com.personal.Kafka_project.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class KafkaMessagePublisher {
    @Autowired
    private KafkaTemplate<String,String> template;

    @Autowired
    private KafkaTemplate<String,Student> studentKafkaTemplate;

    public Boolean sendMessageToTopic(String message){
        AtomicBoolean status = new AtomicBoolean(true);
        try {
            CompletableFuture<SendResult<String, String>> future = template.send("kafkaTopic", message);
            future.whenComplete((result,exception)->{
                if(exception == null){
                    System.out.println("Sent message = [ "+message+" ] with offset = [ "+result.getRecordMetadata().offset()+" ]");
                    status.set(true);

                }else{
                    System.out.println("Some error has occurred while publishing the message to topic = "+result.getRecordMetadata().topic());
                }
            });
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            status.set(false);
        }
        return status.get();
    }

    public Student sendMessageToStudentTopic(Student student,int i){
        Student tempStudent = new Student();
        tempStudent.setName(student.getName()+" "+i);
        tempStudent.setMobNo(student.getMobNo());
        List<Integer> listOfMarks = Arrays.stream(student.getMarks()).toList();
        List<Integer> modifidMarksList = listOfMarks.stream().map(mark -> mark + (int)(Math.random() * 10 - 1)).collect(Collectors.toList());
        Integer[] modifiedMarks = new Integer[listOfMarks.size()];
        for(int j=0;j<listOfMarks.size();j++){
            modifiedMarks[j] =  modifidMarksList.get(j);
        }
        tempStudent.setMarks(modifiedMarks);
        studentKafkaTemplate.send("studentTopic",tempStudent);
        return student;
    }
}
