package ru.otus.kasymbekovPN.HW09.query;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс для реализации класса, работающего с частью запроса.
 */
public interface QueryChunk {

    /**
     * Геттер имени столбца
     * @return имя столбца
     */
    String getName();

    /**
     * Геттер строки, соответствующей имени, для состовления запроса для для
     * создания таблицы
     * @return Строка
     */
    String getCreateChunk();

    /**
     * Заполнение pst значением соответствующем имени
     * @param pst pst
     * @param value значение
     * @param index инстанс
     */
    void fillPst(PreparedStatement pst, Object value, int index) throws SQLException;

    /**
     * Задание полю истанса значения из полученныз данных
     * @param rs полученные данные
     * @param field поле
     * @param instance инстанс
     */
    void setField(ResultSet rs, Field field, Object instance) throws SQLException, IllegalAccessException;
}
