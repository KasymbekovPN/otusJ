package ru.otus.kasymbekovPN.HW14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SawtoothCounter {

    private final static Logger logger = LoggerFactory.getLogger(SawtoothCounter.class);

    private final static int DEFAULT_COUNT_DIAPASON = 10;
    private final static int DEFAULT_THREAD_NUMBER = 2;
    private final static int MIN_THREAD_NUMBER = 1;
    private final static int MAX_THREAD_NUMBER = 12;

    private int minThreshold;
    private int maxThreshold;
    private int threadNumber;

    private SharedData sharedData;

    private int cycleCounter;

    SawtoothCounter(int minThreshold, int maxThreshold, int threadNumber) {

        if (threadNumber < MIN_THREAD_NUMBER || threadNumber > MAX_THREAD_NUMBER){
            this.threadNumber = DEFAULT_THREAD_NUMBER;
            logger.info(
                    "The attempt of setting thread number equals of {} - thread number was set equals of {}",
                    threadNumber,
                    DEFAULT_THREAD_NUMBER
            );
        } else {
            this.threadNumber = threadNumber;
        }

        this.minThreshold = minThreshold;

        if (this.minThreshold >= maxThreshold){
            this.maxThreshold = this.minThreshold + DEFAULT_COUNT_DIAPASON;
            logger.info(
                    "The attempt of setting value of maximum threshold ({}) less or equal than minimal threshold - maximum threshold was set equals of {}",
                    maxThreshold,
                    this.maxThreshold
            );
        } else {
            this.maxThreshold = maxThreshold;
        }

        this.sharedData = new SharedDataImpl(this.minThreshold, this.maxThreshold);
    }

    public int getCycleCounter() {
        return cycleCounter;
    }

    void start() throws InterruptedException {

        System.out.println("1");

        List<SawThread> sawThreads = new ArrayList<>();
        for (int i = 0; i < threadNumber; i++){

            System.out.println("2");

            sawThreads.add(new SawThread(sharedData));
        }

        System.out.println("1.5 : " + sawThreads);

        for (SawThread sawThread : sawThreads) {

            System.out.println("3 : " + sawThread.getName());

            sawThread.start();

            //<
//            sawThread.join();
        }

        for (SawThread sawThread : sawThreads) {
            sawThread.join();
        }
    }
}
