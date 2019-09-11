package ru.otus.kasymbekovPN.HW07.department.atm;

import ru.otus.kasymbekovPN.HW07.department.banknotes.BanknoteHeap;
import ru.otus.kasymbekovPN.HW07.department.banknotes.BanknoteHeaps;

/**
 * Интерфейс для классов, реализующих функционал банкомата.
 */
public interface Atm {
    /**
     * Метод, обработывающий попытку добавления банкнот в ячейки банкомата.
     * @param outsideHeaps хипы, добавляемых банкнот.
     * @return Результат действия.
     */
    AtmActionResult add(BanknoteHeaps outsideHeaps);

    /**
     * Метод, обрабатывающий попытку изъятия определенной суммы банкнот из ячеек
     * банкомата. Здесь сначала из заданной суммы (<code>money</code>) формируется
     * хипы банкнот, если хипы сформированы успешно, происходит изъятие.
     * @param money Запрашиваемая сумма.
     * @param dummy Болванка дл яформировани хипов.
     * @return Результат действия.
     */
    AtmActionResult sub(int money, BanknoteHeap dummy);

    /**
     * Геттер идентификатора банктомата
     * @return Идентификатор
     */
    int getID();

    /**
     * Геттер баланса банкомата
     * @return баланс банкомата
     */
    int getBalance();
}
