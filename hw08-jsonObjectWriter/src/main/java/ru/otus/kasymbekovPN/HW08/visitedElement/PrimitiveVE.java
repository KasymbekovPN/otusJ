package ru.otus.kasymbekovPN.HW08.visitedElement;

import ru.otus.kasymbekovPN.HW08.utils.Txt;
import ru.otus.kasymbekovPN.HW08.visitor.Visitor;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Класс, реализуюший функционал "простой элемент как посещаемый
 * элемент".
 *
 * VE - visited element
 */
public class PrimitiveVE implements VisitedElement {

    /**
     * Поле, посещаемого элемента
     */
    private Field field;

    /**
     * Инстанс элемента
     */
    private Object instance;

    /**
     * Конструктор
     * @param field поле, посещаемого элемента
     * @param instance инстанс элемента
     */
    PrimitiveVE(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    /**
     * Метод, принимающий визитор
     * @param visitor визитор
     */
    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
        visitor.visit(this);
    }

    /**
     * Метод, возвращающий json-строку, соответствующую
     * инстнсу.
     * @return json-строка
     */
    public Optional<String> getLine(){

        var line = new StringBuilder();

        if (field != null){
            line.append(Txt.DOUBLE_QUOTE.get())
                    .append(field.getName())
                    .append(Txt.DOUBLE_QUOTE.get())
                    .append(Txt.COLON.get());
        }

        if (instance.getClass().equals(Character.class)){
            line.append(Txt.DOUBLE_QUOTE.get())
                    .append(instance)
                    .append(Txt.DOUBLE_QUOTE.get());
        } else {
            line.append(instance.toString());
        }

        return Optional.of(line.toString());
    }
}
