package ru.otus.kasymbekovPN.HW13.controllers;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.kasymbekovPN.HW13.services.GreetingService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GreetingRestController {

    GreetingService greetingService;

    //http://localhost:8080/DIhello/hello?name=ddd
    @GetMapping("/hello")
    @ResponseBody
    public String sayHelloSchoolWithGsonMapping(@RequestParam String name){
        return new Gson().toJson(greetingService.sayHello(name));
    }

    //http://localhost:8080/hello/jone
    @GetMapping("/hello/{name}")
    public Map<String, String> sayHelloRestApiStyleWithJacksonMapping(@PathVariable String name){
        return greetingService.sayHello(name);
    }
}
