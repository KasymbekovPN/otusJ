package ru.otus.kasymbekovPN.HW07.utils;

/**
 * Класс-смотритель для хранителя
 */
public class CaretakerImpl implements Caretaker {

    //< ????
    private Memento memento;

    @Override
    public void setMemento(Memento memento) {
        this.memento = memento;

        //<
//        ((MementoImpl)this.memento).d();
    }

    @Override
    public Memento getMemento() {
        return memento;
    }

    //<
//    public CaretakerImpl makeNewInstance(){
//        return new CaretakerImpl();
//    }

    //<
//    /**
//     * Хранители
//     */
//    private Map<Integer, Memento> mementos;
//
//    /**
//     * Конструктор
//     */
//    public CaretakerImpl(){
//        mementos = new HashMap<>();
//    }
//
//    /**
//     * Сеттер храниетля
//     * @param ID идентификатор хранителя
//     * @param memento храниель
//     */
//    @Override
//    public void setMemento(int ID, Memento memento) {
//        mementos.put(ID, memento);
//    }
//
//    /**
//     * Геттер хранителя
//     * @param ID индентификатор
//     * @return Хранитель
//     */
//    @Override
//    public Memento getMemento(int ID) {
//        return mementos.getOrDefault(ID, null);
//    }
//
//    /**
//     * Удалить инстанс хранителя по идентификатору
//     * @param ID индентификатор
//     */
//    @Override
//    public void remove(int ID) {
//        mementos.remove(ID);
//    }
}
