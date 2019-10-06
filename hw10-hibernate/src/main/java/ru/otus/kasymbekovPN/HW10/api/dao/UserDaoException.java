package ru.otus.kasymbekovPN.HW10.api.dao;

public class UserDaoException extends RuntimeException{
    public UserDaoException(Exception ex){
        super(ex);
    }
}
