package ru.otus.kasymbekovPN.HW09.api.service;

import java.util.Optional;

public interface DBService<T> {
    void createRecord(T instance);
    void updateRecord(T instance);
    Optional<T> loadRecord(long id);
}
