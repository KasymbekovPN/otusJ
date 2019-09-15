package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.util.Optional;

public class VisitorImpl implements Visitor, JsonFormat {

    private StringBuilder jsonString;
    private String offset;
    private int offsetCounter;

    VisitorImpl(String offset) {
        this.offset = offset;
        this.offsetCounter = 0;
        this.jsonString = new StringBuilder();
    }

    @Override
    public void visit(PrimitiveVisitedElement primitiveVisitedElement) throws NoSuchFieldException, IllegalAccessException {
        final Optional<String> line = primitiveVisitedElement.getLine();
        line.ifPresent(s -> jsonString.append(s));
    }

    //< обобщить с ArrayVisitedElement
    @Override
    public void visit(ObjectVisitedElement objectVisitedElement) throws IllegalAccessException, NoSuchFieldException {
        if (objectVisitedElement.instanceNotNull()){
            var fieldName = objectVisitedElement.getFieldName();
            fieldName.ifPresent(s -> jsonString.append(s).append(Txt.COLON.get()));

            jsonString.append(Txt.OPEN_BRACE.get());
            objectVisitedElement.traverse(this);
            jsonString.append(Txt.CLOSE_BRACE.get());
        }
    }

    //< обобщить с ObjectVisitedElement
    @Override
    public void visit(ArrayVisitedElement arrayVisitedElement) throws IllegalAccessException, NoSuchFieldException {
        if (arrayVisitedElement.instanceNotNull()){
            var fieldName = arrayVisitedElement.getFieldName();
            fieldName.ifPresent(s -> jsonString.append(s).append(Txt.COLON.get()));

            jsonString.append(Txt.OPEN_SQ_BRACKET.get());
            arrayVisitedElement.traverse(this);
            jsonString.append(Txt.CLOSE_SQ_BRACKET.get());
        }
    }

    @Override
    public void visit(ArPrimitiveVisitedElement arPrimitiveVisitedElement) {
        final Optional<String> line = arPrimitiveVisitedElement.getLine();
        line.ifPresent(s -> jsonString.append(s));
    }

    @Override
    public void visit(CollectionVisitedElement collectionVisitedElement) throws IllegalAccessException, NoSuchFieldException {
        if (collectionVisitedElement.instanceNotNull()){
            var fieldName = collectionVisitedElement.getFieldName();
            fieldName.ifPresent(s -> jsonString.append(s).append(Txt.COLON.get()));

            jsonString.append(Txt.OPEN_SQ_BRACKET.get());
            collectionVisitedElement.traverse(this);
            jsonString.append(Txt.CLOSE_SQ_BRACKET.get());
        }
    }

    @Override
    public void visit(CharSequenceVE charSequenceVE) {
        Optional<String> line = charSequenceVE.getLine();
        line.ifPresent(s->jsonString.append(s));
    }

    @Override
    public void visit(NullVE nullVE) {
        jsonString.append(Txt.NULL.get());
    }

    void addDelimiter(){
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
    StringBuilder getJsonString(){
        return jsonString;
    }
}
