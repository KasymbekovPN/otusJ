package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

public interface Visitor {
    void visit(PrimitiveVisitedElement primitiveVisitedElement) throws NoSuchFieldException, IllegalAccessException;
    void visit(ObjectVisitedElement objectVisitedElement) throws IllegalAccessException, NoSuchFieldException;
    void visit(ArrayVisitedElement arrayVisitedElement) throws IllegalAccessException, NoSuchFieldException;
    void visit(ArPrimitiveVisitedElement arPrimitiveVisitedElement);
    void visit(CollectionVisitedElement collectionVisitedElement) throws IllegalAccessException, NoSuchFieldException;
    void visit(CharSequenceVE charSequenceVE);
    void visit(NullVE nullVE);
}
