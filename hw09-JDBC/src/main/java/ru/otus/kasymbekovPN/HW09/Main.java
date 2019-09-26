package ru.otus.kasymbekovPN.HW09;

import ru.otus.kasymbekovPN.HW09.api.service.DBServiceImpl;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.h2.DataSourceH2Impl;
import ru.otus.kasymbekovPN.HW09.jdbc.dao.UserDao;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorImpl;
import ru.otus.kasymbekovPN.HW09.jdbc.sessionManager.SessionManagerJDBCImpl;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //<------------ in factory method
        SessionManager sessionManager = new SessionManagerJDBCImpl(new DataSourceH2Impl());
        DBExecutorImpl<User> executor = new DBExecutorImpl<>();

        final UserDao<User> dao = new UserDao<>(sessionManager, executor);
        DBServiceImpl<User> service = new DBServiceImpl<>(dao);
        //< -------------

        User user1 = new User("user1", 20);
        service.createRecord(user1);
        System.out.println(user1);

        Optional<User> luser1 = service.loadRecord(1, new User());
        System.out.println("luser1");
        System.out.println(luser1);

//        User user2 = new User("user2", 21);
//        service.createRecord(user2);
//        System.out.println(user2);
    }
}
