package ru.otus.kasymbekovPN.HW13.server.controllers;

import com.google.gson.Gson;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.kasymbekovPN.HW13.server.services.GreetingService;
//<
//import ru.otus.services.GreetingService;

@RestController
@RequiredArgsConstructor
public class GreetingRestController {

    GreetingService greetingService;

    //http://localhost:8080/DIhello/hello?name=ddd
    @GetMapping("/hello")
    @ResponseBody
    public String sayHelloOldSchoolWithGsonMapping(@RequestParam String name) {
        return new Gson().toJson(greetingService.sayHello(name));
    }

    //http://localhost:8080/hello/jone
    @GetMapping("/hello/{name}")
    public Map<String, String> sayHelloRestApiStyleWithJacksonMapping(@PathVariable String name) {
        return greetingService.sayHello(name);
    }

}


//<

//import com.google.gson.Gson;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import ru.otus.kasymbekovPN.HW13.server.services.GreetingService;
//
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//public class GreetingRestController {
//
//    GreetingService greetingService;
//
//    //http://localhost:8080/DIhello/hello?name=ddd
//    @GetMapping("/hello")
//    @ResponseBody
//    public String sayHelloSchoolWithGsonMapping(@RequestParam String name){
//        return new Gson().toJson(greetingService.sayHello(name));
//    }
//
//    //http://localhost:8080/hello/jone
//    @GetMapping("/hello/{name}")
//    public Map<String, String> sayHelloRestApiStyleWithJacksonMapping(@PathVariable String name){
//        return greetingService.sayHello(name);
//    }
//}
