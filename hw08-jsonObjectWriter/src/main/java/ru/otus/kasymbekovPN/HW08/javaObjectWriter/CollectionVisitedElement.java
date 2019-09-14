package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.util.*;

public class CollectionVisitedElement extends ComplexDataVisitedElement implements VisitedElement {

    CollectionVisitedElement(Field field, Collection instance) {
        super(field, instance);
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
        visitor.visit(this);
    }

    void traverse(Visitor visitor) throws IllegalAccessException {

        if (instance != null){

            boolean first = true;
            for (Object object : (Collection)instance) {
                Class<?> type = object.getClass();
                Set<Class> interfaces = new HashSet<>(Arrays.asList(type.getInterfaces()));

                first = addDelimiter(first, visitor);

                if (type.isPrimitive()){
                    new ArPrimitiveVisitedElement(object).accept(visitor);
                } else if (type.isArray()) {
                    new ArrayVisitedElement(null, object).accept(visitor);
                } else if (interfaces.contains(Collection.class)) {
                    new CollectionVisitedElement(null, (Collection) object).accept(visitor);
                } else {
                    new ObjectVisitedElement(null, object).accept(visitor);
                }
            }
        }
    }
}
