package ru.otus.kasymbekovPN.HW08.visitedElement;

import ru.otus.kasymbekovPN.HW08.visitor.Visitor;

/**
 * Класс, реализующий функционал "nullв коллекциях
 * и массивах как посещаемый элемент"
 *
 * VE - visited element
 */
public class NullVE implements VisitedElement {

    /**
     * Принимает визитор
     * @param visitor визитор
     */
    @Override
    public void accept(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {
        visitor.visit(this);
    }
}
