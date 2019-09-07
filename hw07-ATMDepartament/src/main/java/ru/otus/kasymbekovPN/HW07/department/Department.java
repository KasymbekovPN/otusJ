package ru.otus.kasymbekovPN.HW07.department;

import ru.otus.kasymbekovPN.HW07.atm.visitor.VisitedElement;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;
import ru.otus.kasymbekovPN.HW07.utils.Caretaker;

import java.util.Set;

//< !!! comment
public interface Department {
//    int getBalance();
//    int getBalance(Set<Integer> IDs);
    //<
    CommandResult getBalance();
    CommandResult getBalance(Set<Integer> IDs);
    CommandResult resetState();

    //< rename to addArm, replace VisitedElement with class
    void addVisitedElement(VisitedElement visitedElement, Caretaker caretaker);
}
