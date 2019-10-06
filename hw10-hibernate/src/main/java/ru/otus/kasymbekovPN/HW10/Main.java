package ru.otus.kasymbekovPN.HW10;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.UserDao_;
import ru.otus.kasymbekovPN.HW10.api.model.User_;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceUser_;
import ru.otus.kasymbekovPN.HW10.api.service.DbServiceUserImpl_;
import ru.otus.kasymbekovPN.HW10.hibernate.HibernateUtils_;
import ru.otus.kasymbekovPN.HW10.hibernate.dao.UserDaoHibernate_;
import ru.otus.kasymbekovPN.HW10.hibernate.sessionManager.SessionManagerHibernate_;

import java.util.Optional;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // Все главное см в тестах
        SessionFactory sessionFactory = HibernateUtils_.buildSessionFactory("hibernate-test.cfg.xml", User_.class);

        SessionManagerHibernate_ sessionManager = new SessionManagerHibernate_(sessionFactory);
        UserDao_ userDao = new UserDaoHibernate_(sessionManager);
        DBServiceUser_ dbServiceUser = new DbServiceUserImpl_(userDao);

        long id = dbServiceUser.saveUser(new User_(0, "Вася"));
        Optional<User_> mayBeCreatedUser = dbServiceUser.getUser(id);

        id = dbServiceUser.saveUser(new User_(1L, "А! Нет. Это же совсем не Вася"));
        Optional<User_> mayBeUpdatedUser = dbServiceUser.getUser(id);

        outputUserOptional("Created user", mayBeCreatedUser);
        outputUserOptional("Updated user", mayBeUpdatedUser);
    }

    private static void outputUserOptional(String header, Optional<User_> mayBeUser) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(header);
        mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("User not found"));
    }
}
