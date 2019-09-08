package ru.otus.kasymbekovPN.HW07.utils;

/**
 * Интерфейс для реализации функционала "создателя"
 */
public interface Originator {

    /**
     * Создать "хранителя"
     * @return "Хранитель"
     */
    Memento createMemento();

    /**
     * Сеттер "хранителя"
     * @param memento "хранитель"
     */
    void setMemento(Memento memento);
}
