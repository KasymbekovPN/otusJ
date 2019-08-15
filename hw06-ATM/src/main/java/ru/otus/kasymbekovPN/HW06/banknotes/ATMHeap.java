package ru.otus.kasymbekovPN.HW06.banknotes;

import ru.otus.kasymbekovPN.HW06.utils.NumberDiapason;

public class ATMHeap implements HeapOfIdenticalBanknotes {
    private boolean isAdd;
    private int number;
    private int newNumber;
    private Currency banknoteDenomination;
    private HeapOfIdenticalBanknotes newHeap;

    public ATMHeap(int number){
        this.number = number;
    }

    private ATMHeap(Currency banknoteDenomination, int number){
        this.banknoteDenomination = banknoteDenomination;
        this.number = number;
    }

    @Override
    public boolean add(HeapOfIdenticalBanknotes heap) {
        this.isAdd = true;
        this.newHeap = heap;
        this.newNumber = number + heap.getNumber();
        return NumberDiapason.MAX_NUMBER >= newNumber;
    }

    @Override
    public boolean sub(HeapOfIdenticalBanknotes heap) {
        this.isAdd = false;
        this.newHeap = heap;
        this.newNumber = number - heap.getNumber();
        return NumberDiapason.MIN_NUMBER <= newNumber;
    }

    @Override
    public void confirmChange() {
        number = newNumber;
        if (isAdd) {
            newHeap.confirmChange();
        }
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
        return new ATMHeap(banknoteDenomination, number);
    }
}
