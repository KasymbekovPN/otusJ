package ru.otus.kasymbekovPN.HW07.utils;

import ru.otus.kasymbekovPN.HW07.banknotes.BanknoteHeaps;

public interface Memento {
    void setState(BanknoteHeaps state);
    BanknoteHeaps getState();
}
