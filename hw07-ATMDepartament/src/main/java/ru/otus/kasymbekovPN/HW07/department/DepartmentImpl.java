package ru.otus.kasymbekovPN.HW07.department;

import ru.otus.kasymbekovPN.HW07.department.atm.Atm;
import ru.otus.kasymbekovPN.HW07.department.atm.visitor.SelectiveBalanceVisitor;
import ru.otus.kasymbekovPN.HW07.department.atm.visitor.TotalBalanceVisitor;
import ru.otus.kasymbekovPN.HW07.department.atm.visitor.TotalResetStateVisitor;
import ru.otus.kasymbekovPN.HW07.department.atm.visitor.VisitedElement;
import ru.otus.kasymbekovPN.HW07.department.operator.result.OperatorCommandResult;
import ru.otus.kasymbekovPN.HW07.utils.Caretaker;
import ru.otus.kasymbekovPN.HW07.utils.Originator;

import java.util.*;

/**
 * Класс, реализующий департамент с банкоматами
 */
public class DepartmentImpl implements Department{

    /**
     * Банкоматы, предназначенные для визита
     */
    private Set<VisitedElement> visitedElements;

    /**
     * ключ : идентификатор банкомата
     * значение : Опекуп хранителя состояния банкомата
     */
    private Map<Integer, Caretaker> caretakers;

    /**
     * Конструктор
     */
    public DepartmentImpl() {
        this.visitedElements = new HashSet<>();
        this.caretakers = new HashMap<>();
    }

    /**
     * Возвращает информацию об общем балансе департамента
     * банкоматов.
     * @return Инстанс, хранящий информацию об общем балансе.
     */
    @Override
    public OperatorCommandResult getBalance() {
        var totalBalanceVisitor = new TotalBalanceVisitor();
        for (VisitedElement visitedElement : visitedElements) {
            visitedElement.accept(totalBalanceVisitor);
        }

        return totalBalanceVisitor.getResult();
    }

    /**
     * Возвращает информацию о выборочном балансе департемента
     * банкоматов. Возвращает суммарный баланс тех банкоматов,
     * идентификаторы которых представлены в IDs
     * @param IDs Идентификаторы банктоматов
     * @return Инстанс, хранящий информацию о выборочном балансе.
     */
    @Override
    public OperatorCommandResult getBalance(Set<Integer> IDs) {
        var selectiveBalanceVisitor = new SelectiveBalanceVisitor(IDs);
        for (VisitedElement visitedElement : visitedElements) {
            visitedElement.accept(selectiveBalanceVisitor);
        }

        return selectiveBalanceVisitor.getResult();
    }

    /**
     * Выполняет сброс банкоматов департамента в начальное
     * состояние, возвращает информацию действии.
     * @return Инстанс, хранящий информацию о выполненном действии
     */
    @Override
    public OperatorCommandResult resetState() {
        var totalResetStateVisitor = new TotalResetStateVisitor(caretakers);
        for (VisitedElement visitedElement : visitedElements) {
            visitedElement.accept(totalResetStateVisitor);
        }

        return totalResetStateVisitor.getResult();
    }

    /**
     * Добавляет банкомат(посещаемый элемент) в департаментю
     * Добавляет опекуп и помещает в него хранитель, вновь
     * созданного банкоматаю
     * @param visitedElement банкомат (посещаемый элемент)
     * @param caretaker Опекун хранителя банкомата
     */
    @Override
    public void addAtm(VisitedElement visitedElement, Caretaker caretaker) {
        var atm = (Atm) visitedElement;
        var originator = (Originator) visitedElement;
        int id = atm.getID();

        if (!visitedElements.contains(visitedElement)){
            visitedElements.add(visitedElement);

            caretaker.setMemento(originator.createMemento());
            caretakers.put(id, caretaker);
        }
    }
}
