package ru.otus.kasymbekovPN.HW07.utils;

import ru.otus.kasymbekovPN.HW07.department.banknotes.BanknoteHeaps;

/**
 * Интерфейс для реализации хранителя
 */
public interface Memento {

    /**
     * Сеттер состояния
     * @param state состояние
     */
    void setState(BanknoteHeaps state);

    /**
     * Геттер состояния
     * @return Состояние
     */
    BanknoteHeaps getState();
}
