package ru.otus.kasymbekovPN.HW12.user;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDao_ implements UserDao_ {

    private final Map<String, User_> users;

    public InMemoryUserDao_() {
        users = new HashMap<>();
        users.put("John", new User_("John", "qwerty"));
    }

    @Override
    public User_ save(User_ user) {
        return users.put(user.getName(), user);
    }

    @Override
    public User_ findByName(String name) {
        return users.get(name);
    }
}
