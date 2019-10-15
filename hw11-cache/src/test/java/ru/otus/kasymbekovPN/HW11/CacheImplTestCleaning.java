package ru.otus.kasymbekovPN.HW11;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW11.cache.CacheImpl;
import ru.otus.kasymbekovPN.HW11.cache.CacheListenerImpl;

import static org.assertj.core.api.Assertions.*;

/*
    run test with -Xms128m -Xmx128m
 */
@DisplayName("Testing of CacheImplTes")
class CacheImplTestCleaning {

    private static final Logger logger = LoggerFactory.getLogger(CacheImplTestCleaning.class);

    private static Object[][] getDataForTestCache(){
        return new Object[][]{
                {0, 5_000}
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
        for (int i = min ; i < max; i++){
            cache.subscribeListener(new CacheListenerImpl<>());
        }
        System.gc();
    }

    @AfterEach
    void after(){
        logger.info("Checking of deleting listeners in cache");
        assertThat(cache.getListeners().size()).isEqualTo(0);
    }
}
