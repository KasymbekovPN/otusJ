package ru.otus.kasymbekovPN.HW06.banknotes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public static BanknotesHeaps makeInstance(Map<ECurrency, IHeapOfIdenticalBankNotes> heaps){
        return new BanknotesHeaps(heaps);
    }

    private BanknotesHeaps(Map<ECurrency, IHeapOfIdenticalBankNotes> heaps){
        this.heaps = heaps;
    }

    public Map<ECurrency, IHeapOfIdenticalBankNotes> getHeaps() {
        return heaps;
    }

    //< заменить на метод change
    @Override
    public boolean add(IBanknotesHeaps heaps) {
        boolean success = true;
        Set<IHeapOfIdenticalBankNotes> thisHeaps = new HashSet<>();

        for (Map.Entry<ECurrency, IHeapOfIdenticalBankNotes> entry : heaps.getHeaps().entrySet()){
            ECurrency key = entry.getKey();
            IHeapOfIdenticalBankNotes thisHeap = this.heaps.get(key);
            IHeapOfIdenticalBankNotes otherHeap = entry.getValue();
            success &= thisHeap.add(otherHeap);

            thisHeaps.add(thisHeap);
        }

        if (success){
            for (IHeapOfIdenticalBankNotes thisHeap : thisHeaps) {
                thisHeap.confirmChange();
            }
        }

        return success;
    }

    //< заменить на метод change
    @Override
    public boolean sub(IBanknotesHeaps heaps) {
        boolean success = true;
        Set<IHeapOfIdenticalBankNotes> thisHeaps = new HashSet<>();

        for (Map.Entry<ECurrency, IHeapOfIdenticalBankNotes> entry : heaps.getHeaps().entrySet()){
            ECurrency key = entry.getKey();
            IHeapOfIdenticalBankNotes thisHeap = this.heaps.get(key);
            IHeapOfIdenticalBankNotes otherHeap = entry.getValue();
            success &= thisHeap.sub(otherHeap);

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
