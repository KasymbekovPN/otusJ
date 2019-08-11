package ru.otus.kasymbekovPN.HW06.utils;

public class SimplePair<E1, E2> {
    private E1 first;
    private E2 second;

    public E1 getFirst() {
        return first;
    }

    public E2 getSecond() {
        return second;
    }

    public SimplePair(E1 e1, E2 e2){
        this.first = e1;
        this.second = e2;
    }
}
