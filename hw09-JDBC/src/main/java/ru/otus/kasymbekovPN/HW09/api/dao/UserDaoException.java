package ru.otus.kasymbekovPN.HW09.api.dao;

public class UserDaoException extends RuntimeException{
    public UserDaoException(Exception ex) {
        super(ex);
    }
}
