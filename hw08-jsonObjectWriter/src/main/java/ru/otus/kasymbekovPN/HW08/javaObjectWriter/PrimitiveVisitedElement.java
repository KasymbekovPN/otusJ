package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.util.Optional;

public class PrimitiveVisitedElement implements VisitedElement {

    static private Class clDummy;

    private char dummy;

    private Field field;
    private Object instance;

    PrimitiveVisitedElement(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    @Override
    public void accept(Visitor visitor) throws NoSuchFieldException, IllegalAccessException {
        visitor.visit(this);
    }

    Optional<String> getLine() throws NoSuchFieldException, IllegalAccessException {
        Optional<String> res = Optional.empty();

        StringBuilder line = new StringBuilder();
        if (field != null){
            line.append(Txt.DOUBLE_QUOTE.get())
                    .append(field.getName())
                    .append(Txt.DOUBLE_QUOTE.get())
                    .append(Txt.COLON.get());
        }

        fillClDummy();
        if (field.getType().equals(clDummy)){
//            int i = field.getChar(instance);
//            line.append(i);
            //<
            line.append(String.valueOf(Txt.DOUBLE_QUOTE.get() + field.get(instance) + Txt.DOUBLE_QUOTE.get()));
        } else {
            Object o = field.get(instance);
            line.append(o.toString());
        }
        res = Optional.of(line.toString());

        return res;

        //<
//        Optional<String> res = Optional.empty();
//
//        StringBuilder line = new StringBuilder();
//        if (field != null){
////            line.append(field.getName()).append(Txt.COLON.get());
//            //<
//            line.append(Txt.DOUBLE_QUOTE.get())
//                    .append(field.getName())
//                    .append(Txt.DOUBLE_QUOTE.get())
//                    .append(Txt.COLON.get());
//        }
//
//        fillClDummy();
//
//        try{
//            if (field.getType().equals(clDummy)){
//                int i = field.getChar(instance);
//                line.append(i);
//            } else {
//                Object o = field.get(instance);
//                line.append(o.toString());
//            }
//            res = Optional.of(line.toString());
//        } catch (IllegalAccessException ex){
//            ex.printStackTrace();
//        }
//
//        return res;
    }

    //< ??????
//    private void fillClDummy(){
//        if (clDummy == null){
//            try {
//                clDummy = getClass().getDeclaredField("dummy").getType();
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    //<
    private void fillClDummy() throws NoSuchFieldException {
        if (clDummy == null){
            clDummy = getClass().getDeclaredField("dummy").getType();
        }
    }
}
