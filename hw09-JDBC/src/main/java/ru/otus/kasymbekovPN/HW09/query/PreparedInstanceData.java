package ru.otus.kasymbekovPN.HW09.query;

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
     * Задаём значение ключевого поля инстанса
     * @param rs данные
     */
    void setKeyField(ResultSet rs, Object instance) throws NoSuchFieldException, SQLException, IllegalAccessException;

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
     * Получаем значения неключевых полей инстанса
     * @param instance инстанс
     * @return Список значений
     */
    List<Object> extractValues(Object instance) throws IllegalAccessException;

    /**
     * Получаем значение ключевого поля инстанста
     * @param instance инстанс
     * @return Значение ключевого поля инстанса
     */
    Object extractKey(Object instance) throws IllegalAccessException;

    /**
     * Заполняет инстанс, полученными из БД данными
     * @param rs полученные данные
     * @param clazz заполняемый класс
     * @return Модернизированый инстанс
     */
    Object fillInstance(ResultSet rs, Class clazz) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException;
}
