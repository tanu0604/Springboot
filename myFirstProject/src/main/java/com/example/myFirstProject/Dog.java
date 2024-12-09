package com.example.myFirstProject;

import org.springframework.stereotype.Component;

//sent this class to the IOC container as an object/bean by adding @Component annotation.
@Component
public class Dog
{
    public String fun()
    {
        return "something";
    }
}
