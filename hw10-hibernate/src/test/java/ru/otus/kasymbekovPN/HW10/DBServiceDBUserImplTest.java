package ru.otus.kasymbekovPN.HW10;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.DBUserDao;
import ru.otus.kasymbekovPN.HW10.api.model.AddressDataSet;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.model.PhoneDataSet;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceDBUser;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceDBUserImpl;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW10.hibernate.HibernateUtils;
import ru.otus.kasymbekovPN.HW10.hibernate.dao.DBUserDaoHibernate;
import ru.otus.kasymbekovPN.HW10.hibernate.sessionManager.SessionManagerHibernate;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing of DBServiceUserImplTest")
class DBServiceDBUserImplTest {

    static private Logger logger = LoggerFactory.getLogger(DBServiceDBUserImplTest.class);

    @DisplayName("test class User")
    @Test
    void testUser(){

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate-test.cfg.xml",
                DBUser.class, AddressDataSet.class, PhoneDataSet.class);
        SessionManager sessionManager = new SessionManagerHibernate(sessionFactory);
        DBUserDao dao = new DBUserDaoHibernate(sessionManager);
        DBServiceDBUser service = new DBServiceDBUserImpl(dao);

        AddressDataSet addressDataSet = new AddressDataSet(0, "my street");
        List<PhoneDataSet> phones = new ArrayList<>(){{
            add(new PhoneDataSet(0, "123-45-67"));
            add(new PhoneDataSet(0, "234-56-78"));
            add(new PhoneDataSet(0, "345-67-89"));
        }};

        DBUser insertedUser = new DBUser(0, "Pavel", 30, addressDataSet, phones);
        long userId = service.saveUser(insertedUser);
        Optional<DBUser> selectUserOpt = service.getUser(userId);

        assertThat(selectUserOpt).isPresent();
        System.out.println("insert : " + insertedUser);
        System.out.println("select : " + selectUserOpt.get());
        assertThat(selectUserOpt.get()).isEqualTo(insertedUser);

        insertedUser.setName("Pavel K");
        userId = service.saveUser(insertedUser);
        selectUserOpt = service.getUser(userId);

        assertThat(selectUserOpt).isPresent();
        System.out.println("insert : " + insertedUser);
        System.out.println("select : " + selectUserOpt.get());
        assertThat(selectUserOpt.get()).isEqualTo(insertedUser);
    }


    private static void outputUserOptional(String header, Optional<DBUser> userOpt){
        System.out.println(header);
        userOpt.ifPresentOrElse(System.out::println, () ->{logger.info("User not found");});
    }
}
