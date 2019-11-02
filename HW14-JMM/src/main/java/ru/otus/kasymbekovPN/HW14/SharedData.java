package ru.otus.kasymbekovPN.HW14;

public interface SharedData {
    String waitedThread();
    void doIt();
    void addThreadName(String threadName);
    int getCounter();
}
