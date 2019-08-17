package ru.otus.kasymbekovPN.HW06.banknotes;

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

    /**
     * Геттер хипов банкнот.
     * @return Хипы банкнот.
     */
    Map<Currency, HeapOfIdenticalBanknotes> getHeaps();

    /**
     * Метод, выводящий информацию о хипах в консом.
     */
    void display();
}
