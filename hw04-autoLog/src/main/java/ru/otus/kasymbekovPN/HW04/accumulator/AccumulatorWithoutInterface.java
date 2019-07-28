package ru.otus.kasymbekovPN.HW04.accumulator;

import ru.otus.kasymbekovPN.HW04.annotations.Log;

public class AccumulatorWithoutInterface {
    private double value;

    @Log
    public double add(double value) {
        return this.value += value;
    }

    @Log
    public double sub(double value) {
        return this.value -= value;
    }

    @Log
    public double prod(double value) {
        return this.value *= value;
    }

    @Log
    public double div(double value) {
        if (value != 0){
            return this.value /= value;
        } else {
            throw new IllegalArgumentException("Div by zero");
        }
    }
}
