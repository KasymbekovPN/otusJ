package ru.otus.kasymbekovPN.HW08.visitor;

import ru.otus.kasymbekovPN.HW08.visitedElement.*;

/**
 * Интерфейс для реализации функционала визитора
 */
public interface Visitor {

    /**
     * Посещение объекта типа ObjectVE
     * @param objectVisitedElement посещаевый объект
     */
    void visit(ObjectVE objectVisitedElement) throws IllegalAccessException, NoSuchFieldException;

    /**
     * Посещение объекта типа ArrayVE
     * @param arrayVE посещаемый объект
     */
    void visit(ArrayVE arrayVE) throws IllegalAccessException, NoSuchFieldException;

    /**
     * Посещение объекта типа PrimitiveVE
     * @param primitiveVE посещаемый объект
     */
    void visit(PrimitiveVE primitiveVE);

    /**
     * Посещение объекта типа CollectionVE
     * @param collectionVE посещаемый объект
     */
    void visit(CollectionVE collectionVE) throws IllegalAccessException, NoSuchFieldException;

    /**
     * Посещение объекта типа CharSequenceVE
     * @param charSequenceVE посещаемый объект
     */
    void visit(CharSequenceVE charSequenceVE);

    /**
     * Посещение объекта типа NullVE
     * @param nullVE посещаемый объект
     */
    void visit(NullVE nullVE);
}
