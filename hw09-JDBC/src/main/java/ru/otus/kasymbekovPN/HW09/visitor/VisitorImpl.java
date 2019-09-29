package ru.otus.kasymbekovPN.HW09.visitor;

import ru.otus.kasymbekovPN.HW09.query.QueryChunk;
import ru.otus.kasymbekovPN.HW09.query.QueryChunkImpl;

import java.util.ArrayList;
import java.util.List;

public class VisitorImpl implements Visitor, QueryChunkData {

    private List<QueryChunk> keyField;
    private List<QueryChunk> fields;
    private boolean isValid;

    private Class annotationKey;

    public VisitorImpl(Class annotationKey) {
        this.annotationKey = annotationKey;
        this.keyField = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.isValid = true;
    }

    @Override
    public void visit(PrimitiveVE primitiveVE) {
//        if (!primitiveVE.isBadType()){
//            fill(primitiveVE);
//        }
        //<
        fill(primitiveVE);
    }

    @Override
    public void visit(StringVE stringVE) {
        fill(stringVE);
    }

    private void fill(VisitedElementData veData){
        if (veData.isAnnotationPresent(annotationKey)){
            if (keyField.size() == 0){
                keyField.add(new QueryChunkImpl(veData.getName(), veData.getInstance(), true));
            } else {
                isValid = false;
            }
        } else {
            fields.add(new QueryChunkImpl(veData.getName(), veData.getInstance(), false));
        }

        isValid = keyField.size() == 1;
    }

    @Override
    public QueryChunk getKeyField() {
        return isValid ? keyField.get(0) : null;
    }

    @Override
    public List<QueryChunk> getFields() {
        return isValid ? fields : null;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }
}
