package ru.otus.kasymbekovPN.HW07.department.atm.visitor;

import ru.otus.kasymbekovPN.HW07.department.atm.AtmImpl;

/**
 * Интерфейс для реализации функционала "визитор"
 */
public interface Visitor {

    /**
     * Метод для посещения AtmImpl
     * @param atmImpl Инстанс для посещения
     */
    void visit(AtmImpl atmImpl);
}
