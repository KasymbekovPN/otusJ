package ru.otus.kasymbekovPN.HW09;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PreparedInstanceData {
    boolean isValid();
    String getCreateTableUrl();
//    Pair<String, List<Object>> getInsertUrl(Object instance) throws NoSuchFieldException, IllegalAccessException;
    //<
    void setInstance(Object instance);
    Trio<String, List<Object>, List<String>> getInsertUrl() throws NoSuchFieldException, IllegalAccessException;
    void fillPst(PreparedStatement pst, List<Object> values, List<String> names) throws SQLException;
    void setKeyField(ResultSet rs) throws NoSuchFieldException, SQLException, IllegalAccessException;
    Pair<String, List<String>> getSelectSql();
    Object fillInstance(ResultSet rs, List<String> names) throws SQLException, NoSuchFieldException, IllegalAccessException;
}
