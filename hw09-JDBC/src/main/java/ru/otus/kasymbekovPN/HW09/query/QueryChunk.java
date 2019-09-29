package ru.otus.kasymbekovPN.HW09.query;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface QueryChunk {
//    public String getName();
//    public String getType();
//    public boolean isKey();
    //<
    public String getName();
    public String getType();
    public String getCreateChunk();
    public void extractName(StringBuilder sb, String delimiter);
    public void extractQuestionMark(StringBuilder sb, String delimiter);
    public void fillPst(PreparedStatement pst, Object value, int index) throws SQLException;
    public void setField(ResultSet rs, Field field, Object instance) throws SQLException, IllegalAccessException;
}
