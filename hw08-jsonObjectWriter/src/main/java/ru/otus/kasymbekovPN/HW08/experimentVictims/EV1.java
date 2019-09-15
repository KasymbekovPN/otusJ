package ru.otus.kasymbekovPN.HW08.experimentVictims;

import ru.otus.kasymbekovPN.HW08.javaObjectWriter.Txt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    private String[] arrS = new String[] {"ABC", "DEF"};

    private int[][] arrarr = new int[][] {{1, 2}, {11, 12}};

    private char[] ch = new char[] {49, 50, 51};

    private List<String> listS = new ArrayList<>(){{
        add("Hello");
        add("World");
    }};

    private String nullS;
    private List<String> nullStrList;
    private List<String> nullStrList2 = new ArrayList<>(){{
        add("line0");
        add(null);
        add("line2");
    }};

    private String[] strArrWithNUll = new String[]{"abc", null, "def"};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EV1 ev1 = (EV1) o;

        boolean arrarrEq = true;
        int[][] ev1ArrArr = ev1.arrarr;
        if (arrarr.length != ev1ArrArr.length){
            arrarrEq = false;
        } else {
            for(int i = 0; i < arrarr.length; i++){
                if (!Arrays.equals(arrarr[i], ev1ArrArr[i])){
                    arrarrEq = false;
                    break;
                }
            }
        }

        return by == ev1.by &&
                s == ev1.s &&
                i == ev1.i &&
                l == ev1.l &&
                Float.compare(ev1.f, f) == 0 &&
                Double.compare(ev1.d, d) == 0 &&
                bo == ev1.bo &&
                c == ev1.c &&
                Objects.equals(ev2, ev1.ev2) &&
                Objects.equals(nEv2, ev1.nEv2) &&
                Arrays.equals(arrI, ev1.arrI) &&
                Arrays.equals(arrEv4, ev1.arrEv4) &&
                Arrays.equals(arrS, ev1.arrS) &&
                arrarrEq &&
                Arrays.equals(ch, ev1.ch) &&
                Objects.equals(listS, ev1.listS) &&
                Objects.equals(nullS, ev1.nullS) &&
                Objects.equals(nullStrList, ev1.nullStrList) &&
                Objects.equals(nullStrList2, ev1.nullStrList2) &&
                Arrays.equals(strArrWithNUll, ev1.strArrWithNUll);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(by, s, i, l, f, d, bo, c, ev2, nEv2, listS, nullS, nullStrList, nullStrList2);
        result = 31 * result + Arrays.hashCode(arrI);
        result = 31 * result + Arrays.hashCode(arrEv4);
        result = 31 * result + Arrays.hashCode(arrS);
        result = 31 * result + Arrays.hashCode(arrarr);
        result = 31 * result + Arrays.hashCode(ch);
        result = 31 * result + Arrays.hashCode(strArrWithNUll);
        return result;
    }

    @Override
    public String toString() {

        var sb = new StringBuilder(Txt.OPEN_SQ_BRACKET.get());
        for (int[] ints : arrarr) {
            sb.append(Arrays.toString(ints));
        }
        sb.append(Txt.CLOSE_SQ_BRACKET.get());

        return "EV1{" +
                "by=" + by +
                ", s=" + s +
                ", i=" + i +
                ", l=" + l +
                ", f=" + f +
                ", d=" + d +
                ", bo=" + bo +
                ", c=" + c +
                ", ev2=" + ev2 +
                ", nEv2=" + nEv2 +
                ", arrI=" + Arrays.toString(arrI) +
                ", arrEv4=" + Arrays.toString(arrEv4) +
                ", arrS=" + Arrays.toString(arrS) +
                ", arrarr=" + sb.toString() +
                ", ch=" + Arrays.toString(ch) +
                ", listS=" + listS +
                ", nullS='" + nullS + '\'' +
                ", nullStrList=" + nullStrList +
                ", nullStrList2=" + nullStrList2 +
                ", strArrWithNUll=" + Arrays.toString(strArrWithNUll) +
                '}';
    }
}
