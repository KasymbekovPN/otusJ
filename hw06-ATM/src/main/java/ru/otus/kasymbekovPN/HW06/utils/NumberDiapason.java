package ru.otus.kasymbekovPN.HW06.utils;

/**
 * Класс, хранящий максимальное и минимальное значение количества
 * банкнот в ячейке банкомата.
 */
public class NumberDiapason{

    /**
     * Минимальное количество банкнот в ячейке банкомата.
     */
    public static int MIN_NUMBER = 0;

    /**
     * Максимальное количество банкнот в ячейке банкомата.
     */
    public static int MAX_NUMBER = 8_000;

    /**
     * Метод, корректирующий значение агрумента.
     * Корректное значение не должно быть меньше {{@link NumberDiapason#MIN_NUMBER}}
     * @param value корректируемое значение.
     * @return Корректное значение.
     */
    static public int putInRange(int value){

        if (value < MIN_NUMBER){
            value = MIN_NUMBER;
        }

        return value;
    }
}
