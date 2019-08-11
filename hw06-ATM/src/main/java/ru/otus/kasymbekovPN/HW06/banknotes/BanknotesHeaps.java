package ru.otus.kasymbekovPN.HW06.banknotes;

import ru.otus.kasymbekovPN.HW06.utils.NumberDiapason;

import java.util.*;

public class BanknotesHeaps implements IBanknotesHeaps {
    private Map<ECurrency, IHeapOfIdenticalBankNotes> heaps;

    public static BanknotesHeaps makeInstance(Map<ECurrency, IHeapOfIdenticalBankNotes> heaps,
                                      IHeapOfIdenticalBankNotes dummy){

        for (ECurrency eCurrency : ECurrency.getAllItem()) {
            if (!heaps.containsKey(eCurrency)){
                dummy.setDenomination(eCurrency);
                heaps.put(eCurrency, dummy.clone());
            } else {
                heaps.get(eCurrency).setDenomination(eCurrency);
            }
        }

        return new BanknotesHeaps(heaps);
    }

    public static BanknotesHeaps makeInstance(int number_10, int number_50, int number_100,
                                              int number_200, int number_500, int number_1000,
                                              int number_2000, int number_5000,
                                              IHeapOfIdenticalBankNotes dummy){
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

        List<ECurrency> allItem = ECurrency.getAllItem();
        Map<ECurrency, IHeapOfIdenticalBankNotes> heaps = new HashMap<>();

        for (int i = 0; i < allItem.size(); i++){
            ECurrency currency = allItem.get(i);
            int number = NumberDiapason.putInRange(numberList.get(i));

            dummy.setNumber(number);
            dummy.setDenomination(currency);
            heaps.put(currency, dummy.clone());
        }

        return new BanknotesHeaps(heaps);
    }

    public static BanknotesHeaps makeInstance(int number, IHeapOfIdenticalBankNotes dummy){
        List<ECurrency> allItem = ECurrency.getAllItem();
        Map<ECurrency, IHeapOfIdenticalBankNotes> heaps = new HashMap<>();

        number = NumberDiapason.putInRange(number);
        for (ECurrency currency : allItem) {
            dummy.setNumber(number);
            dummy.setDenomination(currency);
            heaps.put(currency, dummy.clone());
        }

        return new BanknotesHeaps(heaps);
    }

    private BanknotesHeaps(Map<ECurrency, IHeapOfIdenticalBankNotes> heaps){
        this.heaps = heaps;
    }

    public Map<ECurrency, IHeapOfIdenticalBankNotes> getHeaps() {
        return heaps;
    }

    @Override
    public boolean add(IBanknotesHeaps heaps) {
        return action(heaps, true);
    }

    @Override
    public boolean sub(IBanknotesHeaps heaps) {
        return action(heaps, false);
    }

    private boolean action(IBanknotesHeaps heaps, boolean isAdd){
        boolean success = true;
        Set<IHeapOfIdenticalBankNotes> thisHeaps = new HashSet<>();

        for (Map.Entry<ECurrency, IHeapOfIdenticalBankNotes> entry : heaps.getHeaps().entrySet()){
            ECurrency key = entry.getKey();
            IHeapOfIdenticalBankNotes thisHeap = this.heaps.get(key);
            IHeapOfIdenticalBankNotes otherHeap = entry.getValue();

            success &= (isAdd ? thisHeap.add(otherHeap) : thisHeap.sub(otherHeap));

            thisHeaps.add(thisHeap);
        }

        if (success){
            for (IHeapOfIdenticalBankNotes thisHeap : thisHeaps) {
                thisHeap.confirmChange();
            }
        }

        return success;
    }

    @Override
    public void display() {
        int sum = 0;
        for (ECurrency eCurrency : ECurrency.getAllItem()) {
            IHeapOfIdenticalBankNotes heap = heaps.get(eCurrency);
            heap.display();
            sum += heap.get();
        }
        System.out.println("Total : " + sum);
    }
}
