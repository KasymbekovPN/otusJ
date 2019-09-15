package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.util.Optional;

public class CharSequenceVE implements VisitedElement {

    private Field field;
    private Object instance;

    public CharSequenceVE(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {
        visitor.visit(this);
    }

    Optional<String> getLine(){

        StringBuilder line = new StringBuilder();

        if (field != null){
            if (instance != null){
                line.append(Txt.DOUBLE_QUOTE.get())
                        .append(field.getName())
                        .append(Txt.DOUBLE_QUOTE.get())
                        .append(Txt.COLON.get())
                        .append(Txt.DOUBLE_QUOTE.get())
                        .append(instance)
                        .append(Txt.DOUBLE_QUOTE.get());
            }
        } else {
            if (instance != null){
                line.append(Txt.DOUBLE_QUOTE.get())
                        .append(instance)
                        .append(Txt.DOUBLE_QUOTE.get());
            } else {
                line.append(Txt.NULL.get());
            }
        }

        return Optional.of(line.toString());
    }

    //<
//    Optional<String> getLine() throws NoSuchFieldException, IllegalAccessException {
//        Optional<String> res = Optional.empty();
//

//
//        fillClDummy();
//        if (field.getType().equals(clDummy)){
////            int i = field.getChar(instance);
////            line.append(i);
//            //<
//            line.append(String.valueOf(Txt.DOUBLE_QUOTE.get() + field.get(instance) + Txt.DOUBLE_QUOTE.get()));
//        } else {
//            Object o = field.get(instance);
//            line.append(o.toString());
//        }
//        res = Optional.of(line.toString());
//
//        return res;

}
