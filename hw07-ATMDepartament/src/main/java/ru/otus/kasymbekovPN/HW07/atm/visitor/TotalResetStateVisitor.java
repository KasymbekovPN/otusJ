package ru.otus.kasymbekovPN.HW07.atm.visitor;

import ru.otus.kasymbekovPN.HW07.atm.AtmImpl;
import ru.otus.kasymbekovPN.HW07.department.command.OperatorCommand;
import ru.otus.kasymbekovPN.HW07.department.command.results.BaseCR;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;
import ru.otus.kasymbekovPN.HW07.utils.Caretaker;

import java.util.Map;

//< ???
public class TotalResetStateVisitor implements Visitor {

    private Map<Integer, Caretaker> caretakers;

    public TotalResetStateVisitor(Map<Integer, Caretaker> caretakers) {
        this.caretakers = caretakers;
    }

    @Override
    public void visit(AtmImpl atmImpl) {
        if (caretakers.containsKey(atmImpl.getID())){
            atmImpl.setMemento(
                    caretakers.get(
                            atmImpl.getID()
                    ).getMemento()
            );
        }
    }

    //< ??? interface
    public CommandResult getResult(){
        return new BaseCR(OperatorCommand.TOTAL_RESET_STATE_REQUEST);
    }
}
