package ru.otus.kasymbekovPN.HW07.atm.visitor;

import ru.otus.kasymbekovPN.HW07.atm.AtmImpl;
import ru.otus.kasymbekovPN.HW07.department.command.OperatorCommand;
import ru.otus.kasymbekovPN.HW07.department.command.results.BaseCR;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;
import ru.otus.kasymbekovPN.HW07.department.command.results.SelectiveBalanceReqCR;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//< ???
public class SelectiveBalanceVisitor implements Visitor{

    private int balance;

    private Set<Integer> IDs;

    //< ??? rename
    private Map<Integer, Boolean> atmExistMap;

    public SelectiveBalanceVisitor(Set<Integer> IDs) {
        this.balance = 0;
        this.IDs = IDs;

        this.atmExistMap = new HashMap<>();
        for (Integer id : IDs) {
            this.atmExistMap.put(id, false);
        }
    }

    @Override
    public void visit(AtmImpl atmImpl) {
        if (IDs.contains(atmImpl.getID())){
            balance += atmImpl.getBalance();
            atmExistMap.put(atmImpl.getID(), true);
        }
    }

    public CommandResult getResult(){
        return new SelectiveBalanceReqCR(
                new BaseCR(OperatorCommand.SELECTIVE_BALANCE_REQUEST),
                balance,
                atmExistMap
        );
    }

    //<
//    public int getBalance(){
//        return balance;
//    }
//
//    public Map<Integer, Boolean> getAtmExistMap() {
//        return atmExistMap;
//    }
}
