package ru.otus.kasymbekovPN.HW09.query;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ResultSetHelper {

    /**
     * Заполняет ключевое поле инстанса
     * @param rs Данные
     * @param instance Инстанс
     * @param key Ключевое поле
     */
    static public void setKeyField(ResultSet rs, Object instance, Field key) throws SQLException, IllegalAccessException {
        rs.next();
        key.set(instance, rs.getObject(key.getName()));
    }

    /**
     * Создает инстанс и заполняет его поля
     * @param rs Данные
     * @param clazz Класс для создания
     * @param key Ключевое поле
     * @param others Прочие аоля
     * @return Созданные инстанс
     */
    static public Object makeInstance(ResultSet rs, Class clazz, Field key, List<Field> others) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object instance = clazz.getConstructor().newInstance();
        rs.next();
        int index = 1;
        key.set(instance, rs.getObject(index++));
        for (Field field : others) {
            field.set(instance, rs.getObject(index++));
        }
        return instance;
    }
}
