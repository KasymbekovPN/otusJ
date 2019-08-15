package ru.otus.kasymbekovPN.HW06.banknotes;

public interface HeapOfIdenticalBanknotes {
    boolean add(HeapOfIdenticalBanknotes heap);
    boolean sub(HeapOfIdenticalBanknotes heap);
    void confirmChange();
    int getNumber();
    void setNumber(int number);
    int get();
    Currency getDenomination();
    void setDenomination(Currency denomination);
    void display();
    HeapOfIdenticalBanknotes clone();
}


