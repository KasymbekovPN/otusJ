package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import ru.otus.kasymbekovPN.HW08.visitedElement.*;
import ru.otus.kasymbekovPN.HW08.visitor.VisitorImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс, реализующий функционал сериализации инстанса
 * объекта в json-строку
 */
public class JavaObjectWriterImpl implements JavaObjectWriter {

    /**
     * Json-строка - результат сериализации.
     */
    private String jsonString;

    /**
     * Конструктор
     * @param instance инстанс, сериализуемого объекта
     */
    public JavaObjectWriterImpl(Object instance) throws IllegalAccessException, NoSuchFieldException {
        if (null == instance)
            jsonString = "null";
        else
            fillJsonStr(instance);
    }

    /**
     * Сериализация объекта не равного null в json
     * @param instance инстанс объекта
     */
    private void fillJsonStr(Object instance) throws NoSuchFieldException, IllegalAccessException {
        VisitorImpl visitor = new VisitorImpl();
        var o = new ObjectVE(null, instance);
        o.accept(visitor);

        this.jsonString = visitor.getJsonString().toString();
    }

    /**
     * Возвращает объект как json-строку
     * @return json-строку
     */
    @Override
    public String getObjectAsJsonStr() {
        return jsonString;
    }
}
