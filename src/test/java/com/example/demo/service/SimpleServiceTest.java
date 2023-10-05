package com.example.demo.service;

import com.example.demo.models.Greeting;
import com.example.demo.repository.GreetingRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SimpleServiceTest {

    @InjectMocks
    SimpleService service;

    @Mock
    GreetingRepo repo;

    @Test
    void greet() {
        String result = service.greet("lawrence");
        assertEquals("Hello lawrence", result);
    }

    @Test
    void listGreetings() {
        List<Greeting> list = new ArrayList<>();
        list.add(new Greeting(1l, "Foo1"));
        list.add(new Greeting(2l, "Foo2"));
        list.add(new Greeting(3l, "Foo3"));
        List<String> listTest = new ArrayList<>();
        for(Greeting greet: list){
            listTest.add (greet.toString());
        }

        when(repo.findAll()).thenReturn(list);
        assertEquals(listTest, service.listGreetings());
    }
}