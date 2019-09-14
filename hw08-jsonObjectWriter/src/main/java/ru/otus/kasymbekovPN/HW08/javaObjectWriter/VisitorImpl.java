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

    //< обобщить с ArrayVisitedElement
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

    //< обобщить с ObjectVisitedElement
    @Override
    public void visit(ArrayVisitedElement arrayVisitedElement) throws IllegalAccessException {
        var fieldName = arrayVisitedElement.getFieldName();
        if (arrayVisitedElement.instanceNotNull()){
            fieldName.ifPresent(s -> jsonString.append(s).append(Txt.COLON.get()));

            jsonString.append(Txt.OPEN_SQ_BRACKET.get());
            arrayVisitedElement.traverse(this);
            jsonString.append(Txt.CLOSE_SQ_BRACKET.get());
        } else {
            fieldName.ifPresent(s->jsonString.append(s).append(Txt.COLON.get()).append(Txt.NULL.get()));
        }
    }

    @Override
    public void visit(ArPrimitiveVisitedElement arPrimitiveVisitedElement) {
        final Optional<String> line = arPrimitiveVisitedElement.getLine();
        line.ifPresent(s -> jsonString.append(s));
    }

    @Override
    public void visit(CollectionVisitedElement collectionVisitedElement) throws IllegalAccessException {
        var fieldName = collectionVisitedElement.getFieldName();
        if (collectionVisitedElement.instanceNotNull()){
            fieldName.ifPresent(s -> jsonString.append(s).append(Txt.COLON.get()));

            jsonString.append(Txt.OPEN_SQ_BRACKET.get());
            collectionVisitedElement.traverse(this);
            jsonString.append(Txt.CLOSE_SQ_BRACKET.get());
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
