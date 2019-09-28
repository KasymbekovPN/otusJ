package ru.otus.kasymbekovPN.HW09.api.service;

import java.util.Optional;

public interface DBService<T> {
    Optional<T> createRecord(T instance);
    Optional<T> updateRecord(T instance);
    Optional<T> loadRecord(long id, T dummy);
}
