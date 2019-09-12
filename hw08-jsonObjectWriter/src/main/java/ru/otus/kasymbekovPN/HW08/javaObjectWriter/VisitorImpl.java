package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

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
    public void visit(ObjectVisitedElement objectVisitedElement) throws IllegalAccessException {

        var fieldName = objectVisitedElement.getFieldName();

        if (objectVisitedElement.instanceNotNull()){
            fieldName.ifPresent(s -> jsonString.append(s).append(Txt.COLON.get()));

            jsonString.append(Txt.OPEN_BRACE.get());
            objectVisitedElement.traverse(this);
            jsonString.append(Txt.CLOSE_BRACE.get());
        } else {
            fieldName.ifPresent(s->jsonString.append(s).append(Txt.COLON.get()).append(Txt.NULL.get()));
        }
    }

    public void addDelimiter(){
        jsonString.append(Txt.COMMA.get());
    }

    //< interface ???
    @Override
    public void openBrace() {
        jsonString.append(Txt.OPEN_BRACE.get());
    }

    //< interface ???
    @Override
    public void closeBrace() {
        jsonString.append(Txt.CLOSE_BRACE.get());
    }

    //< interface
    public StringBuilder getJsonString(){
        return jsonString;
    }
}
