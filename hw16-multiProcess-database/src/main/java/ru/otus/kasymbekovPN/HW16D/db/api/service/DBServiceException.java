package ru.otus.kasymbekovPN.HW16D.db.api.service;

public class DBServiceException  extends  RuntimeException{
    DBServiceException(Exception ex){
        super(ex);
    }
}
