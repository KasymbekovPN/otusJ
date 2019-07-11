package ru.otus.kasymbekovPN.HW02;

import java.util.Collections;
import java.util.List;

public class DIYArrayListTestDrive
{
    static public void main(String... args)
    {
        System.out.println("hello123");
        List<Integer> l = new DIYArrayList<>();
        l.add(12);

        System.out.println(l);

        Collections.addAll(l, 12, 13);
    }
}
