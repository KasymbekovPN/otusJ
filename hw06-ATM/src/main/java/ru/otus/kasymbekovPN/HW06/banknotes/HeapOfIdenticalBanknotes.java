package ru.otus.kasymbekovPN.HW06.banknotes;

/**
 * Интерфейс для организации хипа банкнот одного номинала.
 */
public interface HeapOfIdenticalBanknotes {
    /**
     * Функция осуществляет проверку возможности добавить хип
     * банкнот <code>heap</code> к уже имеющемуся
     * в реализованом классе.
     * @param heap хип банкном, который нужно добавить.
     * @return Успешность операции.
     */
    default boolean add(HeapOfIdenticalBanknotes heap) {
        return false;
    }

    /**
     * Функция осуществляет проверку возможности добавить хип
     * банкнот <code>heap</code> из уже имеющегося
     * в реализованном классе.
     * @param heap хип банкнот, который нужно вычесть.
     * @return Успешность операции.
     */
    default boolean sub(HeapOfIdenticalBanknotes heap) {
        return false;
    }

    /**
     * Подтверждение изменений.
     */
    void confirmChange();

    /**
     * @return Количетсво банктон.
     */
    int getNumber();

    /**
     * Сеттер количества банкнот
     * @param number количество банкнот
     */
    void setNumber(int number);

    /**
     * @return Остаток денежных средст хипа
     */
    int get();

    /**
     * @return Номинал банкнот хипа.
     */
    Currency getDenomination();

    /**
     * Сеттер номинала
     * @param denomination номинал банкнот
     */
    void setDenomination(Currency denomination);

    /**
     * Выводит в консоль информацию о хипе.
     */
    void display();

    /**
     * Клонирует инстанс класса, реализующего интерфейс
     * @return Клон инстанса.
     */
    HeapOfIdenticalBanknotes clone();
}


