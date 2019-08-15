package ru.otus.kasymbekovPN.HW06.banknotes;

import ru.otus.kasymbekovPN.HW06.utils.NumberDiapason;

import java.util.*;

public class BanknotesHeapsImpl implements BanknotesHeaps {
    private Map<Currency, HeapOfIdenticalBanknotes> heaps;

    public static BanknotesHeapsImpl makeInstance(Map<Currency, HeapOfIdenticalBanknotes> heaps,
                                                  HeapOfIdenticalBanknotes dummy){

        for (Currency currency : Currency.values()) {
            if (!heaps.containsKey(currency)){
                dummy.setDenomination(currency);
                heaps.put(currency, dummy.clone());
            } else {
                heaps.get(currency).setDenomination(currency);
            }
        }

        return new BanknotesHeapsImpl(heaps);
    }

    public static BanknotesHeapsImpl makeInstance(int number_10, int number_50, int number_100,
                                                  int number_200, int number_500, int number_1000,
                                                  int number_2000, int number_5000,
                                                  HeapOfIdenticalBanknotes dummy){
        List<Integer> numberList = new ArrayList<>(){{
            add(number_10);
            add(number_50);
            add(number_100);
            add(number_200);
            add(number_500);
            add(number_1000);
            add(number_2000);
            add(number_5000);
        }};

        Currency[] allItems = Currency.values();
        Map<Currency, HeapOfIdenticalBanknotes> heaps = new HashMap<>();

        for (int i = 0; i < allItems.length; i++){
            Currency currency = allItems[i];
            int number = NumberDiapason.putInRange(numberList.get(i));

            dummy.setNumber(number);
            dummy.setDenomination(currency);
            heaps.put(currency, dummy.clone());
        }

        return new BanknotesHeapsImpl(heaps);
    }

    public static BanknotesHeapsImpl makeInstance(int number, HeapOfIdenticalBanknotes dummy){
        final Currency[] allItems = Currency.values();
        Map<Currency, HeapOfIdenticalBanknotes> heaps = new HashMap<>();

        number = NumberDiapason.putInRange(number);
        for (Currency currency : allItems) {
            dummy.setNumber(number);
            dummy.setDenomination(currency);
            heaps.put(currency, dummy.clone());
        }

        return new BanknotesHeapsImpl(heaps);
    }

    private BanknotesHeapsImpl(Map<Currency, HeapOfIdenticalBanknotes> heaps){
        this.heaps = heaps;
    }

    public Map<Currency, HeapOfIdenticalBanknotes> getHeaps() {
        return heaps;
    }

    @Override
    public boolean add(BanknotesHeaps heaps) {
        return action(heaps, true);
    }

    @Override
    public boolean sub(BanknotesHeaps heaps) {
        return action(heaps, false);
    }

    private boolean action(BanknotesHeaps heaps, boolean isAdd){
        boolean success = true;
        Set<HeapOfIdenticalBanknotes> thisHeaps = new HashSet<>();

        for (Map.Entry<Currency, HeapOfIdenticalBanknotes> entry : heaps.getHeaps().entrySet()){
            Currency key = entry.getKey();
            HeapOfIdenticalBanknotes thisHeap = this.heaps.get(key);
            HeapOfIdenticalBanknotes otherHeap = entry.getValue();

            success &= (isAdd ? thisHeap.add(otherHeap) : thisHeap.sub(otherHeap));

            thisHeaps.add(thisHeap);
        }

        if (success){
            for (HeapOfIdenticalBanknotes thisHeap : thisHeaps) {
                thisHeap.confirmChange();
            }
        }

        return success;
    }

    @Override
    public void display() {
        int sum = 0;
        for (Currency currency : Currency.values()) {
            HeapOfIdenticalBanknotes heap = heaps.get(currency);
            heap.display();
            sum += heap.get();
        }
        System.out.println("Total : " + sum);
    }
}
