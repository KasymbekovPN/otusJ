package ru.otus.kasymbekovPN.HW09;

import java.lang.annotation.Annotation;

public interface VisitedElementData {
    String getName();
    String getType();
    boolean isAnnotationPresent(Class annotation);
}
