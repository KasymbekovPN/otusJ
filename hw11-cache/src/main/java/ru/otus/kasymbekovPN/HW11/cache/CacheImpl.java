package ru.otus.kasymbekovPN.HW11.cache;

import java.util.*;

/**
 * Класс, реализующий кэш
 */
public class CacheImpl<K, V> implements Cache<K, V> {

    /**
     * Слушатели
     */
    private Set<CacheListener<K, V>> listeners;

    /**
     * Данные кэша
     */
    private Map<K, V> data;

    /**
     * Геттер набора слушателей
     * @return Набор слушателей
     */
    public Set<CacheListener<K, V>> getListeners() {
        return listeners;
    }

    /**
     * Конструктор
     */
    public CacheImpl() {
        this.data = new HashMap<>();
        this.listeners = Collections.newSetFromMap(new WeakHashMap<CacheListener<K, V>, Boolean>());
    }

    /**
     * Вставка значения по ключу
     * @param key ключ
     * @param value значение
     */
    @Override
    public void put(K key, V value) {
        traverseListeners(
                key,
                value,
                data.containsKey(key)
                        ? CacheActionNames.UPDATE.get()
                        : CacheActionNames.PUT.get()
        );
        data.put(key, value);
    }

    /**
     * Удалени езначения по ключу
     * @param key ключ
     */
    @Override
    public void remove(K key) {
        if (data.containsKey(key)){
            traverseListeners(key, data.get(key), CacheActionNames.REMOVE.get());
            data.remove(key);
        } else {
            traverseListeners(key, null, CacheActionNames.ATTEMPT_REMOVE_NOT_EXISTING.get());
        }
    }

    /**
     * Геттер значения по ключу
     * @param key ключ
     * @return Значение
     */
    @Override
    public V get(K key) {
        if (data.containsKey(key)){
            traverseListeners(key, data.get(key), CacheActionNames.GET.get());
            return data.get(key);
        }

        traverseListeners(key, null, CacheActionNames.ATTEMPT_GET_NOT_EXIST.get());
        return null;
    }

    /**
     * Подписать слушателя на кэш
     * @param listener слушатель
     */
    @Override
    public void subscribeListener(CacheListener<K,V> listener) {
        listeners.add(listener);
    }

    /**
     * ОТписать слушателя от кэша
     * @param listener слушатель
     */
    @Override
    public void unsubscribeListener(CacheListener<K,V> listener) {
        listeners.remove(listener);
    }

    /**
     * Уведомление слушателей
     * @param key ключ
     * @param value значение
     * @param action действие
     */
    private void traverseListeners(K key, V value, String action){
        for (CacheListener<K, V> listener : listeners) {
            listener.notify(key, value, action);
        }
    }
}
