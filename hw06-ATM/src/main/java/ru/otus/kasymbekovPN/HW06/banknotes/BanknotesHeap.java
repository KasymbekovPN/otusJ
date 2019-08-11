package ru.otus.kasymbekovPN.HW06.banknotes;

public class BanknotesHeap implements IHeapOfIdenticalBankNotes {
    private final static int MIN_NUMBER = 0;

    private int number;
    private ECurrency banknoteDenomination;

    public BanknotesHeap(int number){
        this.number = number;
    }

    public BanknotesHeap(ECurrency banknoteDenomination, int number){
        this.banknoteDenomination = banknoteDenomination;
        this.number = number;
    }

    //<
//    public BanknotesHeap(IHeapOfIdenticalBankNotes heap){
//        this.banknoteDenomination = heap.getDenomination();
//        this.number = heap.getNumber();
//    }

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
        number = MIN_NUMBER;
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
    public boolean isEmpty() {
        return MIN_NUMBER == number;
    }

    @Override
    public boolean isFull() {
        return !isEmpty();
    }

    //< default
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
