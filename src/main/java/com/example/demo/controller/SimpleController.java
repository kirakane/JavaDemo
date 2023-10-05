package com.example.demo.controller;


import com.example.demo.exception.BadRequestException;
import com.example.demo.service.SimpleService;
import im.aop.loggers.Level;
import im.aop.loggers.advice.around.LogAround;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class SimpleController {

    @Autowired
    SimpleService service;

    @LogAround(level = Level.INFO)
    @GetMapping("/hello")
    public List<String> listGreetings(){
        List<String> ret = service.listGreetings();
        return ret;
    }

    @LogAround(level = Level.INFO)
    @PostMapping("/hello/{greeting}")
    public String greet(@PathVariable String greeting){
        String ret = service.greet( greeting +" "+System.currentTimeMillis());
        return ret;
    }

    @RequestMapping(value = "/responseBody", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> responseBody(@RequestParam String value){
        if (value.equals("foo")){
            return ResponseEntity.ok("you sent foo");
        } else {
            throw new BadRequestException("You did not send foo");
        }
    }
}
