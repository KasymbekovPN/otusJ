package ru.otus.kasymbekovPN.HW09.api.executor;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Интерфейс для реализации экзекутора БД
 * @param <T> Класс, с которым работает экзекутор
 */
public interface DBExecutor<T> {

    /**
     * Запись в БД
     * @param instance Записываемый объект
     * @param connection Соединение
     * @return Записанный объект
     */
    Optional<T> createRecord(T instance, Connection connection) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException;

    /**
     * Обновление данных в БД, соответствующих объекту
     * @param instance объект
     * @param connection соединение
     * @return Объект
     */
    Optional<T> updateRecord(T instance, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException;

    /**
     * Выгрузка данных из БД по ключу
     * @param id значение ключа
     * @param clazz класс для выгрузки
     * @param connection соединение
     * @return объект с выгруженными данными
     */
    Optional<T> loadRecord(long id, Class clazz, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException;
}
