package ru.otus.kasymbekovPN.HW07.department;

import ru.otus.kasymbekovPN.HW07.atm.visitor.VisitedElement;

//< !!! comment
public interface Department {
    int getTotalBalance();
    void addVisitedElement(VisitedElement visitedElement);
}
