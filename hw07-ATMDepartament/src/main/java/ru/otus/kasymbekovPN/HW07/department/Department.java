package ru.otus.kasymbekovPN.HW07.department;

import ru.otus.kasymbekovPN.HW07.department.atm.visitor.VisitedElement;
import ru.otus.kasymbekovPN.HW07.department.operator.result.OperatorCommandResult;
import ru.otus.kasymbekovPN.HW07.utils.Caretaker;

import java.util.Set;

/**
 * Интерфейс для реализации департемента с банкоматами.
 */
public interface Department {

    /**
     * Возвращает информацию об общем балансе департамента
     * банкоматов.
     * @return Инстанс, хранящий информацию об общем балансе.
     */
    OperatorCommandResult getBalance();

    /**
     * Возвращает информацию о выборочном балансе департемента
     * банкоматов. Возвращает суммарный баланс тех банкоматов,
     * идентификаторы которых представлены в IDs
     * @param IDs Идентификаторы банктоматов
     * @return Инстанс, хранящий информацию о выборочном балансе.
     */
    OperatorCommandResult getBalance(Set<Integer> IDs);

    /**
     * Выполняет сброс банкоматов департамента в начальное
     * состояние, возвращает информацию действии.
     * @return Инстанс, хранящий информацию о выполненном действии
     */
    OperatorCommandResult resetState();

    /**
     * Добавляет банкомат(посещаемый элемент) в департаментю
     * @param visitedElement банкомат (посещаемый элемент)
     * @param caretaker Опекун хранителя банкомата
     */
    void addAtm(VisitedElement visitedElement, Caretaker caretaker);
}
