package ru.otus.kasymbekovPN.HW07.utils;

/**
 * Интерфейс для реализации смотрителя
 */
public interface Caretaker {

    /**
     * Сеттер хранителя
     * @param memento хранитель
     */
    void setMemento(Memento memento);

    /**
     * Сеттер храниетля
     * @return хранитель
     */
    Memento getMemento();
}
