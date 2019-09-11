package ru.otus.kasymbekovPN.HW07.department.atm.visitor;

import ru.otus.kasymbekovPN.HW07.department.atm.AtmImpl;
import ru.otus.kasymbekovPN.HW07.department.operator.command.OperatorCommand;
import ru.otus.kasymbekovPN.HW07.department.operator.result.BaseOCR;
import ru.otus.kasymbekovPN.HW07.department.operator.result.OperatorCommandResult;
import ru.otus.kasymbekovPN.HW07.utils.Caretaker;

import java.util.Map;

/**
 * Визитор, сбрасывающий банкоматы департамента в начальное
 * состояние
 */
public class TotalResetStateVisitor implements Visitor {

    /**
     * Опекуны хранителей состояний банкоманов
     *  + ключ : идентификатор банкомата
     *  + значение : опрекун
     */
    private Map<Integer, Caretaker> caretakers;

    /**
     * Конструктор
     * @param caretakers Опекуны
     */
    public TotalResetStateVisitor(Map<Integer, Caretaker> caretakers) {
        this.caretakers = caretakers;
    }

    /**
     * Посещение инстанса банкомата
     * @param atmImpl Инстанс для посещения
     */
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

    /**
     * Геттер результата посещений
     * @return Результат посещений
     */
    public OperatorCommandResult getResult(){
        return new BaseOCR(OperatorCommand.TOTAL_RESET_STATE_REQUEST);
    }
}
