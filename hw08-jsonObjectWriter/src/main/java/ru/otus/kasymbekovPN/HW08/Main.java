package ru.otus.kasymbekovPN.HW08;

import com.google.gson.Gson;
import ru.otus.kasymbekovPN.HW08.experimentVictims.EV1;
import ru.otus.kasymbekovPN.HW08.javaObjectWriter.JavaObjectWriterImpl;


public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {

        final EV1 original = new EV1();
        var jsonString = new JavaObjectWriterImpl(original).getObjectAsJsonStr();

        System.out.println(jsonString);

        System.out.println(original);

        EV1 restored = new Gson().fromJson(jsonString, EV1.class);

        System.out.println(restored);

        System.out.println(original.equals(restored));
    }
}
