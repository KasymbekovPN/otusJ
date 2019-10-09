package ru.otus.kasymbekovPN.HW10;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.DBUserDao;
import ru.otus.kasymbekovPN.HW10.api.dao.DaoDBUser_;
import ru.otus.kasymbekovPN.HW10.api.model.AddressDataSet;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.model.PhoneDataSet;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceDBUser;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceDBUserImpl;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceDBUserImpl_;
import ru.otus.kasymbekovPN.HW10.api.service.DBServiceDBUser_;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW10.hibernate.HibernateUtils;
import ru.otus.kasymbekovPN.HW10.hibernate.dao.DBUserDaoHibernate;
import ru.otus.kasymbekovPN.HW10.hibernate.dao.DaoDBUserHibernate_;
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
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        DaoDBUser_ dao = new DaoDBUserHibernate_(sessionManager);
        DBServiceDBUser_ service = new DBServiceDBUserImpl_(dao);

        AddressDataSet addressDataSet = new AddressDataSet(0, "my street");
        List<PhoneDataSet> phones = new ArrayList<>(){{
            add(new PhoneDataSet(0, "123-45-67"));
            add(new PhoneDataSet(0, "234-56-78"));
            add(new PhoneDataSet(0, "345-67-89"));
        }};

        DBUser insertedUser = new DBUser(0, "Pavel", 30, addressDataSet, phones);
        Optional<DBUser> insertedRecOpt = service.createRecord(insertedUser);
        assertThat(insertedRecOpt).isPresent();

        Optional<DBUser> selectedRecOpt = service.loadRecord(insertedRecOpt.get().getId());
        assertThat(selectedRecOpt).isPresent();
        assertThat(selectedRecOpt.get()).isEqualTo(insertedRecOpt.get());

        insertedRecOpt.get().setName("Pavel K");
        Optional<DBUser> updateRecordOpt = service.updateRecord(insertedRecOpt.get());
        assertThat(updateRecordOpt).isPresent();

        selectedRecOpt = service.loadRecord(insertedRecOpt.get().getId());
        assertThat(selectedRecOpt).isPresent();
        assertThat(selectedRecOpt.get()).isEqualTo(updateRecordOpt.get());
    }
}
