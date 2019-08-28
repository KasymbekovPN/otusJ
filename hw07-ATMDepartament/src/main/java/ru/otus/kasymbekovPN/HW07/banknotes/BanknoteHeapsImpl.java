package ru.otus.kasymbekovPN.HW07.banknotes;

import ru.otus.kasymbekovPN.HW07.utils.Displayable;
import ru.otus.kasymbekovPN.HW07.utils.NumberDiapason;

import java.util.*;

/**
 * Класс для хранения и изменения хипов (совокупностей банкнот
 * одного номинала) разных номиналов.
 */
public class BanknoteHeapsImpl implements BanknoteHeaps, Displayable {

    /**
     * Хипы банкнот.
     */
    private Map<Currency, BanknoteHeap> heaps;


    /**
     * Статический, фабричный метод. Перед созданием объекта
     * производит проверку инициализирующего контейнера (<code>init</code>).
     * Если в контейнере отсутсвует хип, соответствующий определенному
     * номиналу, то хип добавляется - за основу для, добавляемого хипа,
     * берётся болванка (<code>dummy</code>)
     * @param init инициализирующие хипы
     * @param dummy болванта.
     * @return Инстанс класса.
     */
    public static BanknoteHeapsImpl makeInstance(Map<Currency, BanknoteHeap> init,
                                                 BanknoteHeap dummy){
            for (Currency currency : Currency.values()) {
                if (init.containsKey(currency)) {
                    BanknoteHeap currentHeap = init.get(currency);
                    if (!currentHeap.getDenomination().equals(currency)){
                        init.put(currency, currentHeap.makeNewInstance(currency));
                    }
                } else {
                    init.put(currency, dummy.makeNewInstance(currency));
                }
            }

            return new BanknoteHeapsImpl(init);
    }

    /**
     * Статический, фабричный метод. Перед созданием объекта производит
     * настройку инициализирующего контейнера (<code>init</code>). Каждый хип
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
    public static BanknoteHeapsImpl makeInstance(int number_10, int number_50, int number_100,
                                                 int number_200, int number_500, int number_1000,
                                                 int number_2000, int number_5000,
                                                 BanknoteHeap dummy){
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
        Map<Currency, BanknoteHeap> init = new HashMap<>();

        for (int i = 0; i < allItems.length; i++){
            Currency currency = allItems[i];
            int number = NumberDiapason.putInRange(numberList.get(i));
            init.put(currency, dummy.makeNewInstance(currency, number));
        }

        return new BanknoteHeapsImpl(init);
    }

    /**
     * Статический, фабричный метод. Перед созданием инстанса производит
     * настройку инициализируеющего контейнера (<code>init</code>). Каждый
     * хип в контейнере создаётся с количеством банкнор равным <code>number</code>
     * Основой каждого хипа является <code>dummy</code>. Каждый хип настраивается
     * соответствующим номиналом.
     * @param number количество банкнот в каждом хипе.
     * @param dummy болванка.
     * @return Инстанс класса.
     */
    public static BanknoteHeapsImpl makeInstance(int number, BanknoteHeap dummy){
        final Currency[] allItems = Currency.values();
        Map<Currency, BanknoteHeap> init = new HashMap<>();

        number = NumberDiapason.putInRange(number);
        for (Currency currency : allItems) {
            init.put(currency, dummy.makeNewInstance(currency, number));
        }

        return new BanknoteHeapsImpl(init);
    }

    /**
     * Конструктор
     * @param heaps Инициализирующий контейнер.
     */
    private BanknoteHeapsImpl(Map<Currency, BanknoteHeap> heaps){
        this.heaps = heaps;
    }

    /**
     * Прокси-метод для переноса всех банкнот из внешних хипов (<code>outsideHeaps</code>)
     * в собственные хипы инстанса.
     * @param outsideHeaps внешний хипы.
     * @return Успешность операции
     */
    @Override
    public boolean add(BanknoteHeaps outsideHeaps) {
        return action(outsideHeaps, true);
    }

    /**
     * Прокси-метод для переноса всех банкнот из собственных хипов инстанса
     * во внешние хипы (<code>outsideHeaps</code>).
     * @param outsideHeaps внешние хипы
     * @return Успешность операции
     */
    @Override
    public boolean sub(BanknoteHeaps outsideHeaps) {
        return action(outsideHeaps, false);
    }

    /**
     * Метод, реализующий взаимодействие между двумя
     * хипами банкнот одного номинала. В зависимости от флага-аргумента
     * <code>isAdd</code> банкноты перекладываютя в одну или в другую сторону.
     * @param outsideHeap внешний хип банкнот
     * @param isAdd true - сложение, false - вычитание
     * @param currency номинал банкнот
     * @return Успешность операции
     */
    @Override
    public boolean innerAction(BanknoteHeap outsideHeap, boolean isAdd, Currency currency) {
        return isAdd
                ? outsideHeap.prepareAddAction(heaps.get(currency))
                : outsideHeap.prepareSubAction(heaps.get(currency));
    }

    /**
     * Возвращает количество банкнот одного номинала
     * @param currency Номинал
     * @return Количество банкнот.
     */
    @Override
    public int getNumberOfBanknotes(Currency currency) {
        return heaps.get(currency).getNumber();
    }

    /**
     * Метод, реализующий взаимодействие между хипами банкнот:
     * внешними хипами (<code>outsideHeaps</code>) и собственными хипами
     * инстанса. В зависимости от флага-аргумента <code>isAdd</code>
     * банкноты перекладываютя в одну или в другую сторону.
     * @param outsideHeaps внешние хипы
     * @param isAdd true - произодится добавление банкнот в собственные хипы инстанса, false - изъятие.
     * @return Успешность операции.
     */
    private boolean action(BanknoteHeaps outsideHeaps, boolean isAdd){
        boolean success = true;
        Set<BanknoteHeap> thisHeaps = new HashSet<>();

        for (Currency currency : Currency.values()) {
            BanknoteHeap thisHeap = this.heaps.get(currency);
            success &= outsideHeaps.innerAction(thisHeap, isAdd, currency);
            thisHeaps.add(thisHeap);
        }

        if (success){
            for (BanknoteHeap thisHeap : thisHeaps) {
                thisHeap.confirmChange();
            }
        }

        return success;
    }

    /**
     * Возвращает баланс
     * @return Баланс
     */
    @Override
    public int getBalance() {
        int balance = 0;
        for (Map.Entry<Currency, BanknoteHeap> entry: heaps.entrySet()) {
            balance += entry.getValue().getBalance();
        }

        return balance;
    }

    /**
     * Возвращает новый инстанс, идентичный старому.
     * @return Новый инстанс.
     */
    @Override
    public BanknoteHeaps makeNewInstance() {
        Map<Currency, BanknoteHeap> newHeaps = new HashMap<>();
        for (Map.Entry<Currency, BanknoteHeap> entry: heaps.entrySet()) {
            BanknoteHeap heap = entry.getValue();
            newHeaps.put(entry.getKey(), entry.getValue().makeNewInstance());
        }

        return new BanknoteHeapsImpl(newHeaps);
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
            sum += heap.getBalance();
        }
        System.out.println("Total : " + sum);
    }
}
