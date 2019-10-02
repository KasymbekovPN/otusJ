package ru.otus.kasymbekovPN.HW09.query;

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
     * Геттер типа столюца
     * @return Тип столбца
     */
    String getType();
}
