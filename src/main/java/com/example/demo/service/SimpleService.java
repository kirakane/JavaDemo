package com.example.demo.service;

import com.example.demo.models.Greeting;
import com.example.demo.repository.GreetingRepo;
import im.aop.loggers.Level;
import im.aop.loggers.advice.around.LogAround;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SimpleService {

    @Autowired
    private GreetingRepo greetingRepo;

    @LogAround(level = Level.INFO)
    @WithSpan
    public String greet(String name){
        String greetingString = "Hello "+name;
        Greeting greeting = new Greeting();
        greeting.setGreetingValue(greetingString);
        greetingRepo.save(greeting);
        return greetingString;
    }

    @LogAround(level = Level.INFO)
    @WithSpan
    public List<String> listGreetings(){
        List<Greeting>  list = greetingRepo.findAll();
        List<String> returnList = new ArrayList<>();
        for (Greeting greet: list){
            returnList.add(greet.toString());
        }
        return returnList;
    }
}
