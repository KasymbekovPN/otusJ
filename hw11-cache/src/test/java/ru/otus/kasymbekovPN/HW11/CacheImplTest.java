package ru.otus.kasymbekovPN.HW11;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW11.cache.CacheImpl;
import ru.otus.kasymbekovPN.HW11.cache.CacheListener;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing of CacheImplTes")
class CacheImplTest {

    private static final Logger logger = LoggerFactory.getLogger(CacheImplTest.class);

    private static Object[][] getDataForTestCache(){
        return new Object[][]{
                {-10, 10}
        };
    }

    private CacheImpl<Integer, Integer> cache;

    @BeforeEach
    void before(){
        logger.info("Creating of new cache");
        cache = new CacheImpl<Integer, Integer>();
    }

    @DisplayName("cache test")
    @ParameterizedTest
    @MethodSource("getDataForTestCache")
    void testCache(int min, int max){

        CacheImpl<Integer, Integer> cache = new CacheImpl<Integer, Integer>();
        CacheListener<Integer, Integer> listener = (key, value, action) -> {
            logger.info("key : {}, value : {}, action : {}", key, value, action);
        };
        cache.subscribeListener(listener);

        for(int i = min; i <= max; i++) {
            cache.put(i, i);
            assertThat(i).isEqualTo(cache.get(i));
            cache.put(i, i);
            assertThat(i).isEqualTo(cache.get(i));
        }

        for (int i = min; i <= max; i++){
            cache.remove(i);
            assertThat(cache.get(i)).isNull();
            cache.remove(i);
            assertThat(cache.get(i)).isNull();
        }
    }

    @AfterEach
    void after(){
        logger.info("Checking of deleting listeners in cache");
        assertThat(cache.getListeners().size()).isEqualTo(0);
    }
}
