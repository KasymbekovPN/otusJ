package ru.otus.kasymbekovPN.HW06.banknotes;

import java.util.Map;

public interface BanknotesHeaps {
    boolean add(BanknotesHeaps heaps);
    boolean sub(BanknotesHeaps heaps);
    Map<Currency, HeapOfIdenticalBanknotes> getHeaps();
    void display();
}
