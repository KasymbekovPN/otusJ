package ru.otus.kasymbekovPN.HW09.visitor;

import java.lang.reflect.Field;

/**
 * Класс, позволяющий посетить строку
 */
public class StringVE implements VisitedElement, VisitedElementData {

    private Field field;
    private Object instance;

    public StringVE(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    /**
     * Функция, принимающая визитор
     * @param visitor визитор
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Геттер имени
     * @return имя
     */
    @Override
    public String getName() {
        return field.getName();
    }

    /**
     * Геттер инстанса
     * @return инстанса
     */
    @Override
    public Object getInstance() {
        return instance;
    }

    /**
     * Проверяет есть ли у посещаемого элемента требуемая аннотация
     * @param annotation аннотация
     * @return результат проверки
     */
    @Override
    public boolean isAnnotationPresent(Class annotation) {
        return field.isAnnotationPresent(annotation);
    }
}
