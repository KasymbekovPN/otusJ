package ru.otus.kasymbekovPN.HW10.api.dao;

public class DBUserDaoException extends RuntimeException{
    public DBUserDaoException(Exception ex){
        super(ex);
    }
}
