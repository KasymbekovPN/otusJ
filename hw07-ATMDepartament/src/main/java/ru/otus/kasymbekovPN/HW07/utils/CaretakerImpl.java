package ru.otus.kasymbekovPN.HW07.utils;

/**
 * Класс-смотритель для хранителя
 */
public class CaretakerImpl implements Caretaker {

    /**
     * Хранитель
     */
    private Memento memento;

    /**
     * Сеттер хранителя
     * @param memento хранитель
     */
    @Override
    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    /**
     * Геттер храниетля
     * @return хранитель
     */
    @Override
    public Memento getMemento() {
        return memento;
    }
}
