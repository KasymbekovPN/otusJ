package ru.otus.kasymbekovPN.HW07.utils;

//< rename ??
public interface Observer {
    /**
     * "Установка связи" с налюдаемым инстансом.
     * @param o налюдаемый инсанс.
     */
    void setConnection(Observable o);

    /**
     * "Сброс соединения" с наблюдаемым инстансом.
     */
    void resetConnection();

    /**
     * Возврашает баланс
     * @return баланс
     */
    int getBalance();

    /**
     * Сброс в начальное состояние
     */
    void reset();
}
