package ru.otus.kasymbekovPN.HW15.hello;

import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    public Greeting greeting(HelloMessage message) throws Exception{
        Thread.sleep(1_000);
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
