package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

/**
 * Интерфейс для реалитзации сериализации инстанса
 * объекта в json-строку.
 */
public interface JavaObjectWriter {

    /**
     * Возвращает объект как json-строку
     * @return json-строку
     */
    String getObjectAsJsonStr();
}
