package ru.otus.kasymbekovPN.HW10;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.UserDao;
import ru.otus.kasymbekovPN.HW10.api.model.AddressDataSet;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.model.PhoneDataSet;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceUser;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceUserImpl;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW10.hibernate.HibernateUtils;
import ru.otus.kasymbekovPN.HW10.hibernate.dao.UserDaoHibernate;
import ru.otus.kasymbekovPN.HW10.hibernate.sessionManager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DisplayName("Testing of DBServiceUserImplTest")
class DBServiceDBUserImplTest {

    static private Logger logger = LoggerFactory.getLogger(DBServiceDBUserImplTest.class);

    @DisplayName("test class User")
    @Test
    void testUser(){
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate-test.cfg.xml",
                DBUser.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManager sessionManager = new SessionManagerHibernate(sessionFactory);

        UserDao dao = new UserDaoHibernate(sessionManager);

        DBServiceUser service = new DBServiceUserImpl(dao);

        AddressDataSet addressDataSet = new AddressDataSet(0, "my street");

        List<PhoneDataSet> phones = new ArrayList<>(){{
            add(new PhoneDataSet(0, "123"));
            add(new PhoneDataSet(0, "456"));
            add(new PhoneDataSet(0, "789"));
        }};

        long id = service.saveUser(new DBUser(0, "Pavel", 30, addressDataSet, phones));

        Optional<DBUser> userOpt = service.getUser(id);
        outputUserOptional("1)", userOpt);

        id = service.saveUser(new DBUser(1L, "Pavel K", 31, addressDataSet, phones));
        userOpt = service.getUser(id);
        outputUserOptional("2)", userOpt);
    }


    private static void outputUserOptional(String header, Optional<DBUser> userOpt){
        System.out.println(header);
        userOpt.ifPresentOrElse(System.out::println, () ->{logger.info("User not found");});
    }
}
