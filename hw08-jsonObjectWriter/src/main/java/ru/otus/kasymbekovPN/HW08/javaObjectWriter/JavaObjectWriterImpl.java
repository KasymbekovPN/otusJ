package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import ru.otus.kasymbekovPN.HW08.experimentVictims.EV1;

import javax.print.DocFlavor;
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

    private String jsonString;

    public JavaObjectWriterImpl(Object instance, String offset) throws IllegalAccessException, NoSuchFieldException {
        this.instance = instance;

        VisitorImpl visitor = new VisitorImpl(offset);
        var o = new ObjectVisitedElement(null, instance);
        o.accept(visitor);

        this.jsonString = visitor.getJsonString().toString();

        //<
//        System.out.println(visitor.getJsonString());
    }

    /**
     * Возвращает объект как json-строку
     * @return json-строку
     */
    @Override
    public String getObjectAsJsonStr() {
        return null;
    }

    public String getJsonString() {
        return jsonString;
    }
}
