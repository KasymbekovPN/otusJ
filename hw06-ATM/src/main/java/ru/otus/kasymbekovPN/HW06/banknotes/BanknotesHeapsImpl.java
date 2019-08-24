package ru.otus.kasymbekovPN.HW06.banknotes;

import ru.otus.kasymbekovPN.HW06.utils.Displayable;
import ru.otus.kasymbekovPN.HW06.utils.NumberDiapason;

import java.util.*;

/**
 * Класс для хранения хипов банкнот разных номиналов.
 */
public class BanknotesHeapsImpl implements BanknotesHeaps, Displayable {
    /**
     * Хипы банкнот.
     */
    private Map<Currency, HeapOfIdenticalBanknotes> heaps;

    /**
     * Статический, фабричный метод. Перед созданием объекта
     * производит проверку инициализирующего контейнера (<code>heaps</code>).
     * Если в контейнере отсутсвует хип, соответствующий определенному
     * номиналу, то хип добавляется - за основу для, добавляемого хипа,
     * берётся болванка (<code>dummy</code>)
     * @param heaps инициализирующие хипы
     * @param dummy болванта.
     * @return Инстанс класса.
     */
    public static BanknotesHeapsImpl makeInstance(Map<Currency, HeapOfIdenticalBanknotes> heaps,
                                                  HeapOfIdenticalBanknotes dummy){

        for (Currency currency : Currency.values()) {
            if (!heaps.containsKey(currency)){
                dummy.setDenomination(currency);
                heaps.put(currency, dummy.clone());
            } else {
                heaps.get(currency).setDenomination(currency);
            }
        }

        return new BanknotesHeapsImpl(heaps);
    }

    /**
     * Статический, фабричный метод. Перед созданием объекта производит
     * настройку инициализирующего контейнера (<code>heaps</code>). Каждый хип
     * в контейнере создается с количеством банкнот, соответствующим своему
     * аргументу (<code>number_X</code>). Основой каждого хипа является <code>dummy</code>.
     * Каждый хип в контейнере настраивается со своими: количеством и номиналом.
     * @param number_10 количество банкнот номиналом 10
     * @param number_50 количество банкнот номиналом 50
     * @param number_100 количество банкнот номиналом 100
     * @param number_200 количество банкнот номиналом 200
     * @param number_500 количество банкнот номиналом 500
     * @param number_1000 количество банкнот номиналом 1_000
     * @param number_2000 количество банкнот номиналом 2_000
     * @param number_5000  количество банкнот номиналом 5_000
     * @param dummy болванка
     * @return Инстанс класса
     */
    public static BanknotesHeapsImpl makeInstance(int number_10, int number_50, int number_100,
                                                  int number_200, int number_500, int number_1000,
                                                  int number_2000, int number_5000,
                                                  HeapOfIdenticalBanknotes dummy){
        List<Integer> numberList = new ArrayList<>(){{
            add(number_10);
            add(number_50);
            add(number_100);
            add(number_200);
            add(number_500);
            add(number_1000);
            add(number_2000);
            add(number_5000);
        }};

        Currency[] allItems = Currency.values();
        Map<Currency, HeapOfIdenticalBanknotes> heaps = new HashMap<>();

        for (int i = 0; i < allItems.length; i++){
            Currency currency = allItems[i];
            int number = NumberDiapason.putInRange(numberList.get(i));

            dummy.setNumber(number);
            dummy.setDenomination(currency);
            heaps.put(currency, dummy.clone());
        }

        return new BanknotesHeapsImpl(heaps);
    }

    /**
     * Статический, фабричный метод. Перед созданием инстанса производит
     * настройку инициализируеющего контейнера (<code>heaps</code>). Каждый
     * хип в контейнере создаётся с количеством банкнор равным <code>number</code>
     * Основой каждого хипа является <code>dummy</code>. Каждый хип настраивается
     * соответствующим номиналом.
     * @param number количество банкнот в каждом хипе.
     * @param dummy болванка.
     * @return Инстанс класса.
     */
    public static BanknotesHeapsImpl makeInstance(int number, HeapOfIdenticalBanknotes dummy){
        final Currency[] allItems = Currency.values();
        Map<Currency, HeapOfIdenticalBanknotes> heaps = new HashMap<>();

        number = NumberDiapason.putInRange(number);
        for (Currency currency : allItems) {
            dummy.setNumber(number);
            dummy.setDenomination(currency);
            heaps.put(currency, dummy.clone());
        }

        return new BanknotesHeapsImpl(heaps);
    }

    /**
     * Конструктор
     * @param heaps Инициализирующий контейнер.
     */
    private BanknotesHeapsImpl(Map<Currency, HeapOfIdenticalBanknotes> heaps){
        this.heaps = heaps;
    }

    /**
     * Геттер хипов банкнот.
     * @return Хипы банкнот.
     */
    public Map<Currency, HeapOfIdenticalBanknotes> getHeaps() {
        return heaps;
    }

    /**
     * Прокси-метод для переноса всех банкнот из внешних хипов (<code>heaps</code>)
     * в собственные хипы инстанса.
     * @param heaps внешний хипы.
     * @return Успешность операции
     */
    @Override
    public boolean add(BanknotesHeaps heaps) {
        return action(heaps, true);
    }

    /**
     * Прокси-метод для переноса всех банкнот из собственных хипов инстанса
     * во внешние хипы (<code>heaps</code>)ю
     * @param heaps внешние хипы
     * @return Успешность операции
     */
    @Override
    public boolean sub(BanknotesHeaps heaps) {
        return action(heaps, false);
    }

    /**
     * Метод, реализующий взаимодействие между хипоми банкнот:
     * внешними хипами (<code>heaps</code>) и собственными хипами
     * инстанса. В зависимости от флага-аргумента <code>isAdd</code>
     * банкноты перекладываютя в одну или в другую сторону.
     * @param heaps внешние хипы
     * @param isAdd true - произодится добавление банкнот в собственные хипы инстанса, false - изъятие.
     * @return Успешность операции.
     */
    private boolean action(BanknotesHeaps heaps, boolean isAdd){
        boolean success = true;
        Set<HeapOfIdenticalBanknotes> thisHeaps = new HashSet<>();

        for (Map.Entry<Currency, HeapOfIdenticalBanknotes> entry : heaps.getHeaps().entrySet()){
            Currency key = entry.getKey();
            HeapOfIdenticalBanknotes thisHeap = this.heaps.get(key);
            HeapOfIdenticalBanknotes otherHeap = entry.getValue();

            success &= (isAdd ? thisHeap.add(otherHeap) : thisHeap.sub(otherHeap));

            thisHeaps.add(thisHeap);
        }

        if (success){
            for (HeapOfIdenticalBanknotes thisHeap : thisHeaps) {
                thisHeap.confirmChange();
            }
        }

        return success;
    }

    /**
     * Метод, выводящий информацию о хипах в консом.
     */
    @Override
    public void display() {
        int sum = 0;
        for (Currency currency : Currency.values()) {
            var heap = heaps.get(currency);
            ((Displayable)heap).display();
            sum += heap.get();
            //<
//            HeapOfIdenticalBanknotes heap = heaps.get(currency);
//            heap.display();
//            sum += heap.get();
        }
        System.out.println("Total : " + sum);
    }
}
