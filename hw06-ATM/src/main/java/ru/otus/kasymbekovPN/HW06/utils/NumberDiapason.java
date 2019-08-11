package ru.otus.kasymbekovPN.HW06.utils;

public class NumberDiapason{
    public static int MIN_NUMBER = 0;
    public static int MAX_NUMBER = 8_000;

    static public int putInRange(int value){

        if (value < MIN_NUMBER){
            value = MIN_NUMBER;
        }

        return value;
    }
}
