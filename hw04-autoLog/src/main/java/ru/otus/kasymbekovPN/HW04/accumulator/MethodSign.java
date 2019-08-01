package ru.otus.kasymbekovPN.HW04.accumulator;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MethodSign implements Comparable {

    private static final String DELIMITER = ",";

    private String value;

    public MethodSign(Method method){
        StringBuilder sb = new StringBuilder(method.getName()).append(DELIMITER);
        for (Parameter parameter : method.getParameters()) {
            sb.append(parameter.getType()).append(DELIMITER);
        }

        value = sb.toString();
    }

    private String getValue() {
        return value;
    }

    @Override
    public int compareTo(Object o) {
        MethodSign other = (MethodSign)o;
        return value.compareTo(other.getValue());
    }
}
