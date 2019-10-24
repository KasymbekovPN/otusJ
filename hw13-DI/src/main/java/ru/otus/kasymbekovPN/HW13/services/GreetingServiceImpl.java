package ru.otus.kasymbekovPN.HW13.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public Map<String, String> sayHello(String name) {
        var map = new HashMap<String, String>();
        map.put(name, "Hello, " + name);
        return map;
    }
}
