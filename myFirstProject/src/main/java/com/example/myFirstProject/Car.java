package com.example.myFirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Car
{
    //When we want to use another class in another class then we will write @Autowired.
    //Straightforward meaning is that by using this we can access beans from the IOC container.
    @Autowired
    private Dog dog;

    @GetMapping("/ok")
    public String ok()
    {
       return dog.fun();
    }
}
