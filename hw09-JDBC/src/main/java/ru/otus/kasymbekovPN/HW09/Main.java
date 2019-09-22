package ru.otus.kasymbekovPN.HW09;

import ru.otus.kasymbekovPN.HW09.api.service.DBServiceImpl;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.h2.DataSourceH2Impl;
import ru.otus.kasymbekovPN.HW09.jdbc.dao.UserDao;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorImpl;
import ru.otus.kasymbekovPN.HW09.jdbc.sessionManager.SessionManagerJDBCImpl;

public class Main {
    public static void main(String[] args) {
        //<------------ in factory method
        SessionManager sessionManager = new SessionManagerJDBCImpl(new DataSourceH2Impl());
        DBExecutorImpl<User> executor = new DBExecutorImpl<>();
        UserDao dao = new UserDao(sessionManager, executor);

        DBServiceImpl<User> service = new DBServiceImpl<>(dao);
        //< -------------

        User user1 = new User("user1", 20);
        service.createRecord(user1);
    }
}
