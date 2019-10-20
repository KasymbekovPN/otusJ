package ru.otus.kasymbekovPN.HW12.user;

public class User_ {

    private final String name;
    private final String password;

    public User_(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
