package ru.otus.kasymbekovPN.HW06.banknotes;

import ru.otus.kasymbekovPN.HW06.utils.NumberDiapason;

public class BanknotesHeap implements HeapOfIdenticalBanknotes {
    private int number;
    private Currency banknoteDenomination;

    public BanknotesHeap(int number){
        this.number = number;
    }

    private BanknotesHeap(Currency banknoteDenomination, int number){
        this.banknoteDenomination = banknoteDenomination;
        this.number = number;
    }

    @Override
    public boolean add(HeapOfIdenticalBanknotes heap) {
        return false;
    }

    @Override
    public boolean sub(HeapOfIdenticalBanknotes heap) {
        return false;
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
    public Currency getDenomination() {
        return banknoteDenomination;
    }

    @Override
    public void setDenomination(Currency denomination) {
        banknoteDenomination = denomination;
    }

    @Override
    public void display() {
        System.out.println("Denomination : " + banknoteDenomination.getValue()
                + ", number : " + number + ", sum : " + get());
    }

    @Override
    public HeapOfIdenticalBanknotes clone() {
        return new BanknotesHeap(banknoteDenomination, number);
    }
}
