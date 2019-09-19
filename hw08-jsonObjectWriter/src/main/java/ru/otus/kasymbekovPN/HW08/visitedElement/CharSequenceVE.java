package ru.otus.kasymbekovPN.HW08.visitedElement;

import ru.otus.kasymbekovPN.HW08.utils.Txt;
import ru.otus.kasymbekovPN.HW08.visitor.Visitor;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Класс, реализующий функционал "реализация
 * CharSequence" как посещаемый элемент
 *
 * VE - visited element
 */
public class CharSequenceVE implements VisitedElement {

    /**
     * Поле посещаемого инстанса
     */
    private Field field;

    /**
     * Посещаемый инстанс
     */
    private Object instance;

    /**
     * Конструктор
     * @param field поле посещаемого инстанса
     * @param instance посещаемый инстанс
     */
    CharSequenceVE(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    /**
     * Метод принимающий визитор
     * @param visitor визитор
     */
    @Override
    public void accept(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {
        visitor.visit(this);
    }

    /**
     * Метод, возвращающий json-строку, соответствующую
     * инстнсу.
     * @return json-строка
     */
    public Optional<String> getLine(){

        StringBuilder line = new StringBuilder();

        if (field != null){
            if (instance != null){
                line.append(Txt.DOUBLE_QUOTE.get())
                        .append(field.getName())
                        .append(Txt.DOUBLE_QUOTE.get())
                        .append(Txt.COLON.get())
                        .append(Txt.DOUBLE_QUOTE.get())
                        .append(instance)
                        .append(Txt.DOUBLE_QUOTE.get());
            }
        } else {
            if (instance != null){
                line.append(Txt.DOUBLE_QUOTE.get())
                        .append(instance)
                        .append(Txt.DOUBLE_QUOTE.get());
            } else {
                line.append(Txt.NULL.get());
            }
        }

        return Optional.of(line.toString());
    }
}
