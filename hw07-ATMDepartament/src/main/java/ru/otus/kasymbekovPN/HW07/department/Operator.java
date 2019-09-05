package ru.otus.kasymbekovPN.HW07.department;

import ru.otus.kasymbekovPN.HW07.department.command.Cmd;
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
    private Map<Cmd, Command> commands;

    public Operator(){
        this.commands = new HashMap<>();
    }
    //<
//    public Operator(Command totalBalanceRequest) {
//        this.totalBalanceRequest = totalBalanceRequest;
//    }

    public Operator setCommand(Command command){
        CommandExtracting ce = (CommandExtracting) command;
        commands.put(ce.getCmd(), command);
        return this;
    }

    public CommandResult execute(Cmd cmd){
        if (commands.containsKey(cmd)){
            Command command = commands.get(cmd);
            command.execute();
            return ((CommandExtracting)command).getResult();
        }
        return new BaseCR(Cmd.NONE);
    }
    //<
//    public void totalBalanceRequestEx() {
//        totalBalanceRequest.execute();
//    }
}
