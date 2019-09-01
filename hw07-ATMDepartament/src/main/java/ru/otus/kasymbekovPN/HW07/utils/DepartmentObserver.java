package ru.otus.kasymbekovPN.HW07.utils;

/**
 * Интерфейс для классов, реализующих функционал
 * наблюдателя
 */
public interface DepartmentObserver {

    /**
     * Возврашает баланс
     * @return баланс
     */
    int getBalance();

    /**
     * Сеттер состояния (через хранителя)
     * @param memento хранитель
     */
    void setState(Memento memento);

    /**
     * Геттер состояния (через храниетля)
     * @return Храниель
     */
    Memento getState();

    /**
     * Геттер идентификатора
     * @return Идентификатор.
     */
    int getID();
}
