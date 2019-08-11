package ru.otus.kasymbekovPN.HW06;

import ru.otus.kasymbekovPN.HW06.atm.ATM;
import ru.otus.kasymbekovPN.HW06.atm.ATMActionResult;
import ru.otus.kasymbekovPN.HW06.banknotes.*;

/*
    java -jar hw06-ATM-jar-with-dependencies.jar
 */
public class Main {
    public static void main(String[] args) {

        /*
            Fill ATM
         */
        var heaps = BanknotesHeaps.makeInstance(
                100, 0, 0, 200,
                0, 0, 0, 0, new ATMHeap(0)
        );
        ATM atm = new ATM(heaps);
        System.out.println("Fill ATM\n### ATM ###");
        atm.display();
        System.out.println("\n");

        /*
            Try add heap of banknotes into ATM (success)
         */
        heaps = BanknotesHeaps.makeInstance(
                0, 0, 0, 250,
                0, 20, 0, 0, new BanknotesHeap(0));
        System.out.println("### HUMAN ###");
        heaps.display();
        System.out.println("\n");
        ATMActionResult res = atm.add(heaps);
        System.out.println("Try add heap of banknotes into ATM");
        res.display();

        /*
            Try take money from ATM (success)
         */
        System.out.println("Try take 100_100 from ATM");
        res = atm.sub(100_100, new BanknotesHeap(0));
        res.display();

        /*
            Try take money from ATM (failure)
         */
        System.out.println("Try take 200_000 from ATM");
        res = atm.sub(200_000, new BanknotesHeap(0));
        res.display();

        /*
            Try add heap of banknotes (10 * 10_000) into ATM (failure)
         */
        heaps = BanknotesHeaps.makeInstance(
                10_000, 0, 0, 0,
                0, 0, 0, 0, new BanknotesHeap(0));
        System.out.println("Try add heap of banknotes (10 * 10_000) into ATM");
        System.out.println("### HUMAN ###");
        heaps.display();
        System.out.println("\n");
        res = atm.add(heaps);
        res.display();


        /*
            Add all denominations of 1_000
         */
        heaps = BanknotesHeaps.makeInstance(1_000, new BanknotesHeap(0));
        System.out.println("Add all denominations of 1_000");
        System.out.println("### HUMAN ###");
        heaps.display();
        System.out.println("\n");
        res = atm.add(heaps);
        res.display();

        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//        Map<ECurrency, IHeapOfIdenticalBankNotes> bHeap = new HashMap<>();
////        bHeap.put(ECurrency.VALUE_10, new ATMHeap(ECurrency.VALUE_10, 100));
////        bHeap.put(ECurrency.VALUE_200, new ATMHeap(ECurrency.VALUE_200, 300));
////        BanknotesHeaps banknotesHeaps = BanknotesHeaps.makeInstance(bHeap, new ATMHeap(ECurrency.VALUE_10, 0));
//        //<
//        bHeap.put(ECurrency.VALUE_10, new ATMHeap(100));
//        bHeap.put(ECurrency.VALUE_200, new ATMHeap(300));
//        BanknotesHeaps banknotesHeaps = BanknotesHeaps.makeInstance(bHeap, new ATMHeap(0));
//
//        ATM atm = new ATM(banknotesHeaps);
//        //<
////        atm.display();
//
////        System.out.println("--------");
//
//        Map<ECurrency, IHeapOfIdenticalBankNotes> pHeap = new HashMap<>();
//        pHeap.put(ECurrency.VALUE_200, new BanknotesHeap(150));
//        pHeap.put(ECurrency.VALUE_1000, new BanknotesHeap(20));
//        BanknotesHeaps pBanknotesHeaps = BanknotesHeaps.makeInstance(pHeap, new BanknotesHeap(0));
//        //<
////        pHeap.put(ECurrency.VALUE_200, new BanknotesHeap(ECurrency.VALUE_200, 150));
////        pHeap.put(ECurrency.VALUE_1000, new BanknotesHeap(ECurrency.VALUE_1000, 20));
////        BanknotesHeaps pBanknotesHeaps = BanknotesHeaps
////                .makeInstance(pHeap, new BanknotesHeap(ECurrency.VALUE_10, 0));
////        pBanknotesHeaps.display();
//
////        System.out.println("----");
//
//        ATMActionResult res = atm.add(pBanknotesHeaps);
//        res.display();
//
////        System.out.println("-----");
//
//
//
////        //<
//////        pHeap = new HashMap<>();
//////        pBanknotesHeaps = BanknotesHeaps.makeInstance(pHeap, new BanknotesHeap(ECurrency.VALUE_10, 0));
//////        System.out.println("sub result : " + atm.sub(100_100, pBanknotesHeaps));
//
////        BanknotesHeap dummy = new BanknotesHeap(0);
////        res = atm.sub(100_100, dummy);
//        res = atm.sub(100_100, new BanknotesHeap(0));
//        res.display();
//
//
////        res = atm.sub(200_000, dummy);
//        //<
//        res = atm.sub(200_000, new BanknotesHeap(0));
//        res.display();
//
//        bHeap = new HashMap<>();
//        bHeap.put(ECurrency.VALUE_10, new BanknotesHeap(10_000));
//        banknotesHeaps = BanknotesHeaps.makeInstance(bHeap, new BanknotesHeap(0));
//        res = atm.add(banknotesHeaps);
//        res.display();
//
//        bHeap = new HashMap<>();
//        bHeap.put(ECurrency.VALUE_10, new BanknotesHeap(1_000));
//        bHeap.put(ECurrency.VALUE_50, new BanknotesHeap(1_000));
//        bHeap.put(ECurrency.VALUE_100, new BanknotesHeap(1_000));
//        bHeap.put(ECurrency.VALUE_200, new BanknotesHeap(1_000));
//        bHeap.put(ECurrency.VALUE_500, new BanknotesHeap(1_000));
//        bHeap.put(ECurrency.VALUE_1000, new BanknotesHeap(1_000));
//        bHeap.put(ECurrency.VALUE_2000, new BanknotesHeap(1_000));
//        bHeap.put(ECurrency.VALUE_5000, new BanknotesHeap(1_000));
//        banknotesHeaps = BanknotesHeaps.makeInstance(bHeap, new BanknotesHeap(0));
//        res = atm.add(banknotesHeaps);
//        res.display();

////<<----------------------------------------------------------------
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
