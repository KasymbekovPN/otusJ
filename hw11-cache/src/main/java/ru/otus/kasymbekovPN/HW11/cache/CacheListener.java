package ru.otus.kasymbekovPN.HW11.cache;

/**
 * Интерфейс для реализации слушателя кэша
 */
@FunctionalInterface
public interface CacheListener<K, V> {

    /**
     * Уведомить слушателя
     * @param key ключ
     * @param value значение
     * @param action действие
     */
    void notify(K key, V value, String action);
}
