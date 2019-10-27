package ru.otus.kasymbekovPN.HW13.db.api.service;

public class DBServiceException  extends  RuntimeException{
    DBServiceException(Exception ex){
        super(ex);
    }
}
