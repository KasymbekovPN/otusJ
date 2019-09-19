package ru.otus.kasymbekovPN.HW08.visitedElement;

import ru.otus.kasymbekovPN.HW08.visitor.Visitor;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс, реализующий функционал "массив как посещаемый
 * элемент"
 *
 * VE - visited element
 */
public class ArrayVE extends ComplexDataVE implements VisitedElement {

    /**
     * Конструктор
     * @param field Поле массива
     * @param instance Инстанс масиива
     */
    ArrayVE(Field field, Object instance) {
        super(field, instance);
    }

    /**
     * Метод, принимающий визитор
     * @param visitor визитор
     */
    @Override
    public void accept(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {
        visitor.visit(this);
    }

    /**
     * Обход массива.
     * @param visitor визитор
     */
    public void traverse(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {

        if (instance != null){
            Class<?> type = instance.getClass().getComponentType();
            Set<Class> interfaces = new HashSet<>(Arrays.asList(type.getInterfaces()));

            if (type.isPrimitive()){
                boolean first = true;
                for (int i = 0; i < Array.getLength(instance); i++){
                    first = addDelimiter(first, visitor, false);
                    Object o = Array.get(instance, i);
                    new PrimitiveVE(null, o).accept(visitor);
                }
            } else {
                var objects = (Object[]) this.instance;
                boolean firstIteration = true;
                for (Object object : objects) {
                    firstIteration = addDelimiter(firstIteration, visitor, false);
                    if (object == null){
                        new NullVE().accept(visitor);
                    } else if (type.isArray()) {
                        new ArrayVE(null, object).accept(visitor);
                    } else if (interfaces.contains(Collection.class)){
                        new CollectionVE(null, (Collection) object).accept(visitor);
                    } else if (interfaces.contains(CharSequence.class)){
                        new CharSequenceVE(null, object).accept(visitor);
                    } else {
                        new ObjectVE(null, object).accept(visitor);
                    }
                }
            }
        }
    }
}
