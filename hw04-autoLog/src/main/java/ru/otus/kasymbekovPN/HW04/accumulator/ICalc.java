package ru.otus.kasymbekovPN.HW04.accumulator;

public interface ICalc {
    double add(double value);
    double add(double v1, double v2);
    double sub(double value);
    double prod(double value);
    double div(double value);
    double addWithoutLog(double value);
}