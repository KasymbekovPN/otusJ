package ru.otus.kasymbekovPN.HW09.query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExtractValuesHelper {

    /**
     * Геттер ключевого поля инстанса
     * @param instance Инстанс
     * @param key Ключевое поле
     * @return Значение ключенвого поля
     */
    static public Object extractKeyValue(Object instance, Field key) throws IllegalAccessException {
        return key.get(instance);
    }

    /**
     * Геттер списка значений не ключевых полей инстанса.
     * @param instance инстанс
     * @param others Неключевые поля
     * @return Значения неключевых полей
     */
    static public List<Object> extractOtherValues(Object instance, List<Field> others) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        for (Field other : others) {
            values.add(other.get(instance));
        }
        return values;
    }
}
