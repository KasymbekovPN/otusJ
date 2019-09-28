package ru.otus.kasymbekovPN.HW09.dataClass;

import ru.otus.kasymbekovPN.HW09.annotation.Id;

import java.util.Objects;

public class Account {

    //< задавать значение через рефлексию
    @Id
    private long no = -1;

    private String type;

    private double rest;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRest() {
        return rest;
    }

    public void setRest(double rest) {
        this.rest = rest;
    }

    public Account() {
    }

    public Account(String type, double rest) {
        this.type = type;
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return no == account.no &&
                Double.compare(account.rest, rest) == 0 &&
                Objects.equals(type, account.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, type, rest);
    }
}
