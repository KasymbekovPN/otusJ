package ru.otus.kasymbekovPN.HW07.banknotes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.kasymbekovPN.HW07.atm.AtmAction;
import ru.otus.kasymbekovPN.HW07.atm.AtmActionResult;
import ru.otus.kasymbekovPN.HW07.atm.AtmImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AtmImplTest {

    private static Object[][] getAddData(){
        return new Object[][]{
                {1_000, 1_000, 2_000, 0, AtmAction.ADD, true},
                {1_000, 2_000, 3_000, 0, AtmAction.ADD, true},
                {1_000, 3_000, 4_000, 0, AtmAction.ADD, true},
                {1_000, 4_000, 5_000, 0, AtmAction.ADD, true},
                {1_000, 5_000, 6_000, 0, AtmAction.ADD, true},
                {1_000, 6_000, 7_000, 0, AtmAction.ADD, true},
                {1_000, 7_000, 8_000, 0, AtmAction.ADD, true},
                {1_000, 8_000, 1_000, 8_000, AtmAction.ADD, false}
        };
    }

    private static Object[][] getSubData(){
        return new Object[][]{
                {
                    10, 100_000,
                    10, 10, 10, 10, 10, 10, 10, 10,
                    0, 0, 0, 0, 0, 0, 0, 0,
                    false, AtmAction.SUB
                },
                {
                    10, 80_000,
                    10, 10, 10, 10, 10, 0, 0, 0,
                    0, 0, 0, 0, 0, 10, 10, 10,
                    true, AtmAction.SUB
                },
                {
                    10, 50_000,
                    10, 10, 10, 10, 10, 10, 10, 0,
                    0, 0, 0, 0, 0, 0, 0, 10,
                    true, AtmAction.SUB
                },
                {
                    10, 30_000,
                    10, 10, 10, 10, 10, 10, 10, 4,
                    0, 0, 0, 0, 0, 0, 0, 6,
                    true, AtmAction.SUB
                }
        };
    }

    @DisplayName("add test")
    @ParameterizedTest
    @MethodSource("getAddData")
    void test1(int n1, int n2, int n3, int n4, AtmAction act, boolean r){
        var heaps1 = BanknoteHeapsImpl.makeInstance(n1, new BanknoteHeapImpl());
        var heaps2 = BanknoteHeapsImpl.makeInstance(n2, new BanknoteHeapImpl());
        var heaps3 = BanknoteHeapsImpl.makeInstance(n3, new BanknoteHeapImpl());
        var heaps4 = BanknoteHeapsImpl.makeInstance(n4, new BanknoteHeapImpl());

        var atm = new AtmImpl(heaps1);
        var res1 = atm.add(heaps2);

        var res2 = new AtmActionResult(heaps3, heaps4, act, r);

        assertTrue(res1.eq(res2));
    }

    @DisplayName("sub test")
    @ParameterizedTest
    @MethodSource("getSubData")
    void test2(int n1, int money, int n2_10, int n2_50, int n2_100, int n2_200,
               int n2_500, int n2_1000, int n2_2000, int n2_5000, int n3_10, int n3_50,
               int n3_100, int n3_200, int n3_500, int n3_1000, int n3_2000, int n3_5000,
               boolean r, AtmAction act){
        var heap1 = BanknoteHeapsImpl.makeInstance(n1, new BanknoteHeapImpl());
        var heap2 = BanknoteHeapsImpl.makeInstance(n2_10, n2_50, n2_100, n2_200, n2_500, n2_1000, n2_2000, n2_5000,
                new BanknoteHeapImpl());
        var heap3 = BanknoteHeapsImpl.makeInstance(n3_10, n3_50, n3_100, n3_200, n3_500, n3_1000, n3_2000, n3_5000,
                new BanknoteHeapImpl());

        var atm = new AtmImpl(heap1);
        var res1 = atm.sub(money, new BanknoteHeapImpl());

        if (r){
            var res2 = new AtmActionResult(heap2, heap3, act, r);
            assertTrue(res1.eq(res2));
        }else {
            var res3 = new AtmActionResult(heap2, money);
            assertTrue(res1.eq(res3));
        }


    }
}
