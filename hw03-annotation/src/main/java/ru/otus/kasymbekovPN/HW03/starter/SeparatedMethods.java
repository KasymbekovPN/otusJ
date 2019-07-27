package ru.otus.kasymbekovPN.HW03.starter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SeparatedMethods {
    private Map<Class, List<Method>> methods;

    SeparatedMethods() {
        methods = new HashMap<>();
    }

    void put(Class key, Method method){
        if (!methods.containsKey(key)){
            methods.put(key, new ArrayList<>());
        }
        methods.get(key).add(method);
    }

    List<Method> getMethodList(Class key){
        return methods.containsKey(key)
                ? methods.get(key)
                : new ArrayList<>();
    }
}
