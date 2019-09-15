package ru.otus.kasymbekovPN.HW08.experimentVictims;

import java.util.Objects;

public class EV2 {
    private int a = 10;
    private double b = 101.1;
    private EV3 ev3 = new EV3();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EV2 ev2 = (EV2) o;
        return a == ev2.a &&
                Double.compare(ev2.b, b) == 0 &&
                Objects.equals(ev3, ev2.ev3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, ev3);
    }

    @Override
    public String toString() {
        return "EV2{" +
                "a=" + a +
                ", b=" + b +
                ", ev3=" + ev3 +
                '}';
    }
}
