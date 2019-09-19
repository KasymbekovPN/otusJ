package ru.otus.kasymbekovPN.HW08.visitedElement;

import ru.otus.kasymbekovPN.HW08.utils.Txt;
import ru.otus.kasymbekovPN.HW08.visitor.Visitor;
import ru.otus.kasymbekovPN.HW08.visitor.VisitorImpl;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Базовый класс, реализующий общий функционал для
 * классов реализующих сложные посещаемые элементы :
 *  + ArrayVE
 *  + CollectionVE
 *  + ObjectVE
 *
 *  VE - visited element
 */
class ComplexDataVE {

    /**
     * Поле, посещаемого объекта
     */
    protected Field field;

    /**
     * Инстанс, посещаемого объекта
     */
    protected Object instance;

    /**
     * Конструктор
     * @param field поле, посещаемого элемента
     * @param instance инстанс, посещаемого элемента.
     */
    ComplexDataVE(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    /**
     * Добавляем с json-строку разделитель
     * @param first Является ли данная итерация первой?
     * @param visitor визитор
     * @param pass Нужно ли пропустить данную итерацию?
     * @return значение first для следующей итерации
     */
    boolean addDelimiter(boolean first, Visitor visitor, boolean pass){
        if (!pass){
            if (first) {
                first = false;
            } else {
                ((VisitorImpl)visitor).addDelimiter();
            }
        }
        return first;
    }

    /**
     * Имя, посещаемого объекта
     * @return Имя, посещаемого объекта.
     */
    public Optional<String> getFieldName(){
        Optional<String> res = Optional.empty();
        if (field != null){
            res = Optional.of(
                    Txt.DOUBLE_QUOTE.get() + field.getName() + Txt.DOUBLE_QUOTE.get()
            );
        }
        return res;
    }

    /**
     * Проверяет: является ли инстанс, посещаемого объекта, null
     * @return Результат проверки.
     */
    public boolean instanceNotNull(){
        return instance != null;
    }
}
