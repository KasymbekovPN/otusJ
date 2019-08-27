package ru.otus.kasymbekovPN.HW07.banknotes;

/**
 * Интерфейс для организации совокупности банкнот одного номинала.
 */
public interface BanknoteHeap {
    /**
     * Функция, осуществляющая подготовку и проверку возможности
     * перенести банкноты из <code>outsideHeap</code> во
     * внутренний хип банкнот реализующего класса.
     * @param outsideHeap внешний хип банкнот
     * @return Возможность операции
     */
    boolean prepareAddAction(BanknoteHeap outsideHeap);

    /**
     * Функция, осуществляющая подготовку и проверку возможности
     * перенести банкноты из внутреннего хипа банкнот реализующего
     * класса в <code>outsideHeap</code>.
     * @param outsideHeap внешний хип банкнот
     * @return Возможность операции
     */
    boolean prepareSubAction(BanknoteHeap outsideHeap);

    /**
     * Подтверждение изменений.
     */
    void confirmChange();

    /**
     * @return Количетсво банктон.
     */
    int getNumber();

    /**
     * Сеттер количесва банкнот
     * @param number количество банкнот
     */
    void setNumber(int number);

    /**
     * @param newNumber Новое количесвто банкнот
     */
    void setNewNumber(int newNumber);

    /**
     * @return Остаток денежных средст хипа
     */
    int getBalance();

    /**
     * @return Номинал банкнот хипа.
     */
    Currency getDenomination();

    /**
     * Генерирует новый инстанс
     * @return новый инстанс
     */
    BanknoteHeap makeNewInstance();

    /**
     * Генерирует новый инстанс
     * @param denomination номинал банкнот нового инстанса
     * @return новый инстанс
     */
    BanknoteHeap makeNewInstance(Currency denomination);

    /**
     * Генерирует новый инстанс
     * @param denomination номинал банкнот
     * @param number количетсво банкнот
     * @return новый инстанс
     */
    BanknoteHeap makeNewInstance(Currency denomination, int number);
}
