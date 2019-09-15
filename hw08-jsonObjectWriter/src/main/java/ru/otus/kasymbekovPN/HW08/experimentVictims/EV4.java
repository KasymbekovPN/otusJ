package ru.otus.kasymbekovPN.HW08.experimentVictims;

import java.util.Objects;

public class EV4 {
    private int i = 1;
    private double d = 12.34;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EV4 ev4 = (EV4) o;
        return i == ev4.i &&
                Double.compare(ev4.d, d) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, d);
    }

    @Override
    public String toString() {
        return "EV4{" +
                "i=" + i +
                ", d=" + d +
                '}';
    }
}
