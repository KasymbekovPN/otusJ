package ru.otus.kasymbekovPN.HW08.utils;

/**
 * Перечисление вспомоготельных строк
 */
public enum Txt {
    COLON(":"),
    COMMA(","),
    NULL("null"),
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
    OPEN_SQ_BRACKET("["),
    CLOSE_SQ_BRACKET("]"),
    DOUBLE_QUOTE("\"");

    /**
     * Значение вспомоготальной строки
     */
    private String value;

    /**
     * Геттер значения вспомоготельной строки
     * @return Значение вспомогательной строки
     */
    public String get(){
        return value;
    }

    /**
     * Конструктор
     * @param value вспомоготальная строка
     */
    Txt(String value) {
        this.value = value;
    }
}
