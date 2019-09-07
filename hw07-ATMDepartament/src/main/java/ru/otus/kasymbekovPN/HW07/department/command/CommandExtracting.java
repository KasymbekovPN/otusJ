package ru.otus.kasymbekovPN.HW07.department.command;

import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;

//< !!!
public interface CommandExtracting {
    OperatorCommand getOperatorCommand();
    CommandResult getResult();
}
