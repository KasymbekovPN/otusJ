package ru.otus.kasymbekovPN.HW07.banknotes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BanknoteHeapImplTest {

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
                {1_000, 1_000, 2_000, 0, true},
                {1_000, 2_000, 3_000, 0, true},
                {1_000, 3_000, 4_000, 0, true},
                {1_000, 4_000, 5_000, 0, true},
                {1_000, 5_000, 6_000, 0, true},
                {1_000, 6_000, 7_000, 0, true},
                {1_000, 7_000, 8_000, 0, true},
                {1_000, 8_000, 1_000, 8_000, false}
        };
    }

    private static Object[][] testSubData(){
        return new Object[][]{
                {8_000, 1_000, 7_000, 1_000, true},
                {8_000, 2_000, 6_000, 2_000, true},
                {8_000, 3_000, 5_000, 3_000, true},
                {8_000, 4_000, 4_000, 4_000, true},
                {8_000, 5_000, 3_000, 5_000, true},
                {8_000, 6_000, 2_000, 6_000, true},
                {8_000, 7_000, 1_000, 7_000, true},
                {8_000, 8_000, 0, 8_000, true},
                {8_000, 9_000, 8_000, 0, false}
        };
    }

    @DisplayName("Constructor Test, check number")
    @ParameterizedTest
    @MethodSource("ctorData")
    void ctorTestCheckNumber(Currency c, int n){
        final BanknoteHeap heap = new BanknoteHeapImpl(c, n);
        assertEquals(heap.getNumber(), n);
    }

    @DisplayName("Constructor Test, check denomination")
    @ParameterizedTest
    @MethodSource("ctorData")
    void ctorTestCheckDenomination(Currency c, int n){
        final BanknoteHeap heap = new BanknoteHeapImpl(c, n);
        assertEquals(heap.getDenomination(), c);
    }

    @DisplayName("Add Test")
    @ParameterizedTest
    @MethodSource("testAddData")
    void addTest(int n1, int n2, int n3, int n4, boolean r){
        final BanknoteHeap heap1 = new BanknoteHeapImpl(Currency.VALUE_10, n1);
        final BanknoteHeap heap2 = new BanknoteHeapImpl(Currency.VALUE_10, n2);

        boolean result = heap1.prepareAddAction(heap2);
        assertEquals(r, result);

        if (result) {
            heap1.confirmChange();
            assertEquals(n3, heap1.getNumber());
            assertEquals(n4, heap2.getNumber());

        }
    }

    @DisplayName("Sub Test")
    @ParameterizedTest
    @MethodSource("testSubData")
    void subTest(int n1, int n2, int n3, int n4, boolean r){
        final BanknoteHeap heap1 = new BanknoteHeapImpl(Currency.VALUE_10, n1);
        final BanknoteHeap heap2 = new BanknoteHeapImpl(Currency.VALUE_10, n2);

        boolean result = heap1.prepareSubAction(heap2);
        assertEquals(r, result);

        if (result){
            heap1.confirmChange();
            assertEquals(n3, heap1.getNumber());
            assertEquals(n4, heap2.getNumber());
        }
    }
}
