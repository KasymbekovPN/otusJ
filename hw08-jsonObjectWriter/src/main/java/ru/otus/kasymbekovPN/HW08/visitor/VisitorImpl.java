package ru.otus.kasymbekovPN.HW08.visitor;

import ru.otus.kasymbekovPN.HW08.utils.Txt;
import ru.otus.kasymbekovPN.HW08.visitedElement.*;

import java.util.Optional;

/**
 * Класс, реализующий функционал визитора
 */
public class VisitorImpl implements Visitor {

    /**
     * json-строка, серализуемого объекта
     */
    private StringBuilder jsonString;

    /**
     * конструктор
     */
    public VisitorImpl() {
        this.jsonString = new StringBuilder();
    }

    /**
     * Посещение объекта типа ObjectVE
     * @param objectVisitedElement посещаевый объект
     */
    @Override
    public void visit(ObjectVE objectVisitedElement) throws IllegalAccessException, NoSuchFieldException {
        if (objectVisitedElement.instanceNotNull()){

            var fieldName = objectVisitedElement.getFieldName();
            fieldName.ifPresent(s -> jsonString.append(s).append(Txt.COLON.get()));

            Optional<String> simpleLine = objectVisitedElement.getSimpleLine();
            if (simpleLine.isPresent()){
                jsonString.append(simpleLine.get());
            } else {
                jsonString.append(Txt.OPEN_BRACE.get());
                objectVisitedElement.traverse(this);
                jsonString.append(Txt.CLOSE_BRACE.get());
            }
        }
    }

    /**
     * Посещение объекта типа ArrayVE
     * @param arrayVE посещаемый объект
     */
    @Override
    public void visit(ArrayVE arrayVE) throws IllegalAccessException, NoSuchFieldException {
        if (arrayVE.instanceNotNull()){
            var fieldName = arrayVE.getFieldName();
            fieldName.ifPresent(s -> jsonString.append(s).append(Txt.COLON.get()));

            jsonString.append(Txt.OPEN_SQ_BRACKET.get());
            arrayVE.traverse(this);
            jsonString.append(Txt.CLOSE_SQ_BRACKET.get());
        }
    }

    /**
     * Посещение объекта типа PrimitiveVE
     * @param primitiveVE посещаемый объект
     */
    @Override
    public void visit(PrimitiveVE primitiveVE) {
        final Optional<String> line = primitiveVE.getLine();
        line.ifPresent(s -> jsonString.append(s));
    }

    /**
     * Посещение объекта типа CollectionVE
     * @param collectionVE посещаемый объект
     */
    @Override
    public void visit(CollectionVE collectionVE) throws IllegalAccessException, NoSuchFieldException {
        if (collectionVE.instanceNotNull()){
            var fieldName = collectionVE.getFieldName();
            fieldName.ifPresent(s -> jsonString.append(s).append(Txt.COLON.get()));

            jsonString.append(Txt.OPEN_SQ_BRACKET.get());
            collectionVE.traverse(this);
            jsonString.append(Txt.CLOSE_SQ_BRACKET.get());
        }
    }

    /**
     * Посещение объекта типа CharSequenceVE
     * @param charSequenceVE посещаемый объект
     */
    @Override
    public void visit(CharSequenceVE charSequenceVE) {
        Optional<String> line = charSequenceVE.getLine();
        line.ifPresent(s->jsonString.append(s));
    }

    /**
     * Посещение объекта типа NullVE
     * @param nullVE посещаемый объект
     */
    @Override
    public void visit(NullVE nullVE) {
        jsonString.append(Txt.NULL.get());
    }

    /**
     * Добавляет к json-строке разделитель
     */
    public void addDelimiter(){
        jsonString.append(Txt.COMMA.get());
    }

    /**
     * Геттер json-строки
     * @return json-строка
     */
    public StringBuilder getJsonString(){
        return jsonString;
    }
}
