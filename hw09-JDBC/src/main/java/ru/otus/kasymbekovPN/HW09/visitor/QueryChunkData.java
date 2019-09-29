package ru.otus.kasymbekovPN.HW09.visitor;

import ru.otus.kasymbekovPN.HW09.query.QueryChunk;

import java.util.List;

public interface QueryChunkData {

    /**
     * Геттер данных ключевого поля
     * @return данные
     */
    QueryChunk getKeyField();

    /**
     * Геттер данных неключевых полей
     * @return данные
     */
    List<QueryChunk> getFields();

    /**
     * @return Являются ли данные о полях валидными
     */
    boolean isValid();
}
