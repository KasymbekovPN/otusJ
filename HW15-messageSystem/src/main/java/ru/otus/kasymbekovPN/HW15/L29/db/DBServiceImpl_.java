package ru.otus.kasymbekovPN.HW15.L29.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DBServiceImpl_ implements DBService_ {

    private static final Logger logger = LoggerFactory.getLogger(DBServiceImpl_.class);
    private final Map<Long, String> database = new HashMap<>();

    private void initDatabase() {
        database.put(1L, "val1");
        database.put(2L, "val2");
        database.put(3L, "val3");
    }

    public DBServiceImpl_() {
        initDatabase();
    }

    public String getUserData(long id) {
        logger.info("get data for id:{}", id);
        return database.get(id);
    }
}
