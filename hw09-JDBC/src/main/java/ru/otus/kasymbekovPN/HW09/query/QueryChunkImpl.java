package ru.otus.kasymbekovPN.HW09.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Реализация класса, работающего с частью запроса.
 */
public class QueryChunkImpl implements QueryChunk {

    private static Logger logger = LoggerFactory.getLogger(QueryChunkImpl.class);

    /**
     * Имя столбка
     */
    private String name;

    /**
     * Тип столбка
     */
    private String type;

    /**
     * генерация строкового представления типа
     * на основе полученного инстанса
     * @param instance инстанс
     * @return Строковое представление типа
     */
    private static String makeType(Object instance){
        String type = "";
        if (String.class.isAssignableFrom(instance.getClass()))
            type = "VARCHAR(255)";
        else if (Integer.class.isAssignableFrom(instance.getClass()))
            type = "INT";
        else if (Long.class.isAssignableFrom(instance.getClass()))
            type = "LONG";
        else if (Double.class.isAssignableFrom(instance.getClass()))
            type = "DOUBLE";
        else
            logger.error("wrong type");

        return type;
    }

    /**
     * Конструктор
     * @param name имя столбца
     * @param instance инстанс
     */
    QueryChunkImpl(String name, Object instance) {
        this.name = name;
        this.type = makeType(instance);
    }

    /**
     * Геттер имени столбца
     * @return имя столбца
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }
}
