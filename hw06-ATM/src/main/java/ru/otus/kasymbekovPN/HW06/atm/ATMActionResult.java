package ru.otus.kasymbekovPN.HW06.atm;

import ru.otus.kasymbekovPN.HW06.banknotes.IBanknotesHeaps;

public class ATMActionResult {

    static private final int HUMAN_MONEY_DEFAULT_VALUE = -1;

    static private int checkNumber = 1;

    private boolean result;
    private int humanMoney = HUMAN_MONEY_DEFAULT_VALUE;
    private IBanknotesHeaps atmHeap;
    private IBanknotesHeaps newHeap;
    private ATMAction action;

    ATMActionResult(IBanknotesHeaps atmHeap, IBanknotesHeaps newHeap, ATMAction action, boolean result){
        this.result = result;
        this.atmHeap = atmHeap;
        this.newHeap = newHeap;
        this.action = action;
    }

    ATMActionResult(IBanknotesHeaps atmHeap, int humanMoney, ATMAction action){
        this.result = false;
        this.atmHeap = atmHeap;
        this.humanMoney = humanMoney;
        this.action = action;
    }

    public void display(){
        System.out.println("##### Check Number : " + checkNumber++ + " #####");
        System.out.println("Action : " + action.getName());
        System.out.println("Result : " + (result ? "SUCCESS" : "FAILURE"));

        System.out.println("### ATM ###");
        atmHeap.display();

        System.out.println("### HUMAN ###");
        if (humanMoney != HUMAN_MONEY_DEFAULT_VALUE){
            System.out.println("Human's money : " + humanMoney);
        } else {
            newHeap.display();
        }

        System.out.println("\n");
    }
}
