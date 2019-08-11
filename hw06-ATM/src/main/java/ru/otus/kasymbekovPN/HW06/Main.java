package ru.otus.kasymbekovPN.HW06;

import ru.otus.kasymbekovPN.HW06.atm.ATM;
import ru.otus.kasymbekovPN.HW06.atm.ATMActionResult;
import ru.otus.kasymbekovPN.HW06.banknotes.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<ECurrency, IHeapOfIdenticalBankNotes> bHeap = new HashMap<>();
        bHeap.put(ECurrency.VALUE_10, new ATMHeap(ECurrency.VALUE_10, 100));
        bHeap.put(ECurrency.VALUE_200, new ATMHeap(ECurrency.VALUE_200, 300));

        BanknotesHeaps banknotesHeaps = BanknotesHeaps.makeInstance(bHeap, new ATMHeap(ECurrency.VALUE_10, 0));

        ATM atm = new ATM(banknotesHeaps);
        atm.display();

        System.out.println("--------");

        Map<ECurrency, IHeapOfIdenticalBankNotes> pHeap = new HashMap<>();
        pHeap.put(ECurrency.VALUE_200, new BanknotesHeap(ECurrency.VALUE_200, 150));
        pHeap.put(ECurrency.VALUE_1000, new BanknotesHeap(ECurrency.VALUE_1000, 20));
        BanknotesHeaps pBanknotesHeaps = BanknotesHeaps
                .makeInstance(pHeap, new BanknotesHeap(ECurrency.VALUE_10, 0));
        pBanknotesHeaps.display();

        System.out.println("----");

        ATMActionResult res = atm.add(pBanknotesHeaps);
        res.display();

        System.out.println("-----");



//        //<
////        pHeap = new HashMap<>();
////        pBanknotesHeaps = BanknotesHeaps.makeInstance(pHeap, new BanknotesHeap(ECurrency.VALUE_10, 0));
////        System.out.println("sub result : " + atm.sub(100_100, pBanknotesHeaps));

        res = atm.sub(100_100);
        res.display();


        res = atm.sub(200_000);
        res.display();

//        IBanknotesHeaps sub = atm.sub(100_100);
//
////        if (null != sub){
//            System.out.println("--- sub");
//            //<
//            sub.display();
////        }
//
//        System.out.println("---");
//        atm.display();
//
//        pHeap = new HashMap<>();
//        pHeap.put(ECurrency.VALUE_10, new BanknotesHeap(ECurrency.VALUE_10, 10_000));
//        BanknotesHeaps banknotesHeaps1 = BanknotesHeaps.makeInstance(pHeap, new BanknotesHeap(ECurrency.VALUE_10, 0));
//
//        System.out.println("....");
//        banknotesHeaps1.display();
//
//        System.out.println("add result : " + atm.add(banknotesHeaps1));
//
//        System.out.println("atm : ");
//        atm.display();
//
//        System.out.println("banknotesHeap1");
//        banknotesHeaps1.display();


    }
}
