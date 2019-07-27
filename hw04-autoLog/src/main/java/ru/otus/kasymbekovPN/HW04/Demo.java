package ru.otus.kasymbekovPN.HW04;

import ru.otus.kasymbekovPN.HW04.accumulator.Accumulator;

/*
java -javaagent:hw04.jar -jar hw04.jar
 */
public class Demo {
    public static void main(String... args) {
        //<
        System.out.println("main");
        //<

        Accumulator accumulator = new Accumulator(100.0);
        System.out.println("main : " + accumulator.add(150.0));
        System.out.println("main : " + accumulator.sud(70.0));
        System.out.println("main : " + accumulator.prod(15.2));
        System.out.println("main : " + accumulator.div(-12.586));
        System.out.println("main : " + accumulator.getValue());
    }
}
