package ru.otus.kasymbekovPN.HW07;

import ru.otus.kasymbekovPN.HW07.department.atm.Atm;
import ru.otus.kasymbekovPN.HW07.department.atm.AtmImpl;
import ru.otus.kasymbekovPN.HW07.department.atm.visitor.VisitedElement;
import ru.otus.kasymbekovPN.HW07.department.banknotes.BanknoteHeapImpl;
import ru.otus.kasymbekovPN.HW07.department.*;
import ru.otus.kasymbekovPN.HW07.department.operator.Operator;
import ru.otus.kasymbekovPN.HW07.department.operator.command.OperatorCommand;
import ru.otus.kasymbekovPN.HW07.department.operator.command.SelectiveBalanceReqOperatorCmd;
import ru.otus.kasymbekovPN.HW07.department.operator.command.TotalBalanceReqOperatorCmd;
import ru.otus.kasymbekovPN.HW07.department.operator.command.TotalResetStateReqOperatorCmd;
import ru.otus.kasymbekovPN.HW07.utils.CaretakerImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
    java -jar hw07-ATM-jar-with-dependencies.jar
 */
public class Main {
    public static void main(String[] args) {

        /*
            Создаём банкоматы
         */
        int[] numbers = {1_000, 2_000, 3_000, 4_000, 5_000};
        List<VisitedElement> atmList = new ArrayList<>();
        for (int number : numbers) {
            atmList.add(
                    AtmImpl.makeInstance(number, new BanknoteHeapImpl())
            );
        }

        /*
            Создаем департамент и добавляем в него банкоматы
         */
        Department department = new DepartmentImpl();
        for (VisitedElement visitedElement : atmList) {
            department.addAtm(visitedElement, new CaretakerImpl());
        }

        /*
            Создаем команды
         */
        var totalBalanceRequestCmd = new TotalBalanceReqOperatorCmd(department);
        var selectiveBalanceRequestCmd = new SelectiveBalanceReqOperatorCmd(department,
                new HashSet<>() {{
                    add(1);
                    add(2);
                    add(10);
                }});
        var totalResetStateRequestCmd = new TotalResetStateReqOperatorCmd(department);

        /*
            Создаем оператора добавляем ему команды
         */
        var operator = new Operator()
                .setCommand(totalBalanceRequestCmd)
                .setCommand(selectiveBalanceRequestCmd)
                .setCommand(totalResetStateRequestCmd);

        /*
            Выполняет выборочный запрос баланса банкоматов департамента
         */
        var result = operator.execute(OperatorCommand.SELECTIVE_BALANCE_REQUEST);
        result.display();

        /*
            Выполняем полный запрос баланса банкоматов департамента
         */
        result = operator.execute(OperatorCommand.TOTAL_BALANCE_REQUEST);
        result.display();

        /*
            Изымаем из банкоматов по 1_000_000
         */
        for (VisitedElement visitedElement : atmList) {
            Atm atm = (Atm) visitedElement;
            atm.sub(1_000_000, new BanknoteHeapImpl());
        }

        /*
            Выполняем полный запрос баланса банкоматов департамента
         */
        result = operator.execute(OperatorCommand.TOTAL_BALANCE_REQUEST);
        result.display();

        /*
            Восстанавливаем начальное состояние банкоматов
         */
        result = operator.execute(OperatorCommand.TOTAL_RESET_STATE_REQUEST);
        result.display();

        /*
            Выполняем полный запрос баланса банкоматов департамента
         */
        result = operator.execute(OperatorCommand.TOTAL_BALANCE_REQUEST);
        result.display();
    }
}
