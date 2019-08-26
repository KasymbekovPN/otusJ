package ru.otus.kasymbekovPN.HW07.banknotes;

/**
 * Интерфейс для класса, хранящего хипы(совокупности банкнот
 * одного номинала) банкнот разного номинала.
 */
public interface BanknoteHeaps {

    /**
     * Метод для переноса всех банкнот из внешених хипов (<code>outsideHeap</code>
     * в собственные хипы инстанса.
     * @param outsideHeaps внешние хипы
     * @return Успешность операции
     */
    boolean add(BanknoteHeaps outsideHeaps);

    /**
    * Метод для переноса всех банкнот из собственных хипов инстанса
    * во внешние хипы (<code>outsideHeaps</code>)
    * @param outsideHeaps внешние хипы
    * @return Успешность операции
    */
    boolean sub(BanknoteHeaps outsideHeaps);

    /**
     * Внутреннее действие
     * @param outsideHeap внешний хип банкнот
     * @param isAdd true - сложение, false - вычитание
     * @param currency номинал банкнот
     * @return Успешность операции
     */
    boolean innerAction(BanknoteHeap outsideHeap, boolean isAdd, Currency currency);

    /**
     * Возвращает количество банкнот одного номинала
     * @param currency Номинал
     * @return Количество банкнот.
     */
    int getNumberOfBanknotes(Currency currency);
}
