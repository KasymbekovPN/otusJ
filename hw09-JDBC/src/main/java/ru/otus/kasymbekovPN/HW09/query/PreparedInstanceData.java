package ru.otus.kasymbekovPN.HW09.query;

import ru.otus.kasymbekovPN.HW09.utils.Trio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс для реализации класса, реализующего подготовку
 * данных для запросов в БД
 */
public interface PreparedInstanceData {

    /**
     * Геттер запроса для создания таблицы
     * @return Запрос
     */
    String getCreateTableQuery();

    /**
     * Гетер данных для выполнения вставки данных в таблицу
     * @return Данные для вставки
     */
    Trio<String, List<Object>, List<String>> getInsertQuery() throws NoSuchFieldException, IllegalAccessException;

    /**
     * Геттер данных для обноваления данных в таблице
     * @return Данные для обновления
     */
    Trio<String, List<Object>, List<String>> getUpdateQuery() throws NoSuchFieldException, IllegalAccessException;

    /**
     * Геттер данных для выборки
     * @return Данные для выборки
     */
    Trio<String, String, List<String>> getSelectQuery();

    /**
     * Заполняем pst переданными значениями
     * @param pst pst
     * @param values значения
     * @param names имена значений
     */
    void fillPst(PreparedStatement pst, List<Object> values, List<String> names) throws SQLException;

    /**
     * Заполняет инстанс, полученными из БД данными
     * @param rs полученные данные
     * @param names имена данных
     * @return Модернизированый инстанс
     */
    Object fillInstance(ResultSet rs, List<String> names) throws SQLException, NoSuchFieldException, IllegalAccessException;

    /**
     * Задаём значение ключевого поля инстанса
     * @param rs данные
     */
    void setKeyField(ResultSet rs) throws NoSuchFieldException, SQLException, IllegalAccessException;

    /**
     * Задаем инстанс
     * @param instance инстанс
     */
    void setInstance(Object instance);

    /**
     * Проверяет является ли инстанс класса, реализубщего данный интерфейс, валидным
     * @return Результат проверки
     */
    boolean isValid();

    boolean isValid_();

    String getCreateTableQuery_();
    String getInsertQuery_();
    String getUpdateQuery_();
    String getSelectQuery_();

    List<Object> extractValues(Object instance) throws IllegalAccessException;
    Object extractKey(Object instance) throws IllegalAccessException;
}
