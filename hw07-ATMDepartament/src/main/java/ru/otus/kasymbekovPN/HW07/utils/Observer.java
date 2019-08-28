package ru.otus.kasymbekovPN.HW07.utils;

//< rename ??
public interface Observer {

    //<
//    /**
//     * "Установка связи" с налюдаемым инстансом.
//     * @param o налюдаемый инсанс.
//     */
//    void setConnection(Observable o);
//
//    /**
//     * "Сброс соединения" с наблюдаемым инстансом.
//     */
//    void resetConnection();

    /**
     * Возврашает баланс
     * @return баланс
     */
    int getBalance();

    /**
     * Сеттер состояния (через хранителя)
     * @param memento хранитель
     */
    void setState(Memento memento);

    /**
     * Геттер состояния (через храниетля)
     * @return Храниель
     */
    Memento getState();

    /**
     * Геттер идентификатора
     * @return Идентификатор.
     */
    int getID();
}
