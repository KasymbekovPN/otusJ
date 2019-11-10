package ru.otus.kasymbekovPN.HW15.db.api.service;

public class DBServiceException  extends  RuntimeException{
    DBServiceException(Exception ex){
        super(ex);
    }
}
