package ru.otus.kasymbekovPN.HW11.cache;

/**
 * Перечень действий с кэшом
 */
public enum CacheActionNames {
    PUT("element inserting"),
    UPDATE("element updating"),
    REMOVE("element removing");

    /**
     * Наименование действия
     */
    private String name;

    /**
     * Геттер наименования действия
     * @return Наименование действия
     */
    public String get() {
        return name;
    }

    /**
     * Конструктор
     * @param name Наименование действия
     */
    CacheActionNames(String name) {
        this.name = name;
    }
}
