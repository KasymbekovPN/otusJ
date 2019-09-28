package ru.otus.kasymbekovPN.HW09;

import ru.otus.kasymbekovPN.HW09.dataClass.Account;
import ru.otus.kasymbekovPN.HW09.dataClass.UnrecEntity;
import ru.otus.kasymbekovPN.HW09.dataClass.User;
import ru.otus.kasymbekovPN.HW09.jdbc.service.DBServiceJDBC;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.h2.DataSourceH2;
import ru.otus.kasymbekovPN.HW09.jdbc.dao.DaoJDBC;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorJDBC;
import ru.otus.kasymbekovPN.HW09.jdbc.sessionManager.SessionManagerJDBC;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
//        //<------------ in factory method
//        SessionManager sessionManager = new SessionManagerJDBC(new DataSourceH2());
//        DBExecutorJDBC<User> executor = new DBExecutorJDBC<>();
//
//        DaoJDBC<User> dao = new DaoJDBC<>(sessionManager, executor);
//        DBServiceJDBC<User> service = new DBServiceJDBC<>(dao);
//        //< -------------
//
//        User user1 = new User("user1", 20);
//        service.createRecord(user1);
//        System.out.println(user1);
//
//        Optional<User> luser1 = service.loadRecord(1, new User());
//        System.out.println("luser1");
//        System.out.println(luser1);
//
//        user1.setAge(33);
//        user1.setName("User11111");
//        service.updateRecord(user1);
//
//        Optional<User> luser2 = service.loadRecord(1, new User());
//        System.out.println("luser2");
//        System.out.println(luser2);
//
//        //<------------ in factory method
//        SessionManager sessionManager1 = new SessionManagerJDBC(new DataSourceH2());
//        DBExecutorJDBC<Account> executor1 = new DBExecutorJDBC<>();
//
//        DaoJDBC<Account> dao1 = new DaoJDBC<>(sessionManager1, executor1);
//        DBServiceJDBC<Account> service1 = new DBServiceJDBC<>(dao1);
//        //< -------------
//
//        Account acc1 = new Account("simple", 123.456);
//        service1.createRecord(acc1);
//        System.out.println(acc1);
//
//        Account lacc1 = new Account();
//        service1.loadRecord(1, lacc1);
//        System.out.println(lacc1);
//
//        acc1.setRest(231);
//        acc1.setType("noSimple");
//        service1.updateRecord(acc1);
//        System.out.println(acc1);
//
//        Account lacc2 = new Account();
//        service1.loadRecord(1, lacc2);
//        System.out.println(lacc2);

//        //<------------ in factory method
//        SessionManager sessionManager2 = new SessionManagerJDBC(new DataSourceH2());
//        DBExecutorJDBC<UnrecEntity> executor1 = new DBExecutorJDBC<>();
//
//        DaoJDBC<UnrecEntity> dao2 = new DaoJDBC<>(sessionManager2, executor1);
//        DBServiceJDBC<UnrecEntity> service2 = new DBServiceJDBC<>(dao2);
//        //< -------------
//
//        UnrecEntity unrec = new UnrecEntity("string");
//        Optional<UnrecEntity> record = service2.createRecord(unrec);
//        System.out.println(record);

//        Account acc1 = new Account("simple", 123.456);
//        service1.createRecord(acc1);
//        System.out.println(acc1);
//

    }
}
