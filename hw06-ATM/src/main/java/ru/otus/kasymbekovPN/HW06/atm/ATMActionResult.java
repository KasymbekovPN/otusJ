package ru.otus.kasymbekovPN.HW06.atm;

import ru.otus.kasymbekovPN.HW06.banknotes.BanknotesHeaps;

/**
 * Класс, хранящий результат операции банкомата.
 */
public class ATMActionResult {

    /**
     * Умолчательная сумма наличных, которую человек хочет снять.
     * Задаётя отричательным (невозможных) значением, для того чтобы было возможно понять,
     * какую информацию выводить: количество наличности (int), которую человек хочет
     * снять или информацию о хипах банкнот, которые человек хочет положить в банкомат.
     */
    static private final int HUMAN_MONEY_DEFAULT_VALUE = -1;

    /**
     * Номер чека.
     */
    static private int checkNumber = 1;

    /**
     * Результат опредации (true - успешно)
     */
    private boolean result;

    /**
     * Количество денег, которое человек хочет снять.
     */
    private int humanMoney = HUMAN_MONEY_DEFAULT_VALUE;

    /**
     * Хипы банкнот банкомата.
     */
    private BanknotesHeaps atmHeap;

    /**
     * Хипы банкнот, которые человек хочет положить.
     */
    private BanknotesHeaps newHeap;

    /**
     * Действие.
     */
    private ATMAction action;

    /**
     * Конструктор. Для создания результат добавления, как успешного, так и нет; и для
     * создания результата успешного изъятия.
     * @param atmHeap хипы банкнот банктомата
     * @param newHeap хипы банкнот человека.
     * @param action действие
     * @param result результат действия
     */
    ATMActionResult(BanknotesHeaps atmHeap, BanknotesHeaps newHeap, ATMAction action, boolean result){
        this.result = result;
        this.atmHeap = atmHeap;
        this.newHeap = newHeap;
        this.action = action;
    }

    /**
     * Конструктор. Для создания результат неуспешного изъяния.
     * @param atmHeap хип банкнот банкомата
     * @param humanMoney сумма денег, которую человек хосет снять.
     */
    ATMActionResult(BanknotesHeaps atmHeap, int humanMoney){
        this.result = false;
        this.atmHeap = atmHeap;
        this.humanMoney = humanMoney;
        this.action = ATMAction.SUB;
    }

    /**
     * Выводит в консоль информацию о действии.
     */
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
