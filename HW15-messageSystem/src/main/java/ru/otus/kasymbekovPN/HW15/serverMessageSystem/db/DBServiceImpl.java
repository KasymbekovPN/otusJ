package ru.otus.kasymbekovPN.HW15.serverMessageSystem.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DBServiceImpl implements DBService {

    private static final Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);
    private final Map<Long, String> database = new HashMap<>();

    //<
    private void initDatabase(){
        database.put(1L, "val1");
        database.put(2L, "val2");
    }
    //<

    public DBServiceImpl() {
        //<
        initDatabase();
        //<
    }

    @Override
    public String getUserData(long id) {
        logger.info("get data for id : {}", id);
        return database.get(id);
    }
}
