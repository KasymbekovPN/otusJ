package ru.otus.kasymbekovPN.HW14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс, реализующий поток работы с расшареными данными.
 */
class SawThread extends Thread {

    private final static Logger logger = LoggerFactory.getLogger(SawThread.class);

    /**
     * Расшаренные данные
     */
    private final SharedData sharedData;

    /**
     * Работа выволнена
     */
    private boolean done;

    /**
     * Таймаут (мс)
     */
    private int timeout;

    /**
     * Конструктор
     * @param sharedData расшаренные данные
     * @param timeout таймаут
     */
    SawThread(SharedData sharedData, int timeout) {
        this.done = false;
        this.timeout = timeout;

        this.sharedData = sharedData;
        synchronized (this.sharedData){
            this.sharedData.addThreadName(this.getName());
        }
    }

    /**
     * Выполнение потока
     */
    @Override
    public void run() {
        while (!done){
            synchronized (sharedData){
                done = sharedData.isDone();
                if (!done){
                    if (sharedData.getWaitedThreadName().equals(this.getName())){
                        logger.info("[{}] : {}", this.getName(), sharedData.getCounter());
                        sharedData.calculate();
                    }
                }
            }

            if (timeout > 0){
                try {
                    sleep(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
