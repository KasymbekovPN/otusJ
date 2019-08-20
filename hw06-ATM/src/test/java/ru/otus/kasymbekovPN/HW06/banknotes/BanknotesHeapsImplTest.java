package ru.otus.kasymbekovPN.HW06.banknotes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BanknotesHeapsImplTest {

    private static Object[][] getAddData(){
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

    private static Object[][] getSubData(){
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

    @DisplayName("Add Test")
    @ParameterizedTest
    @MethodSource("getAddData")
    void test1(int n1, int n2, boolean result){
        var heaps1 = BanknotesHeapsImpl.makeInstance(n1, new ATMHeap(0));
        var heaps2 = BanknotesHeapsImpl.makeInstance(n2, new BanknotesHeap(0));
        assertEquals(result, heaps1.add(heaps2));
    }

    @DisplayName("Sub Test")
    @ParameterizedTest
    @MethodSource("getSubData")
    void test2(int n1, int n2, boolean result){
        var heaps1 = BanknotesHeapsImpl.makeInstance(n1, new ATMHeap(0));
        var heaps2 = BanknotesHeapsImpl.makeInstance(n2, new BanknotesHeap(0));
        assertEquals(result, heaps1.sub(heaps2));
    }
}
