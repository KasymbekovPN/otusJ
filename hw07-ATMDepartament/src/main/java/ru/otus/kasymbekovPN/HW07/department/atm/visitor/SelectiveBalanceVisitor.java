package ru.otus.kasymbekovPN.HW07.department.atm.visitor;

import ru.otus.kasymbekovPN.HW07.department.atm.AtmImpl;
import ru.otus.kasymbekovPN.HW07.department.operator.command.OperatorCommand;
import ru.otus.kasymbekovPN.HW07.department.operator.result.BaseOCR;
import ru.otus.kasymbekovPN.HW07.department.operator.result.OperatorCommandResult;
import ru.otus.kasymbekovPN.HW07.department.operator.result.SelectiveBalanceReqOCR;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Визитор, аккумилирующий выборочный баланс банкоматов
 * департамента
 */
public class SelectiveBalanceVisitor implements Visitor{

    /**
     * Выборочный баланс
     */
    private int balance;

    /**
     * Идентификаторы банкоматов для получения баланса
     */
    private Set<Integer> IDs;

    /**
     * ключ : идентификатор банкомата
     * значение : входит ли банкомат в состав департамента
     */
    private Map<Integer, Boolean> atmExistMap;

    /**
     * Конструктор
     * @param IDs Идентификаторы банкомата
     */
    public SelectiveBalanceVisitor(Set<Integer> IDs) {
        this.balance = 0;
        this.IDs = IDs;

        this.atmExistMap = new HashMap<>();
        for (Integer id : IDs) {
            this.atmExistMap.put(id, false);
        }
    }

    /**
     * Посещение инстанса банкомата
     * @param atmImpl Инстанс для посещения
     */
    @Override
    public void visit(AtmImpl atmImpl) {
        if (IDs.contains(atmImpl.getID())){
            balance += atmImpl.getBalance();
            atmExistMap.put(atmImpl.getID(), true);
        }
    }

    /**
     * Геттер результата посещений
     * @return Результат посещений
     */
    public OperatorCommandResult getResult(){
        return new SelectiveBalanceReqOCR(
                new BaseOCR(OperatorCommand.SELECTIVE_BALANCE_REQUEST),
                balance,
                atmExistMap
        );
    }
}
