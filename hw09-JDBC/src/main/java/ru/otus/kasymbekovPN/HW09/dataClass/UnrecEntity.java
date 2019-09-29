package ru.otus.kasymbekovPN.HW09.dataClass;

public class UnrecEntity {

    private long id;

    private String value;

    public String getValue() {
        return value;
    }

    public UnrecEntity() {
    }

    public UnrecEntity(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UnrecEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
