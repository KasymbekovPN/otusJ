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
        return new ATMActionResult(cells, heaps, result);
    }
    //<
//    public boolean add(IBanknotesHeaps heaps){
//        return cells.add(heaps);
//    }

    public ATMActionResult sub(int money){
        SimplePair<Boolean, IBanknotesHeaps> pair = makeMinHeap(money);
        boolean result = false;
        if (pair.getFirst()){
            result = cells.sub(pair.getSecond());
            return new ATMActionResult(cells, pair.getSecond(), result);
        } else {
            return new ATMActionResult(cells, money);
        }



        //<
//        IBanknotesHeaps heaps = makeMinHeap(money);
//        boolean result = cells.sub(heaps);
//        return new ATMActionResult(cells, heaps, result);
    }
    //<
//    public IBanknotesHeaps sub(int money){
//        IBanknotesHeaps heaps = makeMinHeap(money);
//        cells.sub(heaps);
//        //<
////        if (null != heaps){
////            cells.sub(heaps);
////        }
//        return heaps;
//    }

    //<
//    public boolean sub(int money, IBanknotesHeaps heaps){
//
//        System.out.println(heaps);
//
//        heaps = makeMinHeap(money);
//
//
//        if (null != heaps){
//
//            System.out.println("***");
//            heaps.display();
//            cells.sub(heaps);
//            System.out.println("******");
//            heaps.display();
//
//            System.out.println(heaps);
//
//            return true;
//
//
////            return cells.sub(heaps);
//        }
//        //<
////        IBanknotesHeaps iBanknotesHeaps = makeMinHeap(money);
////
////        if (null != iBanknotesHeaps){
////            //<
//////            System.out.println("+++++");
//////            iBanknotesHeaps.display();
////            heaps = iBanknotesHeaps;
////            return cells.sub(heaps);
////        }
//
//        return false;
//    }

    private SimplePair<Boolean, IBanknotesHeaps> makeMinHeap(int money){
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
                        heaps.put(currency, new BanknotesHeap(currency, number));
                        //<
                        sum += number * value;
                        modulo = modulo - (number * value);
                    }
                }
            }
        }

        //<
//        System.out.println(sum + " : " + money);
        //< solid ?
//        return sum == money ? BanknotesHeaps.makeInstance(heaps) : null;
        //<
//        return BanknotesHeaps.makeInstance((sum == money ? heaps : new HashMap<>()), new BanknotesHeap(ECurrency.VALUE_10, 0));

        return new SimplePair<>(sum == money,
                BanknotesHeaps.makeInstance((sum == money ? heaps : new HashMap<>()), new BanknotesHeap(ECurrency.VALUE_10, 0)));
    }

    //<
//    //< money <= 0 ???
//    private IBanknotesHeaps makeMinHeap(int money){
//        List<ECurrency> currencies = new ArrayList<>(ECurrency.getAllItem());
//        Collections.reverse(currencies);
//
//        Map<ECurrency, IHeapOfIdenticalBankNotes> heaps = new HashMap<>();
//
//        int sum = 0;
//        if (money > 0){
//            int modulo = money;
//            for (ECurrency currency : currencies) {
//
//                //<
////                System.out.println("++ : " + currency);
//
//                IHeapOfIdenticalBankNotes heap = cells.getHeaps().get(currency);
//                int value = heap.getDenomination().getValue();
//                int number = heap.getNumber();
//                //<
////            int numberForHeap = 0;
//
//                if (0 < number){
//
//                    //<
////                    System.out.println("+++ : " + currency + " : " + number);
//
//                    int perfectNumber = modulo / value;
//                    if (0 < perfectNumber){
//                        number = Math.min(number, perfectNumber);
//
//                        //< solid ?
//                        heaps.put(currency, new BanknotesHeap(currency, number));
//                        //<
////                    numberForHeap = number;
//
//                        sum += number * value;
//
//                        //<
////                        System.out.println("++++ : modulo : " + modulo + ", number : " + number);
//                        modulo = modulo - (number * value);
//                        //<
////                        System.out.println("+++++ modulo : " + modulo);
//                    }
//                }
//
//                //< solid?
////            heaps.put(currency, new BanknotesHeap(currency, numberForHeap));
//            }
//        }
//
//        //<
//        System.out.println(sum + " : " + money);
//
//        //< solid ?
////        return sum == money ? BanknotesHeaps.makeInstance(heaps) : null;
//        //<
//
//
//        return BanknotesHeaps.makeInstance((sum == money ? heaps : new HashMap<>()), new BanknotesHeap(ECurrency.VALUE_10, 0));
//    }


    //<
//    private Map<ECurrency, IHeapOfIdenticalBankNotes> cells;
//
//    public ATM(Map<ECurrency, IHeapOfIdenticalBankNotes> cells){
//        this.cells = cells;
//    }
//
//    public boolean putMoney(Map<ECurrency, IHeapOfIdenticalBankNotes> cells){
//        for(Map.Entry<ECurrency, IHeapOfIdenticalBankNotes> cell)
//    }
//
//    public Map<ECurrency, IHeapOfIdenticalBankNotes> takeMoney(int money){
//
//    }
//
//    public void displayMoneyStorage(){
//
//    }
}
