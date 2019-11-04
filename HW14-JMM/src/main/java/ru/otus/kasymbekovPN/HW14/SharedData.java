package ru.otus.kasymbekovPN.HW14;

/**
 * Интерфейс для реализации класса расшаренных данных
 */
public interface SharedData {

    /**
     * Возвращает имя ожидаемого потока
     * @return Имя потока
     */
    String getWaitedThreadName();

    /**
     * Функция воздействия потока на расшаренные данные
     * @param threadName имя потока
     */
    void calculate(String threadName);

    /**
     * Добавляет имя потока
     * @param threadName имя потока
     */
    void addThreadName(String threadName);

    /**
     * Возвращает текущее значение счетчика
     * @return Значение счетчик
     */
    int getCounter();

    /**
     * Выполнена работа
     * @return Статус завершенности
     */
    boolean isDone();
}
