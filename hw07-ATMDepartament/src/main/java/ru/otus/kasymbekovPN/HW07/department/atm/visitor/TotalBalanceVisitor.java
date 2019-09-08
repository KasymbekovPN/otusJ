package ru.otus.kasymbekovPN.HW07.department.atm.visitor;

import ru.otus.kasymbekovPN.HW07.department.atm.AtmImpl;
import ru.otus.kasymbekovPN.HW07.department.operator.command.OperatorCommand;
import ru.otus.kasymbekovPN.HW07.department.operator.result.BaseOCR;
import ru.otus.kasymbekovPN.HW07.department.operator.result.OperatorCommandResult;
import ru.otus.kasymbekovPN.HW07.department.operator.result.TotalBalanceReqOCR;

/**
 * Визитор, аккумулирующий общий баланс банкоматов департамента
 */
public class TotalBalanceVisitor implements Visitor {

    /**
     * Общий баланс банкоматов департамента
     */
    private int balance;

    /**
     * Конструктор
     */
    public TotalBalanceVisitor() {
        this.balance = 0;
    }

    /**
     * Посещение инстанса банкомата
     * @param atmImpl Инстанс для посещения
     */
    @Override
    public void visit(AtmImpl atmImpl) {
        balance += atmImpl.getBalance();
    }

    /**
     * Геттер результата посещений
     * @return Результат посещений
     */
    public OperatorCommandResult getResult(){
        return new TotalBalanceReqOCR(
                new BaseOCR(OperatorCommand.TOTAL_BALANCE_REQUEST),
                balance
        );
    }
}
