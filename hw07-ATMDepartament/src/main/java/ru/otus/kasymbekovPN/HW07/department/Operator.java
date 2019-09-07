package ru.otus.kasymbekovPN.HW07.department;

import ru.otus.kasymbekovPN.HW07.department.command.BaseCmd;
import ru.otus.kasymbekovPN.HW07.department.command.OperatorCommand;
import ru.otus.kasymbekovPN.HW07.department.command.Command;
import ru.otus.kasymbekovPN.HW07.department.command.CommandExtracting;
import ru.otus.kasymbekovPN.HW07.department.command.results.BaseCR;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;

import java.util.HashMap;
import java.util.Map;

public class Operator {

    //< replace with map with key - enum !!!
//    private Command totalBalanceRequest;
    //<
    private Map<OperatorCommand, Command> commands;

    public Operator(){
        this.commands = new HashMap<>();
    }
    //<
//    public Operator(Command totalBalanceRequest) {
//        this.totalBalanceRequest = totalBalanceRequest;
//    }

    public Operator setCommand(Command command){
        var bc = (BaseCmd) command;
        commands.put(bc.getOperatorCommand(), command);
        return this;
        //<
//        CommandExtracting ce = (CommandExtracting) command;
//        commands.put(ce.getOperatorCommand(), command);
//        return this;
    }

    public CommandResult execute(OperatorCommand operatorCommand){
        if (commands.containsKey(operatorCommand)){
            Command command = commands.get(operatorCommand);
            command.execute();
            return ((BaseCmd)command).getResult();
        }

        return new BaseCR(OperatorCommand.NONE);
    }
    //<
//    public void totalBalanceRequestEx() {
//        totalBalanceRequest.execute();
//    }
}
