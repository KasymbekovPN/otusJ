package ru.otus.kasymbekovPN.HW06.banknotes;

public class ATMHeap implements IHeapOfIdenticalBankNotes{
    private final static int MIN_NUMBER = 0;
    private final static int MAX_NUMBER = 8_000;

    private boolean isAdd;
    private int number;
    private int newNumber;
    private ECurrency banknoteDenomination;
    private IHeapOfIdenticalBankNotes newHeap;

    public ATMHeap(ECurrency banknoteDenomination, int number){
        this.banknoteDenomination = banknoteDenomination;
        this.number = number;
    }

    public ATMHeap(IHeapOfIdenticalBankNotes heap){
        this.banknoteDenomination = heap.getDenomination();
        this.number = heap.getNumber();
    }

    @Override
    public boolean add(IHeapOfIdenticalBankNotes heap) {
        this.isAdd = true;
        this.newHeap = heap;
        this.newNumber = number + heap.getNumber();
        return MAX_NUMBER >= newNumber;
    }

    @Override
    public boolean sub(IHeapOfIdenticalBankNotes heap) {
        this.isAdd = false;
        this.newHeap = heap;
        this.newNumber = number - heap.getNumber();
        return MIN_NUMBER <= newNumber;
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
    public boolean isEmpty() {
        return MIN_NUMBER == number;
    }

    @Override
    public boolean isFull() {
        return MAX_NUMBER == number;
    }

    //< default
    @Override
    public void display() {
        System.out.println("Номинал : " + banknoteDenomination + ", количество : " + number + ", сумма : " + get());
    }

    @Override
    public IHeapOfIdenticalBankNotes clone() {
        return new ATMHeap(banknoteDenomination, number);
    }
}
