package ru.otus.kasymbekovPN.HW07.utils;

import ru.otus.kasymbekovPN.HW07.utils.Caretaker;
import ru.otus.kasymbekovPN.HW07.utils.Memento;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс-смотритель для хранителя
 */
public class CaretakerImpl implements Caretaker {

    /**
     * Хранители
     */
//    private Memento memento;
    //<
    private Map<Integer, Memento> mementos;

    /**
     * Конструктор
     */
    public CaretakerImpl(){
//        this.memento = memento;
        //<
        mementos = new HashMap<>();
    }

    /**
     * Сеттер храниетля
     * @param ID идентификатор хранителя
     * @param memento храниель
     */
    @Override
    public void setMemento(int ID, Memento memento) {
        mementos.put(ID, memento);
    }

    /**
     * Геттер хранителя
     * @param ID индентификатор
     * @return Хранитель
     */
    @Override
    public Memento getMemento(int ID) {
//        return memento;
        //<
        //< !!!! Проверка на существование
        return mementos.get(ID);
    }

    /**
     * Удалить инстанс хранителя по идентификатору
     * @param ID индентификатор
     */
    @Override
    public void remove(int ID) {
        //< ??? Нужна ли проверка на наличие
        mementos.remove(ID);
    }
}
