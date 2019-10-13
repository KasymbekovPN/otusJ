package ru.otus.kasymbekovPN.HW10;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.kasymbekovPN.HW10.api.dao.DaoDBUser;
import ru.otus.kasymbekovPN.HW10.api.model.AddressDataSet;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.model.PhoneDataSet;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceDBUserImpl;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceDBUser;
import ru.otus.kasymbekovPN.HW10.hibernate.HibernateUtils;
import ru.otus.kasymbekovPN.HW10.hibernate.dao.DaoDBUserHibernate;
import ru.otus.kasymbekovPN.HW10.hibernate.sessionManager.SessionManagerHibernate;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing of DBServiceUserImplTest")
class DBServiceDBUserImplTest {

    @DisplayName("test class User")
    @Test
    void testUser(){

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate-test.cfg.xml",
                DBUser.class, AddressDataSet.class, PhoneDataSet.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        DaoDBUser dao = new DaoDBUserHibernate(sessionManager);
        DBServiceDBUser service = new DBServiceDBUserImpl(dao);

        AddressDataSet addressDataSet = new AddressDataSet(0, "my street");
        List<PhoneDataSet> phones = new ArrayList<>(){{
            add(new PhoneDataSet(0, "123-45-67"));
            add(new PhoneDataSet(0, "234-56-78"));
            add(new PhoneDataSet(0, "345-67-89"));
        }};

        DBUser insertedUser = new DBUser(0, "Pavel", 30, addressDataSet, phones);
        boolean success = service.createRecord(insertedUser);
        assertThat(success).isTrue();

        Optional<DBUser> selectedRecOpt = service.loadRecord(insertedUser.getId());
        assertThat(selectedRecOpt).isPresent();
        assertThat(selectedRecOpt.get()).isEqualTo(insertedUser);

        DBUser updatedUser = insertedUser;
        updatedUser.setName("Pavel K");
        success = service.updateRecord(updatedUser);
        assertThat(success).isTrue();

        selectedRecOpt = service.loadRecord(updatedUser.getId());
        assertThat(selectedRecOpt).isPresent();
        assertThat(selectedRecOpt.get()).isEqualTo(updatedUser);
    }
}
