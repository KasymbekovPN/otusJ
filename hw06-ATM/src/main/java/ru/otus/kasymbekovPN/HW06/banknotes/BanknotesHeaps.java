package ru.otus.kasymbekovPN.HW06.banknotes;

/**
 * Интерфейс для класса, хранящего хипы банкнот разных номиналов.
 */
public interface BanknotesHeaps {
    /**
     * Метод для переноса всех банкнот из внешних хипов (<code>heaps</code>)
     * в собственные хипы инстанса.
     * @param heaps внешний хипы.
     * @return Успешность операции
     */
    boolean add(BanknotesHeaps heaps);

    /**
     * Метод для переноса всех банкнот из собственных хипов инстанса
     * во внешние хипы (<code>heaps</code>)
     * @param heaps внешние хипы
     * @return Успешность операции
     */
    boolean sub(BanknotesHeaps heaps);

    /**
     * Внутреннее действие
     * @param heap внешний хип банкнот
     * @param isAdd true - сложение, false - вычитание
     * @param currency номинал банкнот
     * @return Успешность операции
     */
    boolean innerAction(HeapOfIdenticalBanknotes heap, boolean isAdd, Currency currency);

    /**
     * Возвращает количество банкнот одного номинала
     * @param currency Номинал
     * @return Количество банкнот.
     */
    int getNumberOfBanknotes(Currency currency);
}
