package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.util.Optional;

public class PrimitiveVisitedElement implements VisitedElement {

    static private Class clDummy;

    private char dummy;

    private Field field;
    private Object instance;

    public PrimitiveVisitedElement(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Optional<String> getLine(){
        Optional<String> res = Optional.empty();
        StringBuilder line = new StringBuilder(field.getName()).append(":");

        fillClDummy();

        try{
            if (field.getType().equals(clDummy)){
                int i = field.getChar(instance);
                line.append(i);
            } else {
                Object o = field.get(instance);
                line.append(o.toString());
            }
            res = Optional.of(line.toString());
        } catch (IllegalAccessException ex){
            ex.printStackTrace();
        }

        return res;
    }

    //< ??????
    private void fillClDummy(){
        if (clDummy == null){
            try {
                clDummy = getClass().getDeclaredField("dummy").getType();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
