package ru.otus.kasymbekovPN.HW06.banknotes;

import ru.otus.kasymbekovPN.HW06.utils.NumberDiapason;

public class ATMHeap implements IHeapOfIdenticalBankNotes{
    private boolean isAdd;
    private int number;
    private int newNumber;
    private ECurrency banknoteDenomination;
    private IHeapOfIdenticalBankNotes newHeap;

    public ATMHeap(int number){
        this.number = number;
    }

    private ATMHeap(ECurrency banknoteDenomination, int number){
        this.banknoteDenomination = banknoteDenomination;
        this.number = number;
    }

    @Override
    public boolean add(IHeapOfIdenticalBankNotes heap) {
        this.isAdd = true;
        this.newHeap = heap;
        this.newNumber = number + heap.getNumber();
        return NumberDiapason.MAX_NUMBER >= newNumber;
    }

    @Override
    public boolean sub(IHeapOfIdenticalBankNotes heap) {
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
        return new ATMHeap(banknoteDenomination, number);
    }
}
