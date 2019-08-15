package ru.otus.kasymbekovPN.HW06.banknotes;

import java.util.ArrayList;
import java.util.List;

public enum ECurrency {
    VALUE_10(10),
    VALUE_50(50),
    VALUE_100(100),
    VALUE_200(200),
    VALUE_500(500),
    VALUE_1000(1000),
    VALUE_2000(2000),
    VALUE_5000(5000);

    final private int value;

    static public List<ECurrency> getAllItem(){
        return new ArrayList<>(){{
            add(ECurrency.VALUE_10);
            add(ECurrency.VALUE_50);
            add(ECurrency.VALUE_100);
            add(ECurrency.VALUE_200);
            add(ECurrency.VALUE_500);
            add(ECurrency.VALUE_1000);
            add(ECurrency.VALUE_2000);
            add(ECurrency.VALUE_5000);
        }};
    }

    public int getValue() {
        return value;
    }

    ECurrency(int value){
        this.value = value;
    }
}
