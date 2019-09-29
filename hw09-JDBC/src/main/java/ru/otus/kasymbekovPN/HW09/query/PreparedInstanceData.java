package ru.otus.kasymbekovPN.HW09.query;

import ru.otus.kasymbekovPN.HW09.utils.Trio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PreparedInstanceData {
    boolean isValid();
    String getCreateTableQuery();
//    Pair<String, List<Object>> getInsertUrl(Object instance) throws NoSuchFieldException, IllegalAccessException;
    //<

    //< return this ???
    void setInstance(Object instance);
    //<

    Trio<String, List<Object>, List<String>> getInsertQuery() throws NoSuchFieldException, IllegalAccessException;
    void fillPst(PreparedStatement pst, List<Object> values, List<String> names) throws SQLException;
    void setKeyField(ResultSet rs) throws NoSuchFieldException, SQLException, IllegalAccessException;
    Trio<String, String, List<String>> getSelectSql();
    Object fillInstance(ResultSet rs, List<String> names) throws SQLException, NoSuchFieldException, IllegalAccessException;

    Trio<String, List<Object>, List<String>> getUpdateSql() throws NoSuchFieldException, IllegalAccessException;
}
