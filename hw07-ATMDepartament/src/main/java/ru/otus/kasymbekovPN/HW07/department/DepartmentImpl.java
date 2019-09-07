package ru.otus.kasymbekovPN.HW07.department;

import ru.otus.kasymbekovPN.HW07.atm.Atm;
import ru.otus.kasymbekovPN.HW07.atm.visitor.SelectiveBalanceVisitor;
import ru.otus.kasymbekovPN.HW07.atm.visitor.TotalBalanceVisitor;
import ru.otus.kasymbekovPN.HW07.atm.visitor.TotalResetStateVisitor;
import ru.otus.kasymbekovPN.HW07.atm.visitor.VisitedElement;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;
import ru.otus.kasymbekovPN.HW07.utils.Caretaker;
import ru.otus.kasymbekovPN.HW07.utils.CaretakerImpl;
import ru.otus.kasymbekovPN.HW07.utils.Memento;
import ru.otus.kasymbekovPN.HW07.utils.Originator;

import java.util.*;

/**
 * Класс, реализующий департамент с банкоматами
 */
//public class Department implements ObservableDepartment {
//<
public class DepartmentImpl implements Department{

    //<
//    /**
//     * Мапа, хранящая пары :
//     *  ключ - идентификатор банкомата.
//     *  значение - ссылка на инстанс.
//     */
//    private Map<Integer, DepartmentObserver> observers;

    //< !!!
//    private Set<VisitedElement> visitedElements;
    //<

    //< replace to set
    private Map<Integer, VisitedElement> visitedElements;

    private Map<Integer, Caretaker> caretakers;
    //<
//    /**
//     * "Опекун", хранящий начальные состояния банкоматов.
//     */
//    private Caretaker caretaker;

    public DepartmentImpl() {
        this.visitedElements = new HashMap<>();
        this.caretakers = new HashMap<>();
    }
    //<
//    /**
//     * Конструктор
//     * @param caretaker "опекун"
//     */
//    public DepartmentImpl(Caretaker caretaker){
//        this.observers = new HashMap<>();
//        this.caretaker = caretaker;
//    }

    //< !!! comment
    @Override
    public CommandResult getBalance() {
        var totalBalanceVisitor = new TotalBalanceVisitor();
//        for (VisitedElement visitedElement : visitedElements) {
//            visitedElement.accept(totalBalanceVisitor);
//        }
        //<
        for (Map.Entry<Integer, VisitedElement> entry : visitedElements.entrySet()){
            entry.getValue().accept(totalBalanceVisitor);
        }

        return totalBalanceVisitor.getResult();
        //<
//        return totalBalanceVisitor.getBalance();
    }

    @Override
    public CommandResult getBalance(Set<Integer> IDs) {
        var selectiveBalanceVisitor = new SelectiveBalanceVisitor(IDs);
        for (Map.Entry<Integer, VisitedElement> entry : visitedElements.entrySet()){
            entry.getValue().accept(selectiveBalanceVisitor);
        }

        return selectiveBalanceVisitor.getResult();
    }

    @Override
    public CommandResult resetState() {
        var totalResetStateVisitor = new TotalResetStateVisitor(caretakers);
        for (Map.Entry<Integer, VisitedElement> entry : visitedElements.entrySet()){
            entry.getValue().accept(totalResetStateVisitor);
        }
        return totalResetStateVisitor.getResult();
    }

    //< !!!
    @Override
    public void addVisitedElement(VisitedElement visitedElement, Caretaker caretaker) {
        var atm = (Atm) visitedElement;
        var originator = (Originator) visitedElement;
        int id = atm.getID();

        if (!visitedElements.containsKey(id)){
            visitedElements.put(id, visitedElement);

            caretaker.setMemento(originator.createMemento());
            caretakers.put(id, caretaker);
        }
        //<
//        visitedElements.add(visitedElement);
    }

