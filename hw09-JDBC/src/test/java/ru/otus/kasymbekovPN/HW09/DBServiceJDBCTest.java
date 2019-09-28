package ru.otus.kasymbekovPN.HW09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.Account;
import ru.otus.kasymbekovPN.HW09.dataClass.UnrecEntity;
import ru.otus.kasymbekovPN.HW09.dataClass.User;
import ru.otus.kasymbekovPN.HW09.h2.DataSourceH2;
import ru.otus.kasymbekovPN.HW09.jdbc.dao.DaoJDBC;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorJDBC;
import ru.otus.kasymbekovPN.HW09.jdbc.service.DBServiceJDBC;
import ru.otus.kasymbekovPN.HW09.jdbc.sessionManager.SessionManagerJDBC;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing of DBServiceJDBC")
class DBServiceJDBCTest {

    @DisplayName("class User")
    @Test
    void testUser(){
        SessionManager sessionManager = new SessionManagerJDBC(new DataSourceH2());
        DBExecutorJDBC<User> executor = new DBExecutorJDBC<>();
        DaoJDBC<User> dao = new DaoJDBC<>(sessionManager, executor);
        DBServiceJDBC<User> service = new DBServiceJDBC<>(dao);

        User u1 = new User("user_1", 20);
        Optional<User> record = service.createRecord(u1);
        Optional<User> loaded_u = service.loadRecord(1, new User());
        assertThat(record).isPresent();
        assertThat(loaded_u).isPresent();
        assertThat(loaded_u.get()).isEqualTo(record.get());

        u1.setAge(21);
        u1.setName("new u1 name");
        record = service.updateRecord(u1);
        loaded_u = service.loadRecord(1, new User());
        assertThat(record).isPresent();
        assertThat(loaded_u).isPresent();
        assertThat(loaded_u.get()).isEqualTo(u1);
    }

    @DisplayName("class Account")
    @Test
    void testAccount(){
        SessionManager sessionManager = new SessionManagerJDBC(new DataSourceH2());
        DBExecutorJDBC<Account> executor = new DBExecutorJDBC<>();
        DaoJDBC<Account> dao = new DaoJDBC<>(sessionManager, executor);
        DBServiceJDBC<Account> service = new DBServiceJDBC<>(dao);

        Account acc = new Account("simple", 123.456);
        Optional<Account> record = service.createRecord(acc);
        Optional<Account> loaded_acc = service.loadRecord(1, new Account());
        assertThat(record).isPresent();
        assertThat(loaded_acc).isPresent();
        assertThat(loaded_acc.get()).isEqualTo(record.get());

        acc.setType("noSimple");
        acc.setRest(456.789);
        service.updateRecord(acc);
        record = loaded_acc = service.loadRecord(1, new Account());
        assertThat(record).isPresent();
        assertThat(loaded_acc).isPresent();
        assertThat(loaded_acc.get()).isEqualTo(record.get());
    }

    @DisplayName("class UnrecEntity")
    @Test
    void testUnrecEntity(){
        SessionManager sessionManager = new SessionManagerJDBC(new DataSourceH2());
        DBExecutorJDBC<UnrecEntity> executor = new DBExecutorJDBC<>();
        DaoJDBC<UnrecEntity> dao = new DaoJDBC<>(sessionManager, executor);
        DBServiceJDBC<UnrecEntity> service = new DBServiceJDBC<>(dao);

        UnrecEntity unrecEntity = new UnrecEntity("hello");
        Optional<UnrecEntity> record = service.createRecord(unrecEntity);
        assertThat(record).isEmpty();
    }
}
