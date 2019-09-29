package ru.otus.kasymbekovPN.HW09.visitor;

/**
 * Интерфейс для реализация посещаемого элемента.
 */
public interface VisitedElement {

    /**
     * Функция, принимающая визитор
     * @param visitor визитор
     */
    void accept(Visitor visitor);
}
