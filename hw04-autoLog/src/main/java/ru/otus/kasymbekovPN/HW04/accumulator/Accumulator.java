package ru.otus.kasymbekovPN.HW04.accumulator;

import ru.otus.kasymbekovPN.HW04.annotations.Log;

public class Accumulator implements ICalc{
    private double value;

    @Log
    @Override
    public double add(double value) {
        return this.value += value;
    }

    @Override
    public double add(double v1, double v2) {
        return this.value += v1 + v2;
    }

    @Log
    @Override
    public double sub(double value) {
        return this.value -= value;
    }

    @Log
    @Override
    public double prod(double value) {
        return this.value *= value;
    }

    @Log
    @Override
    public double div(double value) {
        if (value != 0){
            return this.value /= value;
        } else {
            throw new IllegalArgumentException("Div by zero");
        }
    }

    @Override
    public double addWithoutLog(double value) {
        return this.value += value;
    }
}
