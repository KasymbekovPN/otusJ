package ru.otus.kasymbekovPN.HW07.department.operator.result;

import ru.otus.kasymbekovPN.HW07.department.operator.command.OperatorCommand;

import java.util.Map;

/**
 * Класс, хранящий результат выполнения команды "SELECTIVE_BALANCE_REQUEST"
 *
 * OCR - Operator's Command Result
 */
public class SelectiveBalanceReqOCR implements OperatorCommandResult {

    /**
     * Базовый результат, содержит только наименование команды
     */
    private OperatorCommandResult initCR;

    /**
     * Выборочный баланс банкоматов департамента
     */
    private int selectiveBalance;

    /**
     * ключ : идентификатор банкомата
     * значение : true - входит в состав департамента
     */
    private Map<Integer, Boolean> atmExistMap;

    /**
     * Конструктор
     * @param initCR Базовый результат
     * @param selectiveBalance Выборочный баланс
     * @param atmExistMap "идентификатор банкомата" : "содержится в департаменте"
     */
    public SelectiveBalanceReqOCR(OperatorCommandResult initCR, int selectiveBalance, Map<Integer, Boolean> atmExistMap) {
        this.initCR = initCR;
        this.selectiveBalance = selectiveBalance;
        this.atmExistMap = atmExistMap;
    }

    /**
     * Печать результата выполнения команды в консоль
     */
    @Override
    public void display() {
        StringBuilder sb = new StringBuilder("\nCOMMAND NAME : ")
                .append(getOperatorCommand().getName())
                .append("; SELECTIVE BALANCE : ")
                .append(selectiveBalance)
                .append("\nATMs:\n");

        for(Map.Entry<Integer, Boolean> entry : atmExistMap.entrySet()){
            sb.append("ATM ID : ")
                    .append(entry.getKey())
                    .append("; EXISTS : ")
                    .append(entry.getValue())
                    .append("\n");
        }

        System.out.println(sb);
    }

    /**
     * Геттер наименования команды
     * @return Наименование команды.
     */
    @Override
    public OperatorCommand getOperatorCommand() {
        return initCR.getOperatorCommand();
    }
}
