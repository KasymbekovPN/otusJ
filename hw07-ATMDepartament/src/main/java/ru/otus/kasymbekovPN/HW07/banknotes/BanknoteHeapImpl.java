package ru.otus.kasymbekovPN.HW07.banknotes;

import ru.otus.kasymbekovPN.HW07.utils.Displayable;
import ru.otus.kasymbekovPN.HW07.utils.NumberDiapason;

/**
 * Класс, реализующий совокупность банкнот одного номинала.
 */
public class BanknoteHeapImpl implements BanknoteHeap, Displayable {

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
    private Currency denomination;

    /**
     * Внешний хип.
     */
    private BanknoteHeap outsideHeap;

    /**
     * Конструктор
     * @param denomination номинал банкнот
     * @param number количество банкнот
     */
    public BanknoteHeapImpl(Currency denomination, int number){
        this.denomination = denomination;
        this.number = number;
    }

    /**
     * Функция, осуществляющая подготовку и проверку возможности
     * перенести банкноты из <code>outsideHeap</code> во
     * внутренний хип банкнот реализующего класса.
     * @param outsideHeap внешний хип банкнот
     * @return Возможность операции
     */
    @Override
    public boolean prepareAddAction(BanknoteHeap outsideHeap) {
        this.newNumber = number + outsideHeap.getNumber();
        this.outsideHeap = outsideHeap;
        this.outsideHeap.setNewNumber(0);
        return NumberDiapason.MAX_NUMBER >= this.newNumber;
    }

    /**
     * Функция, осуществляющая подготовку и проверку возможности
     * перенести банкноты из внутреннего хипа банкнот реализующего
     * класса в <code>outsideHeap</code>.
     * @param outsideHeap внешний хип банкнот
     * @return Возможность операции
     */
    @Override
    public boolean prepareSubAction(BanknoteHeap outsideHeap) {
        this.newNumber = number - outsideHeap.getNumber();
        this.outsideHeap = outsideHeap;
        this.outsideHeap.setNewNumber(this.outsideHeap.getNumber());
        this.outsideHeap.setNumber(0);
        return NumberDiapason.MIN_NUMBER <= newNumber;
    }

    /**
     * Подтверждение изменений.
     */
    @Override
    public void confirmChange() {
        number = newNumber;
        if (outsideHeap != null)
        {
            outsideHeap.confirmChange();
            outsideHeap = null;
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
     * Сеттер количества банкнот
     * @param number количество банкнот
     */
    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @param newNumber Новое количесвто банкнот
     */
    @Override
    public void setNewNumber(int newNumber) {
        this.newNumber = newNumber;
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
        return denomination;
    }

    /**
    * Генерирует новый инстанс
    * @return новый инстанс
    */
    @Override
    public BanknoteHeap makeNewInstance() {
        return new BanknoteHeapImpl(denomination, number);
    }

    /**
    * Генерирует новый инстанс
    * @param denomination номинал банкнот нового инстанса
    * @return новый инстанс
    */
    @Override
    public BanknoteHeap makeNewInstance(Currency denomination) {
        return new BanknoteHeapImpl(denomination, number);
    }

    /**
    * Генерирует новый инстанс
    * @param denomination номинал банкнот
    * @param number количетсво банкнот
    * @return новый инстанс
    */
    @Override
    public BanknoteHeap makeNewInstance(Currency denomination, int number) {
        return new BanknoteHeapImpl(denomination, number);
    }

    /**
    * Выводит в консоль информацию о хипе.
    */
    @Override
    //<<< ??? Правильный вывод в консоль.
    public void display() {
        System.out.println("Denomination : " + denomination.getValue()
                + ", number : " + number + ", sum : " + get());
    }
}
