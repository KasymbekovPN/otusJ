package ru.otus.kasymbekovPN.HW09;

import java.util.List;

public interface PreparedInstanceData {
    boolean isValid();
    String getCreateTableUrl();
    Pair<String, List<Object>> getInsertUrl(Object instance) throws NoSuchFieldException, IllegalAccessException;
}