    //<
//    /**
//     * Подписываем наблюдателя
//     * @param departmentObserver Наблюдатель
//     * @return Результат действия
//     */
//    @Override
//    public DepartmentActionResult subscribe(DepartmentObserver departmentObserver) {
//        boolean success = false;
//        if (!observers.containsKey(departmentObserver.getID())){
//            observers.put(departmentObserver.getID(), departmentObserver);
//            caretaker.setMemento(departmentObserver.getID(), departmentObserver.getState());
//            success = true;
//        }
//
//        return new DepartmentScribeResult(
//                new DepartmentActionResultImpl(DepartmentAction.SUBSCRIBE),
//                departmentObserver,
//                success
//        );
//    }

    //<
//    /**
//     * Отписываем наблюдателя
//     * @param departmentObserver Наблюдатель
//     * @return Результат действия
//     */
//    @Override
//    public DepartmentActionResult unsubscribe(DepartmentObserver departmentObserver) {
//        boolean success = false;
//        if (observers.containsKey(departmentObserver.getID())){
//            observers.remove(departmentObserver.getID());
//            caretaker.remove(departmentObserver.getID());
//            success = true;
//        }
//
//        return new DepartmentScribeResult(
//                new DepartmentActionResultImpl(DepartmentAction.UNSUBSCRIBE),
//                departmentObserver,
//                success
//        );
//    }

    //<
//    /**
//     * Запрашивает баланс у всех, подписаных на реализацию
//     * Observable, наблюдателей
//     * @return Результат действия
//     */
//    @Override
//    public DepartmentActionResult balanceRequest() {
//        int totalBalance = 0;
//        for (Map.Entry<Integer, DepartmentObserver> entry : observers.entrySet()){
//            totalBalance += entry.getValue().getBalance();
//        }
//
//        return new DepartmentTotalBalanceReqResult(
//                new DepartmentActionResultImpl(DepartmentAction.TOTAL_REQ_BALANCE),
//                totalBalance
//        );
//    }

    //<
//    /**
//     * Запрашавает баланс у налюдателей, чьи идентификаторы
//     * представленны в списке.
//     * @param ids Идентификаторы наблюдателей
//     * @return Результат действия
//     */
//    @Override
//    public DepartmentActionResult balanceRequest(Set<Integer> ids) {
//        Map<Integer, Boolean> obsMap = new HashMap<>();
//        int selectiveBalance = 0;
//        for (Integer id : ids) {
//            if (observers.containsKey(id)){
//                selectiveBalance += observers.get(id).getBalance();
//                obsMap.put(id, true);
//            } else {
//                obsMap.put(id, false);
//            }
//        }
//
//        return new DepartmentSelectiveBalanceReqResult(
//                new DepartmentActionResultImpl(DepartmentAction.SELECTIVE_REQ_BALANCE),
//                selectiveBalance,
//                obsMap
//        );
//    }

    //<
//    /**
//     * Сбрасывает наблюдателей в исходное состоние.
//     * @return Результат действия
//     */
//    @Override
//    public DepartmentActionResult resetObserverState() {
//        for(Map.Entry<Integer, DepartmentObserver> entry : observers.entrySet()){
//            entry.getValue().setState(
//                    caretaker.getMemento(
//                            entry.getKey()
//                    )
//            );
//        }
//        return new DepartmentActionResultImpl(DepartmentAction.TOTAL_RESET_STATE);
//    }

    //<
//    /**
//     * Сбрасываем наблюдателей, идентификаторы который
//     * представлены в списке, в исходное состояние
//     * @param ids Идентификаторы набдюдателей
//     * @return Результат действия
//     */
//    @Override
//    public DepartmentActionResult resetObserverState(Set<Integer> ids) {
//        Map<Integer, Boolean> obsMap = new HashMap<>();
//        for (Integer id : ids) {
//            if (this.observers.containsKey(id)){
//                this.observers.get(id).setState(caretaker.getMemento(id));
//                obsMap.put(id, true);
//            } else {
//                obsMap.put(id, false);
//            }
//        }
//
//        return new DepartmentSelectiveResetStateResult(
//                new DepartmentActionResultImpl(DepartmentAction.SELECTIVE_RESET_STATE),
//                obsMap
//        );
//    }
}
