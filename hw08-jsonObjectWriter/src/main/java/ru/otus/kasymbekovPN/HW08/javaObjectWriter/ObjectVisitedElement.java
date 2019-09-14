package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class ObjectVisitedElement extends ComplexDataVisitedElement implements VisitedElement {

    ObjectVisitedElement(Field field, Object instance) {
        super(field, instance);
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
        visitor.visit(this);
    }

    void traverse(Visitor visitor) throws IllegalAccessException {
        if (instance != null){
            Field[] fields = instance.getClass().getDeclaredFields();

            boolean first = true;
            for (Field f : fields) {
                f.setAccessible(true);

                if (Modifier.isStatic(f.getModifiers()))
                    continue;

                first = addDelimiter(first, visitor);

                Class<?> type = f.getType();
                Set<Class> interfaces = new HashSet<>(Arrays.asList(type.getInterfaces()));

                if (type.isPrimitive()) {
                    new PrimitiveVisitedElement(f, instance).accept(visitor);
                } else if (type.isArray()) {
                    new ArrayVisitedElement(f, f.get(instance)).accept(visitor);
                } else if (interfaces.contains(Collection.class)) {
                    new CollectionVisitedElement(f, (Collection) f.get(instance)).accept(visitor);
                } else {
                    new ObjectVisitedElement(f, f.get(instance)).accept(visitor);
                }
            }
        }
    }
}
