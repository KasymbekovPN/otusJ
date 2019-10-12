package ru.otus.kasymbekovPN.HW11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoUser;
import ru.otus.kasymbekovPN.HW09.api.service.DBServiceUser;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.User;
import ru.otus.kasymbekovPN.HW09.h2.DataSourceH2;
import ru.otus.kasymbekovPN.HW09.jdbc.dao.DaoJDBCUser;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorJDBC;
import ru.otus.kasymbekovPN.HW09.jdbc.sessionManager.SessionManagerJDBC;
import ru.otus.kasymbekovPN.HW11.cache.CacheImpl;
import ru.otus.kasymbekovPN.HW11.cache.CacheListener;
import ru.otus.kasymbekovPN.HW11.jdbc.DBServiceCacheUser;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing of DBServiceJDBCUserTest")
class DBServiceJDBCUserTest {

    static private Logger logger = LoggerFactory.getLogger(DBServiceCacheUser.class);

    @DisplayName("Testing of loading duration")
    @Test
    void test(){
        SessionManager sessionManager = new SessionManagerJDBC(new DataSourceH2());
        DBExecutorJDBC<User> executor = new DBExecutorJDBC<>();
        DaoUser dao = new DaoJDBCUser(sessionManager, executor);

        CacheImpl<Long, User> cache = new CacheImpl<>();
        CacheListener<Long, User> listener = (key, value, action) -> {
            logger.info("key : {}, value : {}, action : {}", key, value, action);
        };
        cache.subscribeListener(listener);
        DBServiceUser service = new DBServiceCacheUser(cache, dao);

        User u1 = new User("user_1", 20);
        Optional<User> record = service.createRecord(u1);

        long timestamp1 = System.currentTimeMillis();

        Optional<User> loaded_u = service.loadRecord(1);
        assertThat(record).isPresent();
        assertThat(loaded_u).isPresent();
        assertThat(loaded_u.get()).isEqualTo(record.get());

        long timestamp2 = System.currentTimeMillis();

        loaded_u = service.loadRecord(1);
        assertThat(record).isPresent();
        assertThat(loaded_u).isPresent();
        assertThat(loaded_u.get()).isEqualTo(record.get());

        long timestamp3 = System.currentTimeMillis();

        long durationWithoutCache = timestamp2 - timestamp1;
        long durationWithCache = timestamp3 - timestamp2;
        logger.info("Loading duration without cache : {} ms", durationWithoutCache);
        logger.info("Loading duration with cache : {} ms", durationWithCache);
        assertThat(durationWithoutCache).isGreaterThan(durationWithCache);
    }
}
