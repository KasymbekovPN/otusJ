package ru.otus.kasymbekovPN.HW03;

public class ExperimentVictim {

    int sum(int x, int y){
        return x + y;
    }

    double div(double x, double y){

        if (0 == y){
            throw new IllegalArgumentException();
        }

        return x / y;
    }
}
