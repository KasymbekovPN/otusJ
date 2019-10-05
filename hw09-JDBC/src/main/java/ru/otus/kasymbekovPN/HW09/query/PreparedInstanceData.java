package ru.otus.kasymbekovPN.HW09.query;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс для реализации класса, реализующего подготовку
 * данных для запросов в БД
 */
public interface PreparedInstanceData {
    /**
     * Проверяет является ли инстанс класса, реализубщего данный интерфейс, валидным
     * @return Результат проверки
     */
    boolean isValid();

    /**
     * Геттер запроса для создания таблицы
     * @return Запрос
     */
    String getCreateTableQuery();

    /**
     * Гетер данных для выполнения вставки данных в таблицу
     * @return Данные для вставки
     */
    String getInsertQuery();

    /**
     * Геттер данных для обноваления данных в таблице
     * @return Данные для обновления
     */
    String getUpdateQuery();

    /**
     * Геттер данных для выборки
     * @return Данные для выборки
     */
    String getSelectQuery();

    /**
     * Геттер ключевого поля
     * @return Ключевое поле
     */
    Field getKeyField();

    /**
     * Геттер неключевых полей
     * @return Неключевые поля
     */
    List<Field> getOtherFields();
}
