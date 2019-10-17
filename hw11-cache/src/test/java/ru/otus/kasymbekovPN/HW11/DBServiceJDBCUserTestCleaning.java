package ru.otus.kasymbekovPN.HW11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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

import static org.assertj.core.api.Assertions.*;

/*
    run test with -Xms128m -Xmx128m
 */
@DisplayName("Testing of DBServiceJDBCUserTest")
class DBServiceJDBCUserTestCleaning {

    private final static Logger logger = LoggerFactory.getLogger(DBServiceJDBCUserTestCleaning.class);

    private static Object[][] getDataForTestWeakMap(){
        return new Object[][]{
                {1, 5_000}
        };
    }

    @DisplayName("Testing of cleaning of cache data")
    @ParameterizedTest
    @MethodSource("getDataForTestWeakMap")
    void testWeakMap(int first, int number){
        SessionManager sessionManager = new SessionManagerJDBC(new DataSourceH2());
        DBExecutorJDBC<User> executor = new DBExecutorJDBC<>();
        DaoUser dao = new DaoJDBCUser(sessionManager, executor);

        CacheImpl<Long, User> cache = new CacheImpl<>();
        CacheListener<Long, User> listener = (key, value, action) -> {
            logger.info("key : {}, value : {}, action : {}", key, value, action);
        };
        cache.subscribeListener(listener);
        DBServiceUser service = new DBServiceCacheUser(cache, dao);

        for (int i = first; i <= number; i++){
            service.createRecord(new User("user_" + i, i));
        }
        assertThat(cache.size()).isLessThan(number);
    }
}

