package ru.otus.kasymbekovPN.HW07.department.command.results;

import ru.otus.kasymbekovPN.HW07.department.command.Cmd;

//< !!!
public class BaseCR implements CommandResult {

    private Cmd cmd;

    public BaseCR(Cmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void display() {
        System.out.println("COMMAND NAME : " + cmd.getName());
    }

    @Override
    public Cmd getCmd() {
        return cmd;
    }
}
