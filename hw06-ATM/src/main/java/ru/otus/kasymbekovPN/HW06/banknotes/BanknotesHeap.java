package ru.otus.kasymbekovPN.HW06.banknotes;

import ru.otus.kasymbekovPN.HW06.utils.NumberDiapason;

public class BanknotesHeap implements IHeapOfIdenticalBankNotes {
    private int number;
    private ECurrency banknoteDenomination;

    public BanknotesHeap(int number){
        this.number = number;
    }

    private BanknotesHeap(ECurrency banknoteDenomination, int number){
        this.banknoteDenomination = banknoteDenomination;
        this.number = number;
    }

    @Override
    public boolean add(IHeapOfIdenticalBankNotes heap) {
        banknoteDenomination = heap.getDenomination();
        number = heap.getNumber();
        return true;
    }

    @Override
    public boolean sub(IHeapOfIdenticalBankNotes heap) {
        banknoteDenomination = heap.getDenomination();
        number = heap.getNumber();
        return true;
    }

    @Override
    public void confirmChange() {
        number = NumberDiapason.MIN_NUMBER;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int get() {
        return getDenomination().getValue() * getNumber();
    }

    @Override
    public ECurrency getDenomination() {
        return banknoteDenomination;
    }

    @Override
    public void setDenomination(ECurrency denomination) {
        banknoteDenomination = denomination;
    }

    @Override
    public void display() {
        System.out.println("Denomination : " + banknoteDenomination.getValue()
                + ", number : " + number + ", sum : " + get());
    }

    @Override
    public IHeapOfIdenticalBankNotes clone() {
        return new BanknotesHeap(banknoteDenomination, number);
    }
}
