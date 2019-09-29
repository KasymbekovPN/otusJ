package ru.otus.kasymbekovPN.HW09.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QueryChunkImpl implements QueryChunk {

    private static Logger logger = LoggerFactory.getLogger(QueryChunkImpl.class);

//    private String name;
//    private String type;
//    private boolean isKey;
    //<
//    private String queryChunk;
    private String name;
    private String type;
    private boolean isKey;

    private static boolean isVARCHAR(String type){
        return type.split("\\(")[0].equals("VARCHAR");
    }

    private static String makeType(Object instance){
        String type = "";
        if (String.class.isAssignableFrom(instance.getClass()))
            type = "VARCHAR(255)";
        else if (Integer.class.isAssignableFrom(instance.getClass()))
            type = "INT";
        else if (Long.class.isAssignableFrom(instance.getClass()))
            type = "LONG";
        else if (Double.class.isAssignableFrom(instance.getClass()))
            type = "DOUBLE";
        else
            logger.error("wrong type");

        return type;
    }

    public static String makeCreateQuery(String tableName, QueryChunk key, List<QueryChunk> others){
        StringBuilder sb = new StringBuilder("CREATE TABLE ")
                .append(tableName)
                .append("(")
                .append(key.getCreateChunk());
        for (QueryChunk other : others) {
            sb.append(", ").append(other.getCreateChunk());
        }
        sb.append(")");

        return String.valueOf(sb);
    }

    public static String makeInsertQuery(String tableName, List<String> names){
        StringBuilder namesLine = new StringBuilder("(");
        StringBuilder questionMarksLine = new StringBuilder("(");
        String delimiter = "";
        for (String name : names) {
            namesLine.append(delimiter).append(name);
            questionMarksLine.append(delimiter).append("?");
            delimiter = ",";
        }

        return "INSERT INTO " +
                tableName +
                namesLine +
                ")" +
                " VALUES " +
                questionMarksLine +
                ")";
    }

    public static String makeSelectQuery(String tableName, String key, List<String> names){
        StringBuilder sb = new StringBuilder("SELECT");
        String delimiter = " ";
        for (String name : names) {
            sb.append(delimiter).append(name);
            delimiter = ", ";
        }
        sb.append(" FROM ")
                .append(tableName)
                .append(" WHERE ")
                .append(key)
                .append("=?");

        return String.valueOf(sb);
    }

    public static String makeUpdateQuery(String tableName, String key, List<String> names){
        StringBuilder sb = new StringBuilder("UPDATE ")
                .append(tableName)
                .append(" SET");
        String delimiter = " ";
        for (String name : names) {
            sb.append(delimiter)
                    .append(name)
                    .append("=?");
            delimiter = ", ";
        }
        sb.append(" WHERE ")
                .append(key)
                .append("=?");

        return String.valueOf(sb);
    }

    public QueryChunkImpl(String name, Object instance, boolean isKey) {
//        this.name = name;
//        this.type = type;
//        this.isKey = isKey;
        //<
//        this.queryChunk = String.valueOf(sb);
        //<

        this.name = name;
        this.type = makeType(instance);
        this.isKey = isKey;
    }

    //<
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String getType() {
//        return type;
//    }
//
//    @Override
//    public boolean isKey() {
//        return isKey;
//    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getCreateChunk() {
//        return queryChunk;
        //<
        StringBuilder sb = new StringBuilder(name).append(" ").append(type);
        if (isKey){
            sb.append(" AUTO_INCREMENT");
        }

        return String.valueOf(sb);
    }

    @Override
    public void extractName(StringBuilder sb, String delimiter) {
        sb.append(delimiter).append(name);
    }

    @Override
    public void extractQuestionMark(StringBuilder sb, String delimiter) {
        sb.append(delimiter).append("?");
    }

    @Override
    public void fillPst(PreparedStatement pst, Object value, int index) throws SQLException {
        if (isVARCHAR(type))
            pst.setString(index, String.valueOf(value));
        else if (type.equals("INT"))
            pst.setInt(index, (Integer)value);
        else if (type.equals("LONG"))
            pst.setLong(index, (Long)value);
        else if (type.equals("DOUBLE"))
            pst.setDouble(index, (Double)value);
        else
            logger.error("wrong type");
    }

    @Override
    public void setField(ResultSet rs, Field field, Object instance) throws SQLException, IllegalAccessException {
        field.setAccessible(true);
        if (isVARCHAR(type)){
            field.set(instance, rs.getString(name));
        } else if (type.equals("INT")){
            field.setInt(instance, rs.getInt(name));
        } else if (type.equals("LONG")){
            field.setLong(instance, rs.getLong(name));
        } else if (type.equals("DOUBLE")){
            field.setDouble(instance, rs.getDouble(name));
        } else {
            logger.error("setField : wrong type");
        }
    }


}
