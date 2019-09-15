package ru.otus.kasymbekovPN.HW08.visitedElement;

import ru.otus.kasymbekovPN.HW08.visitor.Visitor;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс, реализующий функционал "коллекция как посещаемый элемент"
 *
 * VE - visited element
 */
public class CollectionVE extends ComplexDataVE implements VisitedElement {

    /**
     * Конструктор
     * @param field Поле коллекции
     * @param instance инстанс коллекции
     */
    CollectionVE(Field field, Collection instance) {
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
     * Обход коллекции
     * @param visitor визитор
     */
    public void traverse(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {

        if (instance != null){

            boolean first = true;
            for (Object object : (Collection)instance) {

                first = addDelimiter(first, visitor, false);

                if (object != null) {
                    Class<?> type = object.getClass();
                    Set<Class> interfaces = new HashSet<>(Arrays.asList(type.getInterfaces()));

                    if (type.isPrimitive()) {
                        new PrimitiveVE(null, object).accept(visitor);
                    } else if (type.isArray()) {
                        new ArrayVE(null, object).accept(visitor);
                    } else if (interfaces.contains(Collection.class)) {
                        new CollectionVE(null, (Collection) object).accept(visitor);
                    } else if (interfaces.contains(CharSequence.class)) {
                        new CharSequenceVE(null, object).accept(visitor);
                    } else {
                        new ObjectVE(null, object).accept(visitor);
                    }
                } else {
                    new NullVE().accept(visitor);
                }
            }
        }
    }
}
