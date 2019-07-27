package ru.otus.kasymbekovPN.HW04.accumulator;

import ru.otus.kasymbekovPN.HW04.annotations.Log;

public class Accumulator {
    private double value;

    public Accumulator() {
    }

    public Accumulator(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Log
    public double add(double value){
        return (this.value += value);
    }

    @Log
    public double sud(double value){
        return (this.value -= value);
    }

    @Log
    public double prod(double value){
        return  (this.value *= value);
    }

    @Log
    public double div(double value){
        if (value != 0){
            return (this.value /= value);
        } else {
            throw new IllegalArgumentException("div by zero");
        }
    }
}
