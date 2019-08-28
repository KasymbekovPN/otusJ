package ru.otus.kasymbekovPN.HW07.atm;

import ru.otus.kasymbekovPN.HW07.banknotes.BanknoteHeaps;
import ru.otus.kasymbekovPN.HW07.utils.Memento;

/**
 * Класс-хранитель состояния банкомата
 */
public class MementoImpl implements Memento {

    /**
     * Состояние банкомата
     */
    private BanknoteHeaps state;

    /**
     * Сеттер состояния банкомата
     * @param state состояние
     */
    @Override
    public void setState(BanknoteHeaps state) {
        this.state = state;
    }

    /**
     * Геттер состояния
     * @return Состояние
     */
    @Override
    public BanknoteHeaps getState() {
        return state;
    }
}