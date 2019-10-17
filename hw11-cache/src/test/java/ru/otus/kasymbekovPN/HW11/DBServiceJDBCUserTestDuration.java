package ru.otus.kasymbekovPN.HW11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoUser;
import ru.otus.kasymbekovPN.HW09.api.service.DBServiceUser;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.User;
import ru.otus.kasymbekovPN.HW09.h2.DataSourceH2;
import ru.otus.kasymbekovPN.HW09.jdbc.dao.DaoJDBCUser;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorJDBC;
import ru.otus.kasymbekovPN.HW09.jdbc.sessionManager.SessionManagerJDBC;
import ru.otus.kasymbekovPN.HW11.cache.CacheImpl;
import ru.otus.kasymbekovPN.HW11.jdbc.DBServiceCacheUser;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing of DBServiceJDBCUserTest")
class DBServiceJDBCUserTestDuration {

    private static Object[][] getDataForTestLoadingDuration(){
        return new Object[][]{
                {1, 300}
        };
    }

    @DisplayName("Testing of loading duration")
    @ParameterizedTest
    @MethodSource("getDataForTestLoadingDuration")
    void testLoadingDuration(int first, int number){
        SessionManager sessionManager = new SessionManagerJDBC(new DataSourceH2());
        DBExecutorJDBC<User> executor = new DBExecutorJDBC<>();
        DaoUser dao = new DaoJDBCUser(sessionManager, executor);
        CacheImpl<Long, User> cache = new CacheImpl<>();
        DBServiceUser service = new DBServiceCacheUser(cache, dao);

        for (int i = first; i <= number; i++){
            service.createRecord(new User("user_" + i, i));
        }

        cache.clear();

        long timestampWithoutCacheBegin = System.currentTimeMillis();
        for (int i = first; i <= number; i++){
            service.loadRecord(i);
        }
        long durationWithoutCache = System.currentTimeMillis() - timestampWithoutCacheBegin;
        int cache0 = cache.size();

        long timestampWithCacheBegin = System.currentTimeMillis();
        for (int i = first; i <= number; i++){
            service.loadRecord(i);
        }
        long durationWithCache = System.currentTimeMillis() - timestampWithCacheBegin;
        int cache1 = cache.size();

        assertThat(cache0).isEqualTo(cache1);
        assertThat(durationWithoutCache).isGreaterThan(durationWithCache);
    }
}
