package ru.otus.kasymbekovPN.HW14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий пилообразный счетчик.
 *
 * Цикл работы - счет от минимута до максимума и обратно
 */
class SawtoothCounter {

    private final static Logger logger = LoggerFactory.getLogger(SawtoothCounter.class);

    /**
     * Дефолтное значение минимального порога
     */
    private final static int DEFAULT_MIN_THRESHOLD = 1;

    /**
     * Дефолтная разница между минимальным порогом и максимальным
     */
    private final static int DEFAULT_COUNT_DIAPASON = 9;

    /**
     * Дефолтное количество потоков
     */
    private final static int DEFAULT_THREAD_NUMBER = 2;

    /**
     * Минимальное количество потоков
     */
    private final static int MIN_THREAD_NUMBER = 1;

    /**
     * Максимальное количество потоков
     */
    private final static int MAX_THREAD_NUMBER = 12;

    /**
     * Дефотное количество циклов работы
     */
    private final static int DEFAULT_CYCLE_NUMBER = 1;

    /**
     * Минимальное количество циклов работы счетчика.
     */
    private final static int MIN_CYCLE_NUMBER = 1;

    /**
     * Максимальное количество циклов работы счетчика
     */
    private final static int MAX_CYCLE_NUMBER = 10;

    /**
     * Дефолтный таймаут для потоков
     */
    private final static int DEFAULT_TIMEOUT = 100;

    /**
     * Минимальный таймаут для потоков
     */
    private final static int MIN_TIMEOUT = 0;

    /**
     * Максимальный таймаут для потоков
     */
    private final static int MAX_TIMEOUT = 1_000;

    /**
     * Количество, учавствующих потоков
     */
    private int threadNumber;

    /**
     * Таймаут для потоков
     */
    private int timeout;

    /**
     * Расшаренные данные
     */
    private SharedData sharedData;

    /**
     * Создает инстанс класс SawtoothCounter
     * @param args параметры для создания
     * @return инстанс
     */
    static SawtoothCounter createInstance(String... args){

        List<Integer> params = new ArrayList<>();
        try {
            for (String arg : args) {
                params.add(Integer.valueOf(arg));
            }
        } catch (NumberFormatException ex){
            params = null;
        }

        if (params == null || params.size() != 5){
            return createInstance();
        } else {
            return createInstance(
                    params.get(0),
                    params.get(1),
                    params.get(2),
                    params.get(3),
                    params.get(4)
            );
        }
    }

    /**
     * Создает инстанс класс SawtoothCounter с дефолтными значениями
     * @return инстанс
     */
    static SawtoothCounter createInstance(){
        return new SawtoothCounter(
                DEFAULT_MIN_THRESHOLD,
                DEFAULT_MIN_THRESHOLD + DEFAULT_COUNT_DIAPASON,
                DEFAULT_THREAD_NUMBER,
                DEFAULT_CYCLE_NUMBER,
                DEFAULT_TIMEOUT
        );
    }

    /**
     * Создает инстанс класса SawtoothCounter, с заданными параметрами
     * @param minThreshold минимальный порог счета
     * @param maxThreshold максимальный порог счета
     * @param threadNumber количество потоков
     * @param cycleNumber количество циклов работы
     * @param timeout таймаут для потоков
     * @return инстанс
     */
    static SawtoothCounter createInstance(
            int minThreshold, int maxThreshold, int threadNumber,
            int cycleNumber, int timeout){

        if (threadNumber < MIN_THREAD_NUMBER || threadNumber > MAX_THREAD_NUMBER){
            threadNumber = DEFAULT_THREAD_NUMBER;
            logger.info(
                    "The attempt of setting thread number equals of {} - thread number was set equals of {}",
                    threadNumber,
                    DEFAULT_THREAD_NUMBER
            );
        }

        if (minThreshold >= maxThreshold){
            maxThreshold = minThreshold + DEFAULT_COUNT_DIAPASON;
            logger.info(
                    "The attempt of setting value of maximum threshold less or equal than minimal threshold - maximum threshold was set equals of {}",
                    maxThreshold
            );
        }

        if (cycleNumber < MIN_CYCLE_NUMBER || cycleNumber > MAX_CYCLE_NUMBER){
            logger.info(
                    "The attempt of setting of cycle number which out of range ({}). Was set {}",
                    cycleNumber,
                    DEFAULT_CYCLE_NUMBER
            );
            cycleNumber = DEFAULT_CYCLE_NUMBER;
        }

        if (timeout < MIN_TIMEOUT || timeout > MAX_TIMEOUT){
            logger.info(
                    "The attempt of setting of timeout which out of range ({}). Was set {}",
                    timeout,
                    DEFAULT_TIMEOUT
            );
            timeout = DEFAULT_TIMEOUT;
        }

        return new SawtoothCounter(minThreshold, maxThreshold, threadNumber, cycleNumber, timeout);
    }

    /**
     * Конструктор
     * @param minThreshold минимальный порог счета
     * @param maxThreshold максимальный порог счета
     * @param threadNumber количество потоков
     * @param cycleNumber количество циклов работы
     * @param timeout таймаут для потоков
     */
    private SawtoothCounter(int minThreshold, int maxThreshold, int threadNumber, int cycleNumber, int timeout){
        this.threadNumber = threadNumber;
        this.timeout = timeout;
        this.sharedData = new SharedDataImpl(minThreshold, maxThreshold, cycleNumber);
    }

    /**
     * Запускает потоки.
     */
    void start() throws InterruptedException {
        List<SawThread> sawThreads = new ArrayList<>();
        for (int i = 0; i < threadNumber; i++){
            sawThreads.add(new SawThread(sharedData, timeout));
        }

        for (SawThread sawThread : sawThreads) {
            sawThread.start();
        }

        for (SawThread sawThread : sawThreads) {
            sawThread.join();
        }
    }
}
