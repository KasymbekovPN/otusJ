package ru.otus.kasymbekovPN.HW08.experimentVictims;

import java.util.Objects;

public class EV3 {
    String s = "Hello";
    int i = 100;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EV3 ev3 = (EV3) o;
        return i == ev3.i &&
                Objects.equals(s, ev3.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, i);
    }

    @Override
    public String toString() {
        return "EV3{" +
                "s='" + s + '\'' +
                ", i=" + i +
                '}';
    }
}
