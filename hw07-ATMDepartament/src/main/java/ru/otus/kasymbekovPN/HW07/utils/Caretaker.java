package ru.otus.kasymbekovPN.HW07.utils;

/**
 * Интерфейс для реализации смотрителя
 */
public interface Caretaker {

    /**
     * Сеттер храниетля
     * @param ID идентификатор хранителя
     * @param memento храниель
     */
    void setMemento(int ID, Memento memento);

    /**
     * Геттер хранителя
     * @param ID индентификатор
     * @return Хранитель
     */
    Memento getMemento(int ID);

    /**
     * Удалить инстанс храниетеля по идентификатору
     * @param ID индентификатор
     */
    void remove(int ID);
}
