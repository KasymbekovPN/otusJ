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

    public JavaObjectWriterImpl(Object instance, String offset) {
        this.instance = instance;

        VisitorImpl visitor = new VisitorImpl(offset);
        var o = new ObjectVisitedElement(null, instance);
        o.accept(visitor);

        //<
        System.out.println(visitor.getJsonString());
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
