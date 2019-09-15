package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class ObjectVisitedElement extends ComplexDataVisitedElement implements VisitedElement {

    ObjectVisitedElement(Field field, Object instance) {
        super(field, instance);
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {
        visitor.visit(this);
    }

    void traverse(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {
        if (instance != null){
            Field[] fields = instance.getClass().getDeclaredFields();

            boolean first = true;
            for (Field f : fields) {
                f.setAccessible(true);

                if (Modifier.isStatic(f.getModifiers()))
                    continue;

                Object instanceForVE = f.get(instance);

                first = addDelimiter(first, visitor, instanceForVE == null);

                Class<?> type = f.getType();
                Set<Class> interfaces = new HashSet<>(Arrays.asList(type.getInterfaces()));

                if (type.isPrimitive()) {
                    new PrimitiveVisitedElement(f, instance).accept(visitor);
                } else if (type.isArray()) {
                    new ArrayVisitedElement(f, instanceForVE).accept(visitor);
                } else if (interfaces.contains(Collection.class)) {
                    new CollectionVisitedElement(f, (Collection) instanceForVE).accept(visitor);
                } else if (interfaces.contains(CharSequence.class)) {
                    new CharSequenceVE(f, instanceForVE).accept(visitor);
                } else {
                    new ObjectVisitedElement(f, instanceForVE).accept(visitor);
                }
            }
        }
    }
}
