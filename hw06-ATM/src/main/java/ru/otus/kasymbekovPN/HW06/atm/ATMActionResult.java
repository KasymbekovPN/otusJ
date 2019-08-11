package ru.otus.kasymbekovPN.HW06.atm;

import ru.otus.kasymbekovPN.HW06.banknotes.IBanknotesHeaps;

public class ATMActionResult {

    static private final int HUMAN_MONEY_DEFAULT_VALUE = -1;

    private boolean result;
    private int humanMoney = -HUMAN_MONEY_DEFAULT_VALUE;
    private IBanknotesHeaps atmHeap;
    private IBanknotesHeaps newHeap;

    ATMActionResult(IBanknotesHeaps atmHeap, IBanknotesHeaps newHeap, boolean result){
        this.result = result;
        this.atmHeap = atmHeap;
        this.newHeap = newHeap;
    }

    ATMActionResult(IBanknotesHeaps atmHeap, int humanMoney){
        this.result = false;
        this.atmHeap = atmHeap;
        this.humanMoney = humanMoney;
    }

    public void display(){
        System.out.println("----------");
        System.out.println("Result : " + result);
        System.out.println("ATM");
        atmHeap.display();
        System.out.println("HUMAN");
        if (humanMoney != HUMAN_MONEY_DEFAULT_VALUE){
            System.out.println("Human's money : " + humanMoney);
        } else {
            newHeap.display();
        }

    }
}
