package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import ru.otus.kasymbekovPN.HW08.experimentVictims.EV1;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Класс, реализующий функционал сериализации инстанса
 * объекта в json-формат
 */
public class JavaObjectWriterImpl implements JavaObjectWriter {

    /**
     * Сериализуемый объект
     */
    private Object instance;

    public JavaObjectWriterImpl(Object instance) {
        this.instance = instance;
        traverse();
    }

    private void traverse(){

        //< !!! Call visitedElement for object, arg is instance

        VisitorImpl visitor = new VisitorImpl();

        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            //< ??? check static

            Class<?> fieldType = field.getType();
            if (fieldType.isPrimitive()){
                new PrimitiveVisitedElement(field, instance).accept(visitor);
            }
            //< ? object
            //< ? array
            //< ? collections
        }

//        //<
//        System.out.println(this.instance.getClass().getName());
//
////        EV1 ev1 = (EV1) this.instance;
//
//        for (Field declaredField : this.instance.getClass().getDeclaredFields()) {
//            System.out.println(declaredField);
//        }
    }

    /**
     * Возвращает объект как json-строку
     * @return json-строку
     */
    @Override
    public String getObjectAsJsonStr() {
        return null;
    }
}
