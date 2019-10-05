package ru.otus.kasymbekovPN.HW09.query;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetHelper {

    /**
     * Заполняет ключевое поле инстанса
     * @param rs Данные
     * @param instance Инстанс
     * @param key Ключевое поле
     */
    void setKeyField(ResultSet rs, Object instance, Field key) throws SQLException, IllegalAccessException;

    /**
     * Создает инстанс и заполняет его поля
     * @param rs Данные
     * @param clazz Класс для создания
     * @param key Ключевое поле
     * @param others Прочие аоля
     * @return Созданные инстанс
     */
    Object makeInstance(ResultSet rs, Class clazz, Field key, List<Field> others) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
