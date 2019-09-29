package ru.otus.kasymbekovPN.HW09.visitor;

import ru.otus.kasymbekovPN.HW09.query.QueryChunk;
import ru.otus.kasymbekovPN.HW09.query.QueryChunkImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Визитор для формирования данных. из которых
 * формируется запрос.
 */
public class VisitorImpl implements Visitor, QueryChunkData {

    /**
     * Данные о ключевом поле
     */
    private List<QueryChunk> keyField;

    /**
     * Данные о неключевых полях
     */
    private List<QueryChunk> fields;

    /**
     * Являются ли полученные данные валидными
     */
    private boolean isValid;

    /**
     * Аннотация для определения ключевого поля
     */
    private Class annotationKey;

    /**
     * Конструктор
     * @param annotationKey Аннотация для определения ключевого поля.
     */
    public VisitorImpl(Class annotationKey) {
        this.annotationKey = annotationKey;
        this.keyField = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.isValid = true;
    }

    /**
     * Посетить PrimitiveVE
     * @param primitiveVE посещаемый инстанс
     */
    @Override
    public void visit(PrimitiveVE primitiveVE) {
        fill(primitiveVE);
    }

    /**
     * Посетить StringVE
     * @param stringVE посещаемый инстанс
     */
    @Override
    public void visit(StringVE stringVE) {
        fill(stringVE);
    }

    /**
     * Заполнение данных о полях
     * @param veData посещенный элемент
     */
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

    /**
     * Геттер данных ключевого поля
     * @return данные
     */
    @Override
    public QueryChunk getKeyField() {
        return isValid ? keyField.get(0) : null;
    }

    /**
     * Геттер данных неключевых полей
     * @return данные
     */
    @Override
    public List<QueryChunk> getFields() {
        return isValid ? fields : null;
    }

    /**
     * @return Являются ли данные о полях валидными
     */
    @Override
    public boolean isValid() {
        return isValid;
    }
}
