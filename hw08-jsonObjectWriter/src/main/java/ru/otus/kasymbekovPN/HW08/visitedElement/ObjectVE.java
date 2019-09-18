package ru.otus.kasymbekovPN.HW08.visitedElement;

import ru.otus.kasymbekovPN.HW08.utils.Txt;
import ru.otus.kasymbekovPN.HW08.visitor.Visitor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, реализующий функционал "объект как посещаемый
 * элемента"
 *
 * VE - visited element
 */
public class ObjectVE extends ComplexDataVE implements VisitedElement {

    private static Class listNClass;
    private static Class singletonListClass;

    /**
     * Интерфейсы сериализуемого истанса
     */
    private Set<Class> instanceInterfaces;

    /**
     * Конструктор
     * @param field поле, посещаемого объекта
     * @param instance инстанс, посещаемого объекта
     */
    public ObjectVE(Field field, Object instance) {
        super(field, instance);

        if (instance != null)
            instanceInterfaces = new HashSet<>(Arrays.asList(instance.getClass().getInterfaces()));
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
     * Обход объекта
     * @param visitor визитор
     */
    public void traverse(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {
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
                    new PrimitiveVE(f, instanceForVE).accept(visitor);
                } else if (type.isArray()) {
                    new ArrayVE(f, instanceForVE).accept(visitor);
                } else if (interfaces.contains(Collection.class)) {
                    new CollectionVE(f, (Collection) instanceForVE).accept(visitor);
                } else if (interfaces.contains(CharSequence.class)) {
                    new CharSequenceVE(f, instanceForVE).accept(visitor);
                } else {
                    new ObjectVE(f, instanceForVE).accept(visitor);
                }
            }
        }
    }

    public Optional<String> getSimpleLine(){

        if (listNClass == null)
            listNClass = List.of(1,2,3).getClass();
        if (singletonListClass == null)
            singletonListClass = Collections.singletonList(1).getClass();

        if (instance instanceof Number)
            return Optional.of(instance.toString());
        else if (instance instanceof Character || instanceInterfaces.contains(CharSequence.class))
            return Optional.of(Txt.DOUBLE_QUOTE.get() + instance.toString() + Txt.DOUBLE_QUOTE.get());
        else if (instance.getClass().equals(listNClass) || instance.getClass().equals(singletonListClass))
            return Optional.of(instance.toString().replace(" ", ""));
        else if (instance instanceof int[]){
            return Optional.of(
                    Arrays.stream((int[])instance).boxed().collect(Collectors.toList()).toString().replace(" ", "")
            );
        } else
            return Optional.empty();
    }
}
