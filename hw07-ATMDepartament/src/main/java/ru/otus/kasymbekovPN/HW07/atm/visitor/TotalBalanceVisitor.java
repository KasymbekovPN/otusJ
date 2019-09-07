package ru.otus.kasymbekovPN.HW07.atm.visitor;

import ru.otus.kasymbekovPN.HW07.atm.AtmImpl;
import ru.otus.kasymbekovPN.HW07.department.command.OperatorCommand;
import ru.otus.kasymbekovPN.HW07.department.command.results.BaseCR;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;
import ru.otus.kasymbekovPN.HW07.department.command.results.TotalBalanceReqCR;

//< ???
public class TotalBalanceVisitor implements Visitor {

    private int balance;

    public TotalBalanceVisitor() {
        this.balance = 0;
    }

    @Override
    public void visit(AtmImpl atmImpl) {
        balance += atmImpl.getBalance();
    }

    //< interface ???
    public CommandResult getResult(){
        return new TotalBalanceReqCR(
                new BaseCR(OperatorCommand.TOTAL_BALANCE_REQUEST),
                balance
        );
    }
    /*

            commandResult = new TotalBalanceReqCR(
                new BaseCR(Cmd.TOTAL_BALANCE_REQUEST),
                department.getBalance()
        );

     */

//    public int getBalance() {
//        return balance;
//    }
}
