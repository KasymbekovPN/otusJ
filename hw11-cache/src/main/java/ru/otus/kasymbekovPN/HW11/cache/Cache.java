package ru.otus.kasymbekovPN.HW11.cache;

/**
 * Интерфейс для реализации кэша
 */
public interface Cache<K, V> {

    /**
     * Вставка значения по ключу
     * @param key ключ
     * @param value значение
     */
    void put(K key, V value);

    /**
     * Удалени езначения по ключу
     * @param key ключ
     */
    void remove(K key);

    /**
     * Геттер значения по ключу
     * @param key ключ
     * @return Значение
     */
    V get(K key);

    /**
     * Подписать слушателя на кэш
     * @param listener слушатель
     */
    void subscribeListener(CacheListener<K,V> listener);

    /**
     * ОТписать слушателя от кэша
     * @param listener слушатель
     */
    void unsubscribeListener(CacheListener<K,V> listener);

    /**
     * Геттер размера данных
     * @return размер данных
     */
    int size();

    /**
     * Очистить кэш
     */
    void clear();
}
