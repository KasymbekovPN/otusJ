package ru.otus.kasymbekovPN.HW07.department.atm.visitor;

/**
 * Интерфейс, служащий для реализации функционала посещаемого
 * элемента.
 */
public interface VisitedElement {

    /**
     * Метод, принимающий визитор
     * @param visitor визитор
     */
    void accept(Visitor visitor);
}
