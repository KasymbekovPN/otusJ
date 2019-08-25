package ru.otus.kasymbekovPN.HW07.banknotes;

/**
 * Перечисление номиналов банкнот.
 */
public enum Currency {
    VALUE_10(10),
    VALUE_50(50),
    VALUE_100(100),
    VALUE_200(200),
    VALUE_500(500),
    VALUE_1000(1000),
    VALUE_2000(2000),
    VALUE_5000(5000);

    /**
     * Числовое вырожение номинала банкноты.
     */
    final private int value;

    public int getValue() {
        return value;
    }

    Currency(int value){
        this.value = value;
    }
}
