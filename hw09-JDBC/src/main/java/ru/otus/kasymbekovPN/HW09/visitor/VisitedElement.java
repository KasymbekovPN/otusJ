package ru.otus.kasymbekovPN.HW09.visitor;

import ru.otus.kasymbekovPN.HW09.visitor.Visitor;

public interface VisitedElement {
    void accept(Visitor visitor);
}
