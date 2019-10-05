package ru.otus.kasymbekovPN.HW09.query;

import java.lang.reflect.Field;
import java.util.List;

public interface ExtractValuesHelper {

    /**
     * Геттер ключевого поля инстанса
     * @param instance Инстанс
     * @param key Ключевое поле
     * @return Значение ключенвого поля
     */
    Object extractKeyValue(Object instance, Field key) throws IllegalAccessException;

    /**
     * Геттер списка значений не ключевых полей инстанса.
     * @param instance инстанс
     * @param others Неключевые поля
     * @return Значения неключевых полей
     */
    List<Object> extractOtherValues(Object instance, List<Field> others) throws IllegalAccessException;

}
