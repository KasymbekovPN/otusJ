package ru.otus.kasymbekovPN.HW07.atm.visitor;

import ru.otus.kasymbekovPN.HW07.atm.AtmImpl;

public interface Visitor {
    void visit(AtmImpl atmImpl);
}
