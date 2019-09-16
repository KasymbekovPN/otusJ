package ru.otus.kasymbekovPN.HW08;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import ru.otus.kasymbekovPN.HW08.javaObjectWriter.JavaObjectWriterImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    run JavaObjectWriterImplTest
 */
public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        //<
//        System.out.println("run JavaObjectWriterImplTest");


//        EV1 original = new EV1();
//        String jsonString = new JavaObjectWriterImpl(original).getObjectAsJsonStr();
//        EV1 restored = new Gson().fromJson(jsonString, EV1.class);

        Gson gson = new GsonBuilder().serializeNulls().create();

        String s_null = gson.toJson(null);
        System.out.println("s_null : " + s_null);
        System.out.println(new JavaObjectWriterImpl(null).getObjectAsJsonStr());


        String s_byte = gson.toJson((byte) 1);
        System.out.println("s_byte : " + s_byte);
        System.out.println(new JavaObjectWriterImpl((byte) 1).getObjectAsJsonStr());

        String s_short = gson.toJson((short) 1f);
        System.out.println("s_short : " + s_short);
        System.out.println(new JavaObjectWriterImpl((short) 1f).getObjectAsJsonStr());

        String s_int = gson.toJson(1);
        System.out.println("s_int" + s_int);
        System.out.println(new JavaObjectWriterImpl(1).getObjectAsJsonStr());

        String s_long = gson.toJson(1L);
        System.out.println("s_long" + s_long);
        System.out.println(new JavaObjectWriterImpl(1L).getObjectAsJsonStr());

        String s_float = gson.toJson(1F);
        System.out.println("s_float" + s_float);
        System.out.println(new JavaObjectWriterImpl(1F).getObjectAsJsonStr());

        String s_double = gson.toJson(1D);
        System.out.println("s_double : " + s_double);
        System.out.println(new JavaObjectWriterImpl(1D).getObjectAsJsonStr());

        String s_string = gson.toJson("aaa");
        System.out.println("s_string : " + s_string);
        String sStr = gson.fromJson(s_string, String.class);
        System.out.println("restored s_string : " + sStr);

        String s_char = gson.toJson('a');
        System.out.println("s_char : " + s_char);
        Character character = gson.fromJson(s_char, Character.class);
        System.out.println("restored s_char : " + character);

        String s_int_arr = gson.toJson(new int[]{1, 2, 3});
        System.out.println("s_int_arr : " + s_int_arr);
        ArrayList arrayList = gson.fromJson(s_int_arr, ArrayList.class);
        System.out.println("restored s_int_arr : " + arrayList);

        String s_list = gson.toJson(List.of(1, 2, 3));
        System.out.println(s_list);

        final String s_si = gson.toJson(Collections.singletonList(1));
        System.out.println(s_si);

//        assertEquals(gson.toJson(List.of(1, 2 ,3)), new JavaObjectWriterImpl(List.of(1, 2 ,3)).getObjectAsJsonStr());
//        assertEquals(gson.toJson(Collections.singletonList(1)), new JavaObjectWriterImpl(Collections.singletonList(1)).getObjectAsJsonStr());


        //    @Test
//    void customTest() throws NoSuchFieldException, IllegalAccessException {
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        assertEquals(gson.toJson(null), new JavaObjectWriterImpl(null).getObjectAsJsonStr());
//        assertEquals(gson.toJson((byte)1), new JavaObjectWriterImpl((byte)1).getObjectAsJsonStr());
//        assertEquals(gson.toJson((short)1f), new JavaObjectWriterImpl((short)1f).getObjectAsJsonStr());
//        assertEquals(gson.toJson(1), new JavaObjectWriterImpl(1).getObjectAsJsonStr());
//        assertEquals(gson.toJson(1L), new JavaObjectWriterImpl(1L).getObjectAsJsonStr());
//        assertEquals(gson.toJson(1f), new JavaObjectWriterImpl(1f).getObjectAsJsonStr());
//        assertEquals(gson.toJson(1d), new JavaObjectWriterImpl(1d).getObjectAsJsonStr());
//        assertEquals(gson.toJson("aaa"), new JavaObjectWriterImpl("aaa").getObjectAsJsonStr());
//        assertEquals(gson.toJson('a'), new JavaObjectWriterImpl('a').getObjectAsJsonStr());
//        assertEquals(gson.toJson(new int[] {1, 2, 3}), new JavaObjectWriterImpl(new int[] {1, 2, 3}).getObjectAsJsonStr());
//        assertEquals(gson.toJson(List.of(1, 2 ,3)), new JavaObjectWriterImpl(List.of(1, 2 ,3)).getObjectAsJsonStr());
//        assertEquals(gson.toJson(Collections.singletonList(1)), new JavaObjectWriterImpl(Collections.singletonList(1)).getObjectAsJsonStr());
//
//    }

    }
}
