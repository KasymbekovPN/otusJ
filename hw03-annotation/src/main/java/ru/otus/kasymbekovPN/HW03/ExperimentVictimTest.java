package ru.otus.kasymbekovPN.HW03;

import ru.otus.kasymbekovPN.HW03.starter.annotations.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExperimentVictimTest {

    private ExperimentVictim experimentVictim;

    @StarterBeforeAll("Call before all tests, #1")
    public static void beforeClassFirst(){
    }

    @StarterBeforeAll("Call before all tests, #2")
    public static void beforeClassSecond(){
    }

    @StarterAfterAll("Call before all tests")
    public static void afterClass(){
    }

    @StarterBeforeEach("Test instance initialization")
    public void init(){
        experimentVictim = new ExperimentVictim();
    }

    @StarterAfterEach("Test ended")
    public void testEnd() {
    }

    @StarterTest("Sum test : 2 == 1 + 1")
    public void test1(){
        assertEquals(2, experimentVictim.sum(1, 1));
    }

    @StarterTest("Div test with exception")
    public void test2(){
        assertThrows(IllegalArgumentException.class, () -> experimentVictim.div(10.0, 0.0));
    }

    @StarterTest("Div test : 10.0 == 100.0 / 10.0")
    public void test3() {
        assertEquals(10.0, experimentVictim.div(100.0, 10.0));
    }

    @StarterTest("Div test : 11.0 == 100.0 / 10.0")
    public void test4() {
        assertEquals(11.0, experimentVictim.div(100.0, 10.0));
    }

    @StarterTest("Div test : 20.0 == 100.0 / 5.0")
    public void test5() {
        assertEquals(20.0, experimentVictim.div(100.0, 5.0));
    }
}
