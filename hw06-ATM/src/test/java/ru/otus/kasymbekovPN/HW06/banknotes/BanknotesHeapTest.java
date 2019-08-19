package ru.otus.kasymbekovPN.HW06.banknotes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BanknotesHeapTest {

    private static Object[][] ctorData(){
        return new Object[][]{
                {Currency.VALUE_10, 10},
                {Currency.VALUE_10, 100},
                {Currency.VALUE_10, 1_000},
                {Currency.VALUE_50, 10},
                {Currency.VALUE_50, 100},
                {Currency.VALUE_50, 1_000},
                {Currency.VALUE_100, 10},
                {Currency.VALUE_100, 100},
                {Currency.VALUE_100, 1_000},
                {Currency.VALUE_200, 10},
                {Currency.VALUE_200, 100},
                {Currency.VALUE_200, 1_000},
                {Currency.VALUE_500, 10},
                {Currency.VALUE_500, 100},
                {Currency.VALUE_500, 1_000},
                {Currency.VALUE_1000, 10},
                {Currency.VALUE_1000, 100},
                {Currency.VALUE_1000, 1_000},
                {Currency.VALUE_2000, 10},
                {Currency.VALUE_2000, 100},
                {Currency.VALUE_2000, 1_000},
                {Currency.VALUE_5000, 10},
                {Currency.VALUE_5000, 100},
                {Currency.VALUE_5000, 1_000}
        };
    }

    @DisplayName("Constructor Test, check number")
    @ParameterizedTest
    @MethodSource("ctorData")
    void ctorTestCheckNumber(Currency c, int n){
        var heap = new BanknotesHeap(c, n);
        assertEquals(heap.getNumber(), n);
    }

    @DisplayName("Constructor Test, check denomination")
    @ParameterizedTest
    @MethodSource("ctorData")
    void ctorTestCheckDenomination(Currency c, int n){
        var heap = new BanknotesHeap(c, n);
        assertEquals(heap.getDenomination(), c);
    }

}
