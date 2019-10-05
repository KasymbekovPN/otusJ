package ru.otus.kasymbekovPN.HW09.api.service;

public class DbServiceException extends RuntimeException {
    public DbServiceException(Exception ex){
        super(ex);
    }
}
