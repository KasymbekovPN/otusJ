package ru.otus.kasymbekovPN.HW10.api.service;

class DBServiceException extends RuntimeException{
    DBServiceException(Exception ex){
        super(ex);
    }
}
