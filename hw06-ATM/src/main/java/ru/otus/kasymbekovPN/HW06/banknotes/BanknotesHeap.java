package ru.otus.kasymbekovPN.HW06.banknotes;

import ru.otus.kasymbekovPN.HW06.utils.Displayable;
import ru.otus.kasymbekovPN.HW06.utils.NumberDiapason;

/**
 * Класс, реализующий хип банкнот одного номинала,
 * внешний отностительно банкомата.
 */
public class BanknotesHeap implements HeapOfIdenticalBanknotes, Displayable {
    /**
     * Количество банкнот.
     */

    private int number;
    /**
     * Номинал банкнот.
     */
    final private Currency banknoteDenomination;

    /**
     * Конструктор
     * @param number количество банкнот
     */
    //< need ?
    public BanknotesHeap(int number){
        this.number = number;
        this.banknoteDenomination = Currency.VALUE_10;
    }

    /**
     * Конструктор
     * @param banknoteDenomination номинал банкнот
     * @param number количество банкнот
     */
    public BanknotesHeap(Currency banknoteDenomination, int number){
        this.banknoteDenomination = banknoteDenomination;
        this.number = number;
    }

    /**
     * Метод, подтверждающий изменения.
     */
    @Override
    public void confirmChange() {
        number = NumberDiapason.MIN_NUMBER;
    }

    /**
     * Геттер количества банкнот.
     * @return Количество банкнот.
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * Сеттер количество банкнот.
     * @param number количество банкнот
     */
    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return Возвращает останок денежных средств хипа.
     */
    @Override
    public int get() {
        return getDenomination().getValue() * getNumber();
    }

    /**
     * Геттер номинала банкнот.
     * @return Номинал банкнот.
     */
    @Override
    public Currency getDenomination() {
        return banknoteDenomination;
    }

    //<
//    /**
//     * Сеттер номинала банкнот.
//     * @param denomination номинал банкнот
//     */
//    @Override
//    public void setDenomination(Currency denomination) {
//        banknoteDenomination = denomination;
//    }

    /**
     * Выводит в консоль информацию о хипе.
     */
    @Override
    public void display() {
        System.out.println("Denomination : " + banknoteDenomination.getValue()
                + ", number : " + number + ", sum : " + get());
    }

    //<
//    /**
//     * Клонирует инстанс класса, реализующего интерфейс
//     * @return Клон инстанса.
//     */
//    @Override
//    public HeapOfIdenticalBanknotes clone() {
//        return new BanknotesHeap(banknoteDenomination, number);
//    }

    /**
     * Генерирует новый инстанс
     * @return новый инстанс
     */
    @Override
    public HeapOfIdenticalBanknotes makeNewInstance() {
        return new BanknotesHeap(this.banknoteDenomination, this.number);
    }

    /**
     * Генерирует новый инстанс
     * @param currency номинал банкнот нового инстанса
     * @return новый инстанс
     */
    @Override
    public HeapOfIdenticalBanknotes makeNewInstance(Currency currency) {
        return new BanknotesHeap(currency, this.number);
    }

    /**
     * Генерирует новый инстанс
     * @param currency номинал банкнот
     * @param number количетсво банкнот
     * @return новый инстанс
     */
    @Override
    public HeapOfIdenticalBanknotes makeNewInstance(Currency currency, int number) {
        return new BanknotesHeap(currency, number);
    }
}
