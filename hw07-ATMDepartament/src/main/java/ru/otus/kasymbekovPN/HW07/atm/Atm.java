package ru.otus.kasymbekovPN.HW07.atm;

import ru.otus.kasymbekovPN.HW07.banknotes.BanknoteHeap;
import ru.otus.kasymbekovPN.HW07.banknotes.BanknoteHeaps;

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
}
