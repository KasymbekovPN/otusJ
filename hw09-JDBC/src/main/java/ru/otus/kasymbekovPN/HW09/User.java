package ru.otus.kasymbekovPN.HW09;

public class User {

    //< задавать через рефлексию
    @Id
    private long id = -1;

    private String name;

    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
