package ru.otus.kasymbekovPN.HW06.atm;

import ru.otus.kasymbekovPN.HW06.banknotes.*;
import ru.otus.kasymbekovPN.HW06.banknotes.Currency;
import ru.otus.kasymbekovPN.HW06.utils.Displayable;
import ru.otus.kasymbekovPN.HW06.utils.SimplePair;

import java.util.*;

/**
 * Класс, реализующий банктомат.
 */
public class ATM {
    /**
     * Яейки банкомата.
     */
    private BanknotesHeaps cells;

    /**
     * Конструктор.
     * @param cells Ячейки банкомата.
     */
    public ATM(BanknotesHeaps cells){
        this.cells = cells;
    }

    /**
     * Выводит информацию о содержимом ячеек банкомата.
     */
    public void display(){
        ((Displayable)cells).display();
    }

    /**
     * Метод, обработывающий попытку добавления банкнот в ячейки банкомата.
     * @param heaps хипы, добавляемых банкнот.
     * @return Результат действия.
     */
    public ATMActionResult add(BanknotesHeaps heaps){
        boolean result = cells.add(heaps);
        return new ATMActionResult(cells, heaps, ATMAction.ADD, result);
    }

    /**
     * Метод, обрабатывающий попытку изъятия определенной суммы банкнот из ячеек
     * банкомата. Здесь сначала из заданной суммы (<code>money</code>) формируется
     * хипы банкнот, если хипы сформированы успешно, происходит изъятие.
     * @param money Запрашиваемая сумма.
     * @param dummy Болванка дл яформировани хипов.
     * @return Результат действия.
     */
    public ATMActionResult sub(int money, HeapOfIdenticalBanknotes dummy){
        SimplePair<Boolean, BanknotesHeaps> pair = makeMinHeap(money, dummy);
        boolean result;
        if (pair.getFirst()){
            result = cells.sub(pair.getSecond());
            return new ATMActionResult(cells, pair.getSecond(), ATMAction.SUB, result);
        } else {
            return new ATMActionResult(cells, money);
        }
    }

    /**
     * Формирование хипов банкнот из запрошенной суммы.
     * @param money Запрошенная сумма.
     * @param dummy Болванка для формирования хипов.
     * @return Хипы банкнот.
     */
    private SimplePair<Boolean, BanknotesHeaps> makeMinHeap(int money, HeapOfIdenticalBanknotes dummy){
        List<Currency> currencies = Arrays.asList(Currency.values());
        Collections.reverse(currencies);

        Map<Currency, HeapOfIdenticalBanknotes> heaps = new HashMap<>();

        int sum = 0;
        if (money > 0){
            int modulo = money;
            for (Currency currency : currencies) {
                int value = currency.getValue();
                int number = cells.getNumberOfBanknotes(currency);

                if (0 < number){
                    int perfectNumber = modulo / value;
                    if (0 < perfectNumber){
                        number = Math.min(number, perfectNumber);
                        heaps.put(currency, dummy.makeNewInstance(currency, number));

                        sum += number * value;
                        modulo = modulo - (number * value);
                    }
                }
            }
        }

        return new SimplePair<>(
                sum == money,
                BanknotesHeapsImpl.makeInstance((sum == money ? heaps : new HashMap<>()), dummy.makeNewInstance())
        );
    }
}
