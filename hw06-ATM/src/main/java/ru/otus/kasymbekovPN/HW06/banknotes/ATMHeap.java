package ru.otus.kasymbekovPN.HW06.banknotes;

import ru.otus.kasymbekovPN.HW06.utils.Displayable;
import ru.otus.kasymbekovPN.HW06.utils.NumberDiapason;

/**
 * Класс, реализующий хип банкомата с банкнотами одного номинала.
 */
public class ATMHeap implements HeapOfIdenticalBanknotes, Displayable {
    /**
     * Флаг, указавыющий на то, было ли последняя проверка перед действием
     * на добавление (true) или изъяние (false) банкнот из хипа.
     */
    private boolean isAdd;

    /**
     * Текущее количество банкнот.
     */
    private int number;

    /**
     * Новое количество банкнот, вступает в силу после подтверждения
     * возможности успешного действия (добавления или изъятия) со всеми
     * хипами банкомата.
     */
    private int newNumber;

    /**
     * Номинал банкнот.
     */
    private Currency banknoteDenomination;

    /**
     * Внешний хип.
     */
    private HeapOfIdenticalBanknotes newHeap;

//    /**
//     * Конструктор.
//     * @param number количество банкнот
//     */
//    public ATMHeap(int number){
//        this.number = number;
//    }

    /**
     * Конструктор
     * @param banknoteDenomination номинал банкнот.
     * @param number количество банкнот.
     */
    public ATMHeap(Currency banknoteDenomination, int number){
        this.banknoteDenomination = banknoteDenomination;
        this.number = number;
    }

    /**
     * Метод, проверяющий возможность перености банкнот из
     * внешнего хипа в хип банкомата.
     * @param heap хип банкном, который нужно добавить.
     * @return Возможность операции.
     */
    @Override
    public boolean add(HeapOfIdenticalBanknotes heap) {
        this.isAdd = true;
        this.newHeap = heap;
        this.newNumber = number + heap.getNumber();
        return NumberDiapason.MAX_NUMBER >= newNumber;
    }

    /**
     * Метод, проверяющий возможность изъятия банкнот из
     * хипа банкомата.
     * @param heap хип банкнот, который нужно вычесть.
     * @return Возможность операции.
     */
    @Override
    public boolean sub(HeapOfIdenticalBanknotes heap) {
        this.isAdd = false;
        this.newHeap = heap;
        this.newNumber = number - heap.getNumber();
        return NumberDiapason.MIN_NUMBER <= newNumber;
    }

    /**
     * Подтверждение изменений.
     */
    @Override
    public void confirmChange() {
        number = newNumber;
        if (isAdd) {
            newHeap.confirmChange();
        }
    }

    /**
     * Геттер количества банкнот.
     * @return количество банкнот
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * @return Возвращает остаток денежных средств в хипе.
     */
    @Override
    public int get() {
        return getDenomination().getValue() * getNumber();
    }

    /**
     * Геттер номинала.
     * @return Номинал банкнот в хипе.
     */
    @Override
    public Currency getDenomination() {
        return banknoteDenomination;
    }

    /**
     * Выводит в консоль информацию о хипе.
     */
    @Override
    public void display() {
        System.out.println("Denomination : " + banknoteDenomination.getValue()
                + ", number : " + number + ", sum : " + get());
    }

    /**
     * Генерирует новый инстанс
     * @return новый инстанс
     */
    @Override
    public HeapOfIdenticalBanknotes makeNewInstance() {
        return new ATMHeap(this.banknoteDenomination, this.number);
    }

    /**
     * Генерирует новый инстанс
     * @param currency номинал банкнот нового инстанса
     * @return новый инстанс
     */
    @Override
    public HeapOfIdenticalBanknotes makeNewInstance(Currency currency) {
        return new ATMHeap(currency, this.number);
    }

    /**
     * Генерирует новый инстанс
     * @param currency номинал банкнот
     * @param number количетсво банкнот
     * @return новый инстанс
     */
    @Override
    public HeapOfIdenticalBanknotes makeNewInstance(Currency currency, int number) {
        return new ATMHeap(currency, number);
    }
}
