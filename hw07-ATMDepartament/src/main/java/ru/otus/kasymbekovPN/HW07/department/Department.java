package ru.otus.kasymbekovPN.HW07.department;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;
import ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult.*;
import ru.otus.kasymbekovPN.HW07.utils.Caretaker;
import ru.otus.kasymbekovPN.HW07.utils.ObservableDepartment;
import ru.otus.kasymbekovPN.HW07.utils.DepartmentObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс, реализующий департамент с банкоматами
 */
public class Department implements ObservableDepartment {

    /**
     * Мапа, хранящая пары :
     *  ключ - идентификатор банкомата.
     *  значение - ссылка на инстанс.
     */
    private Map<Integer, DepartmentObserver> observers;

    /**
     * "Опекун", хранящий начальные состояния банкоматов.
     */
    private Caretaker caretaker;

    /**
     * Конструктор
     * @param caretaker "опекун"
     */
    public Department(Caretaker caretaker){
        this.observers = new HashMap<>();
        this.caretaker = caretaker;
    }

    /**
     * Подписываем наблюдателя
     * @param departmentObserver Наблюдатель
     * @return Результат действия
     */
    @Override
    public DepartmentActionResult subscribe(DepartmentObserver departmentObserver) {
        boolean success = false;
        if (!observers.containsKey(departmentObserver.getID())){
            observers.put(departmentObserver.getID(), departmentObserver);
            caretaker.setMemento(departmentObserver.getID(), departmentObserver.getState());
            success = true;
        }

        return new DepartmentScribeResult(
                new DepartmentActionResultImpl(DepartmentAction.SUBSCRIBE),
                departmentObserver,
                success
        );
    }

    /**
     * Отписываем наблюдателя
     * @param departmentObserver Наблюдатель
     * @return Результат действия
     */
    @Override
    public DepartmentActionResult unsubscribe(DepartmentObserver departmentObserver) {
        boolean success = false;
        if (observers.containsKey(departmentObserver.getID())){
            observers.remove(departmentObserver.getID());
            caretaker.remove(departmentObserver.getID());
            success = true;
        }

        return new DepartmentScribeResult(
                new DepartmentActionResultImpl(DepartmentAction.UNSUBSCRIBE),
                departmentObserver,
                success
        );
    }

    /**
     * Запрашивает баланс у всех, подписаных на реализацию
     * Observable, наблюдателей
     * @return Результат действия
     */
    @Override
    public DepartmentActionResult balanceRequest() {
        int totalBalance = 0;
        for (Map.Entry<Integer, DepartmentObserver> entry : observers.entrySet()){
            totalBalance += entry.getValue().getBalance();
        }

        return new DepartmentTotalBalanceReqResult(
                new DepartmentActionResultImpl(DepartmentAction.TOTAL_REQ_BALANCE),
                totalBalance
        );
    }

    /**
     * Запрашавает баланс у налюдателей, чьи идентификаторы
     * представленны в списке.
     * @param ids Идентификаторы наблюдателей
     * @return Результат действия
     */
    @Override
    public DepartmentActionResult balanceRequest(Set<Integer> ids) {
        Map<Integer, Boolean> obsMap = new HashMap<>();
        int selectiveBalance = 0;
        for (Integer id : ids) {
            if (observers.containsKey(id)){
                selectiveBalance += observers.get(id).getBalance();
                obsMap.put(id, true);
            } else {
                obsMap.put(id, false);
            }
        }

        return new DepartmentSelectiveBalanceReqResult(
                new DepartmentActionResultImpl(DepartmentAction.SELECTIVE_REQ_BALANCE),
                selectiveBalance,
                obsMap
        );
    }

    /**
     * Сбрасывает наблюдателей в исходное состоние.
     * @return Результат действия
     */
    @Override
    public DepartmentActionResult resetObserverState() {
        for(Map.Entry<Integer, DepartmentObserver> entry : observers.entrySet()){
            entry.getValue().setState(
                    caretaker.getMemento(
                            entry.getKey()
                    )
            );
        }
        return new DepartmentActionResultImpl(DepartmentAction.TOTAL_RESET_STATE);
    }

    /**
     * Сбрасываем наблюдателей, идентификаторы который
     * представлены в списке, в исходное состояние
     * @param ids Идентификаторы набдюдателей
     * @return Результат действия
     */
    @Override
    public DepartmentActionResult resetObserverState(Set<Integer> ids) {
        Map<Integer, Boolean> obsMap = new HashMap<>();
        for (Integer id : ids) {
            if (this.observers.containsKey(id)){
                this.observers.get(id).setState(caretaker.getMemento(id));
                obsMap.put(id, true);
            } else {
                obsMap.put(id, false);
            }
        }

        return new DepartmentSelectiveResetStateResult(
                new DepartmentActionResultImpl(DepartmentAction.SELECTIVE_RESET_STATE),
                obsMap
        );
    }
}
