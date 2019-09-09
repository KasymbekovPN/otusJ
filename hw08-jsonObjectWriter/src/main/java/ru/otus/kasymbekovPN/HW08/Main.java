package ru.otus.kasymbekovPN.HW08;

import com.google.gson.Gson;
import ru.otus.kasymbekovPN.HW08.experimentVictims.EV1;
import ru.otus.kasymbekovPN.HW08.javaObjectWriter.JavaObjectWriterImpl;

public class Main {
    public static void main(String[] args){

        //<
        System.out.println("Hello111");

        //<
        new JavaObjectWriterImpl(new EV1());

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
