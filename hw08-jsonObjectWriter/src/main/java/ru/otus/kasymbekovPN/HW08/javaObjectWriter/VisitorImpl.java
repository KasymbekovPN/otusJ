package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.util.Optional;

public class VisitorImpl implements Visitor, JsonFormat {

    private StringBuilder jsonString;
    private String offset;
    private int offsetCounter;

    public VisitorImpl(String offset) {
        this.offset = offset;
        this.offsetCounter = 0;
        this.jsonString = new StringBuilder();
    }

    @Override
    public void visit(PrimitiveVisitedElement primitiveVisitedElement){
        final Optional<String> line = primitiveVisitedElement.getLine();
        line.ifPresent(s -> jsonString.append(s));
    }

    @Override
    public void visit(ObjectVisitedElement objectVisitedElement) {

        //< !!! name : {}

        openBrace();

        objectVisitedElement.traverse(this);

        closeBrace();
    }

    public void addDelimiter(){
        jsonString.append(",");
    }

    //< interface ???
    @Override
    public void openBrace() {
        jsonString.append("{");
    }

    //< interface ???
    @Override
    public void closeBrace() {
        jsonString.append("}");
    }

    //< interface
    public StringBuilder getJsonString(){
        return jsonString;
    }
}
