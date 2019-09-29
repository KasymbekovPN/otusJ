package ru.otus.kasymbekovPN.HW09.visitor;

/**
 * Интерфейс для реализации визитора
 */
public interface Visitor {

    /**
     * Посетить PrimitiveVE
     * @param primitiveVE посещаемый инстанс
     */
    void visit(PrimitiveVE primitiveVE);

    /**
     * Посетить StringVE
     * @param stringVE посещаемый инстанс
     */
    void visit(StringVE stringVE);
}
