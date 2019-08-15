package ru.otus.kasymbekovPN.HW06.atm;

import ru.otus.kasymbekovPN.HW06.banknotes.*;
import ru.otus.kasymbekovPN.HW06.banknotes.Currency;
import ru.otus.kasymbekovPN.HW06.utils.SimplePair;

import java.util.*;

public class ATM {
    private BanknotesHeaps cells;

    public ATM(BanknotesHeaps cells){
        this.cells = cells;
    }

    public void display(){
        cells.display();
    }

    public ATMActionResult add(BanknotesHeaps heaps){
        boolean result = cells.add(heaps);
        return new ATMActionResult(cells, heaps, ATMAction.ADD, result);
    }

    public ATMActionResult sub(int money, HeapOfIdenticalBanknotes dummy){
        SimplePair<Boolean, BanknotesHeaps> pair = makeMinHeap(money, dummy);
        boolean result;
        if (pair.getFirst()){
            result = cells.sub(pair.getSecond());
            return new ATMActionResult(cells, pair.getSecond(), ATMAction.SUB, result);
        } else {
            return new ATMActionResult(cells, money, ATMAction.SUB);
        }
    }

    private SimplePair<Boolean, BanknotesHeaps> makeMinHeap(int money, HeapOfIdenticalBanknotes dummy){
        List<Currency> currencies = Arrays.asList(Currency.values());
        Collections.reverse(currencies);

        Map<Currency, HeapOfIdenticalBanknotes> heaps = new HashMap<>();

        int sum = 0;
        if (money > 0){
            int modulo = money;
            for (Currency currency : currencies) {
                HeapOfIdenticalBanknotes heap = cells.getHeaps().get(currency);
                int value = heap.getDenomination().getValue();
                int number = heap.getNumber();

                if (0 < number){
                    int perfectNumber = modulo / value;
                    if (0 < perfectNumber){
                        number = Math.min(number, perfectNumber);
                        HeapOfIdenticalBanknotes clone = dummy.clone();
                        clone.setNumber(number);
                        clone.setDenomination(currency);
                        heaps.put(currency, clone);

                        sum += number * value;
                        modulo = modulo - (number * value);
                    }
                }
            }
        }

        return new SimplePair<>(
                sum == money,
                BanknotesHeapsImpl.makeInstance((sum == money ? heaps : new HashMap<>()), dummy.clone())
        );
    }
}
