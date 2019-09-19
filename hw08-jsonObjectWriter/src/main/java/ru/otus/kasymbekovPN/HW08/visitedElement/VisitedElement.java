package ru.otus.kasymbekovPN.HW08.visitedElement;

import ru.otus.kasymbekovPN.HW08.visitor.Visitor;

/**
 * Интерфейс для реализации функционала посещаемого
 * элемента
 */
public interface VisitedElement {

    /**
     * Метод, принимающий визитор
     * @param visitor визитор
     */
    void accept(Visitor visitor) throws IllegalAccessException, NoSuchFieldException;
}
