package ru.otus.kasymbekovPN.HW07.atm.visitor;

import ru.otus.kasymbekovPN.HW07.atm.AtmImpl;

public class TotalBalanceVisitor implements Visitor {

    private int balance;

    public TotalBalanceVisitor() {
        this.balance = 0;
    }

    @Override
    public void visit(AtmImpl atmImpl) {
        balance += atmImpl.getBalance();
    }

    public int getBalance() {
        return balance;
    }
}
