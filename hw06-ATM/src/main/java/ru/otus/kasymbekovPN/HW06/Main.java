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
        var heaps = BanknotesHeapsImpl.makeInstance(
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
        heaps = BanknotesHeapsImpl.makeInstance(
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
        heaps = BanknotesHeapsImpl.makeInstance(
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
        heaps = BanknotesHeapsImpl.makeInstance(1_000, new BanknotesHeap(0));
        System.out.println("Add all denominations of 1_000");
        System.out.println("### HUMAN ###");
        heaps.display();
        System.out.println("\n");
        res = atm.add(heaps);
        res.display();
    }
}
