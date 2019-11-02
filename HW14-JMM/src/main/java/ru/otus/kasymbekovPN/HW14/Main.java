package ru.otus.kasymbekovPN.HW14;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SawtoothCounter sawtoothCounter = new SawtoothCounter(1, 10, 2);
        sawtoothCounter.start();
    }
}
