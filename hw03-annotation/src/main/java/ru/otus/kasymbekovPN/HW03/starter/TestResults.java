package ru.otus.kasymbekovPN.HW03.starter;

import java.util.HashMap;
import java.util.Map;

class TestResults {
    private Map<String, Boolean> results;

    TestResults(){
        results = new HashMap<>();
    }

    void put(String name, boolean result){
        results.put(name, result);
    }

    void print(){
        int count = 0;

        System.out.println("\nResults");
        for (Map.Entry<String, Boolean> entry : results.entrySet()) {
            System.out.println(entry.getValue() + "\t : " + entry.getKey());
            if (entry.getValue()){
                count++;
            }
        }
        System.out.println("\nDone : " + count + "/" + results.size());
    }
}
