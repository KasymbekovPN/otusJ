package ru.otus.kasymbekovPN.HW06.banknotes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMHeapTest {

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

    private static Object[][] testAddData(){
        return new Object[][]{
                {1_000, 1_000, true},
                {1_000, 2_000, true},
                {1_000, 3_000, true},
                {1_000, 4_000, true},
                {1_000, 5_000, true},
                {1_000, 6_000, true},
                {1_000, 7_000, true},
                {1_000, 8_000, false}
        };
    }

    private static Object[][] testSubData(){
        return new Object[][]{
                {8_000, 1_000, true},
                {8_000, 2_000, true},
                {8_000, 3_000, true},
                {8_000, 4_000, true},
                {8_000, 5_000, true},
                {8_000, 6_000, true},
                {8_000, 7_000, true},
                {8_000, 8_000, true},
                {8_000, 9_000, false}
        };
    }

    @DisplayName("Constructor Test, check number")
    @ParameterizedTest
    @MethodSource("ctorData")
    void ctorTestCheckNumber(Currency c, int n){
        final ATMHeap atmHeap = new ATMHeap(c, n);
        assertEquals(atmHeap.getNumber(), n);
    }

    @DisplayName("Constructor Test, check denomination")
    @ParameterizedTest
    @MethodSource("ctorData")
    void ctorTestCheckDenomination(Currency c, int n){
        final ATMHeap atmHeap = new ATMHeap(c, n);
        assertEquals(atmHeap.getDenomination(), c);
    }

    @DisplayName("Add Test")
    @ParameterizedTest
    @MethodSource("testAddData")
    void testAdd(int n1, int n2, boolean result){
        final ATMHeap heap1 = new ATMHeap(Currency.VALUE_10, n1);
        final ATMHeap heap2 = new ATMHeap(Currency.VALUE_10, n2);
        assertEquals(result, heap1.add(heap2));
    }

    @DisplayName("Sub Test")
    @ParameterizedTest
    @MethodSource("testSubData")
    void testSub(int n1, int n2, boolean result){
        final ATMHeap heap1 = new ATMHeap(Currency.VALUE_10, n1);
        final ATMHeap heap2 = new ATMHeap(Currency.VALUE_10, n2);
        assertEquals(result, heap1.sub(heap2));
    }
}
