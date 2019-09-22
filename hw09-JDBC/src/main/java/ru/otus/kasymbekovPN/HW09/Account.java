package ru.otus.kasymbekovPN.HW09;

public class Account {

    //< задавать значение через рефлексию
    @Id
    private long no;

    private String type;

    private double rest;

    public Account(String type, double rest) {
        this.type = type;
        this.rest = rest;
    }
}
