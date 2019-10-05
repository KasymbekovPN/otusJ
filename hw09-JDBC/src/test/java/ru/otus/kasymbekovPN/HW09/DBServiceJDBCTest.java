package ru.otus.kasymbekovPN.HW09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoAccount;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoUser;
import ru.otus.kasymbekovPN.HW09.api.service.DBServiceAccount;
import ru.otus.kasymbekovPN.HW09.api.service.DBServiceUser;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.Account;
import ru.otus.kasymbekovPN.HW09.dataClass.User;
import ru.otus.kasymbekovPN.HW09.h2.DataSourceH2;
import ru.otus.kasymbekovPN.HW09.jdbc.dao.DaoJDBCAccount;
import ru.otus.kasymbekovPN.HW09.jdbc.dao.DaoJDBCUser;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorJDBC;
import ru.otus.kasymbekovPN.HW09.jdbc.service.DBServiceJDBCAccount;
import ru.otus.kasymbekovPN.HW09.jdbc.service.DBServiceJDBCUser;
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
        DaoUser dao = new DaoJDBCUser(sessionManager, executor);
        DBServiceUser service = new DBServiceJDBCUser(dao);

        User u1 = new User("user_1", 20);
        Optional<User> record = service.createRecord(u1);
        Optional<User> loaded_u = service.loadRecord(1);
        assertThat(record).isPresent();
        assertThat(loaded_u).isPresent();
        assertThat(loaded_u.get()).isEqualTo(record.get());

        u1.setAge(21);
        u1.setName("new u1 name");
        record = service.updateRecord(u1);
        loaded_u = service.loadRecord(1);
        assertThat(record).isPresent();
        assertThat(loaded_u).isPresent();
        assertThat(loaded_u.get()).isEqualTo(u1);
    }

    @DisplayName("class Account")
    @Test
    void testAccount(){
        SessionManager sessionManager = new SessionManagerJDBC(new DataSourceH2());
        DBExecutorJDBC<Account> executor = new DBExecutorJDBC<>();
        DaoAccount dao = new DaoJDBCAccount(sessionManager, executor);
        DBServiceAccount service = new DBServiceJDBCAccount(dao);

        Account acc = new Account("simple", 123.456);
        Optional<Account> record = service.createRecord(acc);
        Optional<Account> loaded_acc = service.loadRecord(1);
        assertThat(record).isPresent();
        assertThat(loaded_acc).isPresent();
        assertThat(loaded_acc.get()).isEqualTo(record.get());

        acc.setType("noSimple");
        acc.setRest(456.789);
        service.updateRecord(acc);
        record = loaded_acc = service.loadRecord(1);
        assertThat(record).isPresent();
        assertThat(loaded_acc).isPresent();
        assertThat(loaded_acc.get()).isEqualTo(record.get());
    }
}
