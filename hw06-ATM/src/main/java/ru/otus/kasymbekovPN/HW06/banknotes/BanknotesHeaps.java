package ru.otus.kasymbekovPN.HW06.banknotes;

import javax.swing.plaf.ColorUIResource;
import java.util.Map;

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
     * во внешние хипы (<code>heaps</code>)ю
     * @param heaps внешние хипы
     * @return Успешность операции
     */
    boolean sub(BanknotesHeaps heaps);

    //<
//    /**
//     * Геттер хипов банкнот.
//     * @return Хипы банкнот.
//     */
//    Map<Currency, HeapOfIdenticalBanknotes> getHeaps();

    //<
//    /**
//     * Возвращает хип банкнот, соответствующий номиналу
//     * @param currency номинал банкнот
//     * @return Хип банкнот
//     */
//    HeapOfIdenticalBanknotes getHeap(Currency currency);

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
