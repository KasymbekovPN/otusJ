package ru.otus.kasymbekovPN.HW12.db.api.service;

public class DBServiceException  extends  RuntimeException{
    DBServiceException(Exception ex){
        super(ex);
    }
}
