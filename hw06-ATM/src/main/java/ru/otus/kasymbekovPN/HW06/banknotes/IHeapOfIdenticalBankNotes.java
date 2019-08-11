package ru.otus.kasymbekovPN.HW06.banknotes;

public interface IHeapOfIdenticalBankNotes {
    boolean add(IHeapOfIdenticalBankNotes heap);
    boolean sub(IHeapOfIdenticalBankNotes heap);
    void confirmChange();
    int getNumber();
    void setNumber(int number);
    int get();
    ECurrency getDenomination();
    void setDenomination(ECurrency denomination);
    void display();
    IHeapOfIdenticalBankNotes clone();
}


