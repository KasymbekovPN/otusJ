package ru.otus.kasymbekovPN.HW09.api.dao;

public class DaoException extends RuntimeException{
    public DaoException(Exception ex) {
        super(ex);
    }
}
