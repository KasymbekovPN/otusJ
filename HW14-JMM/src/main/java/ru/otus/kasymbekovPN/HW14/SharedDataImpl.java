package ru.otus.kasymbekovPN.HW14;

import java.util.ArrayList;
import java.util.List;

/**
 * Класса расшаренных данных
 */
public class SharedDataImpl implements SharedData {

    /**
     * Минимальный порог счета
     */
    private int minThreshold;

    /**
     * Максимальный порог счета
     */
    private int maxThreshold;

    /**
     * Флаг, указывающий направление счета:
     *  + true - вверх
     *  + false - вниз
     */
    private boolean up;

    /**
     * Флаг завершенности работы
     */
    private boolean done;

    /**
     * Счетчик пройденных циклов
     */
    private int cycleCounter;

    /**
     * Количество циклов нужное для завершения работы.
     */
    private int cycleNumber;

    /**
     * Счетчик, изменяемый в отрезке [minThreshold, maxThreshold]
     */
    private int counter;

    /**
     * Имена покотов, служат для организации порядка обращения
     * каждого потока к расшаренным данным.
     */
    private List<String> threadNames;

    /**
     * Индекс элемента threadNames который соответствует потоку,
     * которому разрешен доступ к расшаренным данным.
     *
     * Циклично инкрементируется в интервале [0, threadNames.size()).
     */
    private int threadNamesIndex;

    SharedDataImpl(int minThreshold, int maxThreshold, int cycleNumber) {
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
        this.counter = minThreshold;
        this.threadNames = new ArrayList<>();
        this.threadNamesIndex = -1;
        this.up = true;
        this.cycleNumber = 2 * cycleNumber;
        this.cycleCounter = 0;
    }

    /**
     * Возвращает имя ожидаемого потока
     * @return Имя потока
     */
    @Override
    public String getWaitedThreadName() {
        if (threadNamesIndex == -1){
            return null;
        } else {
            return threadNames.get(threadNamesIndex);
        }
    }

    /**
     * Функция воздействия потока на расшаренные данные
     *
     * + После обращения всех зарегистрированных потоков счетчик или
     *   инкрементируется, или декрементируется, в зависиммости up
     *
     * + Если счетчик прошёл полный рабочий цикл (от минимального
     *   порога до максимального и обратно), то происходит инкремент
     *   счетчика циклов.
     *
     * + Если количество пройденных циклов сравняется с cycleNumberб
     *   то работа считается выполненной.
     *
     *   @param threadName имя потока
     */
    @Override
    public void calculate(String threadName) {
        if (threadNames.contains(threadName)){
            if (threadNames.size() - 1 == threadNamesIndex){
                threadNamesIndex = 0;

                int threshold = up ? maxThreshold : minThreshold;
                if (counter == threshold){
                    counter = up ? --counter : ++counter;
                    up = !up;
                    if (++cycleCounter == cycleNumber){
                        done = true;
                    }
                } else {
                    counter = up ? ++counter : --counter;
                }
            } else {
                threadNamesIndex++;
            }
        }
    }

    /**
     * Добавляет имя потока
     * @param threadName имя потока
     */
    @Override
    public void addThreadName(String threadName) {
        threadNames.add(threadName);
        if (threadNamesIndex == -1){
            threadNamesIndex = 0;
        }
    }

    /**
     * Возвращает текущее значение счетчика
     * @return Значение счетчик
     */
    @Override
    public int getCounter() {
        return counter;
    }

    /**
     * Выполнена работа
     * @return Статус завершенности
     */
    @Override
    public boolean isDone() {
        return done;
    }
}
