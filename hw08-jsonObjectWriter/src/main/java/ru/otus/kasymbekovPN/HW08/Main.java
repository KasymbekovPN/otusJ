package ru.otus.kasymbekovPN.HW08;

import com.google.gson.Gson;
import ru.otus.kasymbekovPN.HW08.experimentVictims.EV1;
import ru.otus.kasymbekovPN.HW08.javaObjectWriter.JavaObjectWriterImpl;


public class Main {

    //< строки как перечисления !!!!!

    static private String OFFSET = "  ";

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {

        final EV1 original = new EV1();
        var jsonString = new JavaObjectWriterImpl(original, OFFSET).getJsonString();

        System.out.println(original);

        EV1 restored = new Gson().fromJson(jsonString, EV1.class);

        System.out.println(restored);

        System.out.println(original.equals(restored));

//        final Gson gson = new Gson();
//        final EV1 ev1 = new EV1();
//        final String s = gson.toJson(ev1);
//        System.out.println(s);



//        Gson gson = new Gson();
//        final A a = new A(10, 100);
//        System.out.println(a);
//
//        final String s = gson.toJson(a);
//        System.out.println(s);
//
//        A a1 = gson.fromJson(s, A.class);
//        System.out.println(a1);
    }

    //<
//    private static class A{
//        int i;
//        float d;
//
//        public A(int i, float d) {
//            this.i = i;
//            this.d = d;
//        }
//
//        @Override
//        public String toString() {
//            return "A{" +
//                    "i=" + i +
//                    ", d=" + d +
//                    '}';
//        }
//    }

    //<
//    static class BagOfPrimitives {
//        private final int value1;
//        private final String value2;
//        private final int value3;
//
//        BagOfPrimitives(int value1, String value2, int value3) {
//            this.value1 = value1;
//            this.value2 = value2;
//            this.value3 = value3;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            BagOfPrimitives that = (BagOfPrimitives) o;
//
//            if (value1 != that.value1) return false;
//            if (value3 != that.value3) return false;
//            return Objects.equals(value2, that.value2);
//        }
//
//        @Override
//        public String toString() {
//            return "BagOfPrimitives{" +
//                    "value1=" + value1 +
//                    ", value2='" + value2 + '\'' +
//                    ", value3=" + value3 +
//                    '}';
//        }
//    }

}
