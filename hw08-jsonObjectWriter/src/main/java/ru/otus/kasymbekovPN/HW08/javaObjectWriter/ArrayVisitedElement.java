package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class ArrayVisitedElement extends ComplexDataVisitedElement implements VisitedElement {

    ArrayVisitedElement(Field field, Object instance) {
        super(field, instance);
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {
        visitor.visit(this);
    }

    void traverse(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {

        if (instance != null){
            Class<?> type = instance.getClass().getComponentType();
            Set<Class> interfaces = new HashSet<>(Arrays.asList(type.getInterfaces()));

            if (type.isPrimitive()){
                boolean first = true;
                for (int i = 0; i < Array.getLength(instance); i++){
                    first = addDelimiter(first, visitor, false);
                    Object o = Array.get(instance, i);
                    new ArPrimitiveVisitedElement(o).accept(visitor);
                }
            } else {
                var objects = (Object[]) this.instance;
                boolean firstIteration = true;
                for (Object object : objects) {
                    firstIteration = addDelimiter(firstIteration, visitor, false);
                    if (object == null){
                        new NullVE().accept(visitor);
                    } else if (type.isArray()) {
                        new ArrayVisitedElement(null, object).accept(visitor);
                    } else if (interfaces.contains(Collection.class)){
                        new CollectionVisitedElement(null, (Collection) object).accept(visitor);
                    } else if (interfaces.contains(CharSequence.class)){
                        new CharSequenceVE(null, object).accept(visitor);
                    } else {
                        new ObjectVisitedElement(null, object).accept(visitor);
                    }
                }
            }
            //<
//            if (type.isPrimitive()){
//                int length = Array.getLength(instance);
//                boolean first = true;
//                for (int i = 0; i < length; i++){
//                    first = addDelimiter(first, visitor);
//                    Object o = Array.get(instance, i);
//                    new ArPrimitiveVisitedElement(o).accept(visitor);
//                }
//            } else if (type.isArray()){
//                Object[] objects = (Object[])instance;
//                boolean first = true;
//                for (Object object : objects) {
//                    first = addDelimiter(first, visitor);
//                    if (object == null){
//                        new NullVE().accept(visitor);
//                    } else {
//                        new ArrayVisitedElement(null, object).accept(visitor);
//                    }
//                }
//            } else if (interfaces.contains(Collection.class)){
//                Object[] objects = (Object[]) this.instance;
//                boolean first = true;
//                for (Object object : objects) {
//                    first = addDelimiter(first, visitor);
//                    if (object == null) {
//                        new NullVE().accept(visitor);
//                    } else {
//                        new CollectionVisitedElement(null, (Collection) object).accept(visitor);
//                    }
//                }
//            } else if (interfaces.contains(CharSequence.class)) {
//                Object[] objects = (Object[]) this.instance;
//                boolean first = true;
//                for (Object object : objects) {
//                    first = addDelimiter(first, visitor);
//                    if (object == null) {
//                        new NullVE().accept(visitor);
//                    } else {
//                        new CharSequenceVE(null, object).accept(visitor);
//                    }
//                }
//            } else {
//                Object[] objects = (Object[])instance;
//                boolean first = true;
//                for (Object object : objects) {
//                    first = addDelimiter(first, visitor);
//                    if (object == null) {
//                        new NullVE().accept(visitor);
//                    } else {
//                        new ObjectVisitedElement(null, object).accept(visitor);
//                    }
//                }
//            }
        }
    }
}
