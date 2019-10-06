package ru.otus.kasymbekovPN.HW10.api.service;

public class DBServiceException extends RuntimeException{
    public DBServiceException(Exception ex){
        super(ex);
    }
}
