package ru.otus.kasymbekovPN.HW01;

import com.google.common.math.IntMath;
import java.util.concurrent.ThreadLocalRandom;

public class HelloOtus
{
    private static final int NUMBER = 10;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 15;

    public static void main(String... args)
    {
        for(int i = 0; i < NUMBER; i++)
        {
            int value = ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1);
            int result = IntMath.factorial(value);
            String msg = result == Integer.MAX_VALUE ? " (overflow)" : "";

            System.out.println(value + "!\t= " + result + msg);
        }

        System.out.println("Calculating is done.");
    }
}
