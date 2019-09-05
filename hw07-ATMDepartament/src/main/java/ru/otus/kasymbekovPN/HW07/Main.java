package ru.otus.kasymbekovPN.HW07;

import ru.otus.kasymbekovPN.HW07.atm.AtmImpl;
import ru.otus.kasymbekovPN.HW07.atm.visitor.VisitedElement;
import ru.otus.kasymbekovPN.HW07.banknotes.BanknoteHeapImpl;
import ru.otus.kasymbekovPN.HW07.department.*;
import ru.otus.kasymbekovPN.HW07.department.command.Cmd;
import ru.otus.kasymbekovPN.HW07.department.command.TotalBalanceRequestCmd;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;

import java.util.ArrayList;
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

        Department department = new DepartmentImpl();
        for (VisitedElement visitedElement : atmList) {
            department.addVisitedElement(visitedElement);
        }

        var totalBalanceRequestCmd = new TotalBalanceRequestCmd(department);

        var operator = new Operator()
                .setCommand(totalBalanceRequestCmd);

        CommandResult execute = operator.execute(Cmd.TOTAL_BALANCE_REQUEST);
        execute.display();

//        Operator operator = new Operator(totalBalanceRequestCmd);
//        operator.totalBalanceRequestEx();

        //<
//        /*
//            Создаём "хранителя"
//         */
//        Caretaker caretaker = new CaretakerImpl();
//
//        /*
//            Создаём департамент
//         */
//        ObservableDepartment department = new Department(caretaker);
//
//        /*
//            Создаём банкоматы
//         */
//        int[] numbers = {1_000, 2_000, 3_000, 4_000, 5_000};
//        List<DepartmentObserver> atmList = new ArrayList<>();
//        for (int number : numbers) {
//            atmList.add(
//                    AtmImpl.makeInstance(number, new BanknoteHeapImpl())
//            );
//        }
//
//        /*
//            Подписываем банкоматы на департамент
//         */
//        List<DepartmentActionResult> actionResults = new ArrayList<>();
//        for (DepartmentObserver departmentObserver : atmList) {
//            actionResults.add(
//                    department.subscribe(departmentObserver)
//            );
//        }
//        printActionResult(actionResults,"Subscribe ATMs to the department");
//
//        /*
//            Запрашиваем баланс со всех банкоматов
//         */
//        actionResults.add(
//                department.balanceRequest()
//        );
//        printActionResult(actionResults, "\nTotal Balance Request");
//
//        /*
//            Выборочный запрос баланса
//         */
//        actionResults.add(
//                department.balanceRequest(new TreeSet<>() {{
//                    add(0);
//                    add(2);
//                    add(4);
//                    add(10);
//                }})
//        );
//        printActionResult(actionResults, "\nSelective Balance Request");
//
//        /*
//            Меняем содержимое банкоматов
//         */
//        System.out.println("\nChange the contents of ATMs - withdrawal 5_000_000");
//        printATMsBalance(atmList, "\nDisplay initial ATM states");
//        for (DepartmentObserver departmentObserver : atmList) {
//            ((Atm)departmentObserver).sub(5_000_000, new BanknoteHeapImpl());
//        }
//        printATMsBalance(atmList, "\nDisplay changed ATM states");
//
//        /*
//            Восстанавливаем начальное состояние банкоматов
//         */
//        actionResults.add(
//                department.resetObserverState()
//        );
//        printActionResult(actionResults, "\nReset state all ATMs");
//        printATMsBalance(atmList, "\nAfter reset state of all ATMs");
//
//        /*
//            Меняем содержимое банкоматов
//        */
//        System.out.println("\nChange the contents of ATMs again - withdrawal 5_000_000");
//        for (DepartmentObserver departmentObserver : atmList) {
//            ((Atm)departmentObserver).sub(5_000_000, new BanknoteHeapImpl());
//        }
//        printATMsBalance(atmList, "\nDisplay changed ATM states");
//
//        /*
//            Выборочно восстанавливаем начальное состояние банкоматов
//         */
//        actionResults.add(
//                department.resetObserverState(new TreeSet<>(){{
//                    add(3);
//                    add(4);
//                    add(5);
//                }})
//        );
//        printActionResult(actionResults, "\nSelective reset ATMs state");
//        printATMsBalance(atmList, "\nReset ATMs with ## 3, 4");
//
//        /*
//            Попытка повторной подписки
//         */
//        for (DepartmentObserver departmentObserver : atmList) {
//            actionResults.add(
//                    department.subscribe(departmentObserver)
//            );
//        }
//        printActionResult(actionResults, "\nRe-subscription attempt");
//
//        /*
//            Отписываем банкоматы от департамента
//         */
//        for (DepartmentObserver departmentObserver : atmList) {
//            actionResults.add(
//                    department.unsubscribe(departmentObserver)
//            );
//        }
//        printActionResult(actionResults, "\nUnsubscribe ATMs from the department");
//
//        /*
//            Попытка повторной отписки
//         */
//        for (DepartmentObserver departmentObserver : atmList) {
//            actionResults.add(
//                    department.unsubscribe(departmentObserver)
//            );
//        }
//        printActionResult(actionResults, "\nRe-unsubscription attempt");
    }

    //<
//    private static void printActionResult(List<DepartmentActionResult> actionResultList, String msg){
//        System.out.println(msg);
//        for (DepartmentActionResult departmentActionResult : actionResultList) {
//            departmentActionResult.display();
//        }
//        actionResultList.clear();
//    }
//
//    private static void printATMsBalance(List<DepartmentObserver> atmList, String msg){
//        System.out.println(msg);
//        for (DepartmentObserver departmentObserver : atmList) {
//            System.out.println("ATM ID : " + departmentObserver.getID() +
//                               "; balance : " + departmentObserver.getBalance());
//        }
//    }
}
