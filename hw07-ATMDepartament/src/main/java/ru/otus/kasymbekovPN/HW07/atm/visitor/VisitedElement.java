package ru.otus.kasymbekovPN.HW07.atm.visitor;

/**
 * Интерфейс,с лужащий для реализации функционала посещаемого
 * элемента.
 */
public interface VisitedElement {

    /**
     * Метод, принимающий визитор
     * @param visitor визитор
     */
    void accept(Visitor visitor);
}
