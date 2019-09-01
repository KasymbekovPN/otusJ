package ru.otus.kasymbekovPN.HW07.atm;

import ru.otus.kasymbekovPN.HW07.banknotes.BanknoteHeaps;
import ru.otus.kasymbekovPN.HW07.banknotes.Currency;
import ru.otus.kasymbekovPN.HW07.utils.Displayable;

/**
 * Класс, хранящий результат операции банкомата.
 */
public class AtmActionResult {

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
    private BanknoteHeaps atmHeap;

    /**
     * Хипы банкнот, которые человек хочет положить.
     */
    private BanknoteHeaps newHeap;

    /**
     * Действие.
     */
    private AtmAction action;

    /**
     * Конструктор. Для создания результат добавления, как успешного, так и нет; и для
     * создания результата успешного изъятия.
     * @param atmHeap хипы банкнот банктомата
     * @param newHeap хипы банкнот человека.
     * @param action действие
     * @param result результат действия
     */
    public AtmActionResult(BanknoteHeaps atmHeap, BanknoteHeaps newHeap, AtmAction action, boolean result){
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
    public AtmActionResult(BanknoteHeaps atmHeap, int humanMoney){
        this.result = false;
        this.atmHeap = atmHeap;
        this.humanMoney = humanMoney;
        this.action = AtmAction.SUB;
    }

    /**
     * Выводит в консоль информацию о действии.
     */
    public void display(){
        System.out.println("##### Check Number : " + checkNumber++ + " #####");
        System.out.println("Action : " + action.getName());
        System.out.println("Result : " + (result ? "SUCCESS" : "FAILURE"));

        System.out.println("### ATM ###");
        ((Displayable)atmHeap).display();

        System.out.println("### HUMAN ###");
        if (humanMoney != HUMAN_MONEY_DEFAULT_VALUE){
            System.out.println("Human's money : " + humanMoney);
        } else {
            ((Displayable)newHeap).display();
        }

        System.out.println("\n");
    }

    public boolean eq(AtmActionResult otherActionResult){
        boolean res = result == otherActionResult.result;
        res &= action.equals(otherActionResult.action);

        for (Currency currency : Currency.values()) {
            res &= atmHeap.getNumberOfBanknotes(currency) == otherActionResult.atmHeap.getNumberOfBanknotes(currency);
        }

        res &= humanMoney == otherActionResult.humanMoney;

        if (humanMoney == HUMAN_MONEY_DEFAULT_VALUE)
        {
            for (Currency currency : Currency.values()) {
                res &= newHeap.getNumberOfBanknotes(currency) == otherActionResult.newHeap.getNumberOfBanknotes(currency);
            }
        }

        return res;
    }
}

