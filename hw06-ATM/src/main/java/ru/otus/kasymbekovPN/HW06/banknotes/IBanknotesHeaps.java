package ru.otus.kasymbekovPN.HW06.banknotes;

import java.util.Map;

public interface IBanknotesHeaps {
    boolean add(IBanknotesHeaps heaps);
    boolean sub(IBanknotesHeaps heaps);
    Map<ECurrency, IHeapOfIdenticalBankNotes> getHeaps();
    void display();
}
