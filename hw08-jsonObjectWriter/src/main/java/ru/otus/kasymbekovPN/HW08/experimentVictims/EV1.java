package ru.otus.kasymbekovPN.HW08.experimentVictims;

import java.util.ArrayList;
import java.util.List;

public class EV1 {
    private byte by;
    private short s;
    private int i;
    private long l;
    private float f;
    private double d;
    private boolean bo;
    private char c = '1';

    private EV2 ev2 = new EV2();
    private EV2 nEv2;

    private int[] arrI = new int[]{0, 1, 2};
    private EV4[] arrEv4 = new EV4[] {new EV4(), new EV4()};

    private int[][] arrarr = new int[][] {{1, 2}, {11, 12}};

    private char[] ch = new char[] {49, 50, 51};

    private List<Integer> listI = new ArrayList<>(){{
        add(101);
        add(102);
    }};
}
