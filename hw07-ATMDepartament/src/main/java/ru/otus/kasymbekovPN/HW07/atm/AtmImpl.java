package ru.otus.kasymbekovPN.HW07.atm;

import ru.otus.kasymbekovPN.HW07.banknotes.BanknoteHeap;
import ru.otus.kasymbekovPN.HW07.banknotes.BanknoteHeaps;
import ru.otus.kasymbekovPN.HW07.banknotes.BanknoteHeapsImpl;
import ru.otus.kasymbekovPN.HW07.utils.Displayable;
import ru.otus.kasymbekovPN.HW07.utils.Observable;
import ru.otus.kasymbekovPN.HW07.utils.Observer;
import ru.otus.kasymbekovPN.HW07.utils.SimplePair;
import ru.otus.kasymbekovPN.HW07.banknotes.Currency;

import java.util.*;

/**
 * Класс, реализующий банктомат.
 */
public class AtmImpl implements Atm, Displayable, Observer {

    /**
     * Яейки банкомата.
     */
    private BanknoteHeaps cells;

    /**
     * Наблюдаемый объект
     */
    private Observable observable;

    /**
     * Конструктор.
     * @param cells Ячейки банкомата.
     */
    public AtmImpl(BanknoteHeaps cells){
        this.cells = cells;
    }

    /**
     * Метод, обработывающий попытку добавления банкнот в ячейки банкомата.
     * @param outsideHeaps хипы, добавляемых банкнот.
     * @return Результат действия.
     */
    @Override
    public AtmActionResult add(BanknoteHeaps outsideHeaps) {
        boolean result = cells.add(outsideHeaps);
        return new AtmActionResult(cells, outsideHeaps, AtmAction.ADD, result);
    }

    /**
     * Метод, обрабатывающий попытку изъятия определенной суммы банкнот из ячеек
     * банкомата. Здесь сначала из заданной суммы (<code>money</code>) формируется
     * хипы банкнот, если хипы сформированы успешно, происходит изъятие.
     * @param money Запрашиваемая сумма.
     * @param dummy Болванка дл яформировани хипов.
     * @return Результат действия.
     */
    @Override
    public AtmActionResult sub(int money, BanknoteHeap dummy) {
        SimplePair<Boolean, BanknoteHeaps> pair = makeMinHeap(money, dummy);
        boolean result;
        if (pair.getFirst()){
            result = cells.sub(pair.getSecond());
            return new AtmActionResult(cells, pair.getSecond(), AtmAction.SUB, result);
        } else {
            return new AtmActionResult(cells, money);
        }
    }

    /**
     * Выводит информацию о содержимом ячеек банкомата.
     */
    @Override
    public void display() {
        ((Displayable)cells).display();
    }

    @Override
    public void setConnection(Observable o) {
        observable = o;
    }

    @Override
    public int getBalance() {
        //< implement
        return 0;
    }

    @Override
    public void reset() {
        //< implement
    }

    /**
     * Формирование хипов банкнот из запрошенной суммы.
     * @param money Запрошенная сумма.
     * @param dummy Болванка для формирования хипов.
     * @return Хипы банкнот.
     */
    private SimplePair<Boolean, BanknoteHeaps> makeMinHeap(int money, BanknoteHeap dummy){
        List<Currency> currencies = Arrays.asList(Currency.values());
        Collections.reverse(currencies);

        Map<Currency, BanknoteHeap> heaps = new HashMap<>();

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
                BanknoteHeapsImpl.makeInstance((sum == money ? heaps : new HashMap<>()), dummy.makeNewInstance())
        );
    }
}
