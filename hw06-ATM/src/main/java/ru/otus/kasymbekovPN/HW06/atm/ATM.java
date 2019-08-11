package ru.otus.kasymbekovPN.HW06.atm;

import ru.otus.kasymbekovPN.HW06.banknotes.*;
import ru.otus.kasymbekovPN.HW06.utils.SimplePair;

import java.util.*;

public class ATM {
    private IBanknotesHeaps cells;

    public ATM(IBanknotesHeaps cells){
        this.cells = cells;
    }

    public void display(){
        cells.display();
    }

    public ATMActionResult add(IBanknotesHeaps heaps){
        boolean result = cells.add(heaps);
        return new ATMActionResult(cells, heaps, ATMAction.ADD, result);
    }

    public ATMActionResult sub(int money, IHeapOfIdenticalBankNotes dummy){
        SimplePair<Boolean, IBanknotesHeaps> pair = makeMinHeap(money, dummy);
        boolean result;
        if (pair.getFirst()){
            result = cells.sub(pair.getSecond());
            return new ATMActionResult(cells, pair.getSecond(), ATMAction.SUB, result);
        } else {
            return new ATMActionResult(cells, money, ATMAction.SUB);
        }
    }

    private SimplePair<Boolean, IBanknotesHeaps> makeMinHeap(int money, IHeapOfIdenticalBankNotes dummy){
        List<ECurrency> currencies = new ArrayList<>(ECurrency.getAllItem());
        Collections.reverse(currencies);

        Map<ECurrency, IHeapOfIdenticalBankNotes> heaps = new HashMap<>();

        int sum = 0;
        if (money > 0){
            int modulo = money;
            for (ECurrency currency : currencies) {
                IHeapOfIdenticalBankNotes heap = cells.getHeaps().get(currency);
                int value = heap.getDenomination().getValue();
                int number = heap.getNumber();

                if (0 < number){
                    int perfectNumber = modulo / value;
                    if (0 < perfectNumber){
                        number = Math.min(number, perfectNumber);

                        //< solid ?
//                        heaps.put(currency, new BanknotesHeap(currency, number));
                        //<
                        IHeapOfIdenticalBankNotes clone = dummy.clone();
                        clone.setNumber(number);
                        clone.setDenomination(currency);
                        heaps.put(currency, clone);

                        sum += number * value;
                        modulo = modulo - (number * value);
                    }
                }
            }
        }

        IHeapOfIdenticalBankNotes zero = dummy.clone();
        return new SimplePair<>(sum == money, BanknotesHeaps.makeInstance((sum == money ? heaps : new HashMap<>()), zero));
        //<
//        return new SimplePair<>(sum == money,
//                BanknotesHeaps.makeInstance((sum == money ? heaps : new HashMap<>()), new BanknotesHeap(0)));
        //<
//        return new SimplePair<>(sum == money,
//                BanknotesHeaps.makeInstance((sum == money ? heaps : new HashMap<>()), new BanknotesHeap(ECurrency.VALUE_10, 0)));
    }
}
