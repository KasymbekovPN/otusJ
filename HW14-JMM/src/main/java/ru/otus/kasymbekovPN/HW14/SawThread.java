package ru.otus.kasymbekovPN.HW14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SawThread extends Thread {

    private final static Logger logger = LoggerFactory.getLogger(SawThread.class);

    private final SharedData sharedData;

//    public static SawThread create(SharedData sharedData){
//        SawThread sawThread = new SawThread();
//        synchronized (sharedData){
//            sawThread.sharedData.addThreadId(currentThread().getId());
//        }
//
//        return sawThread;
//    }

//    private SawThread() {
//    }

    //<
    SawThread(SharedData sharedData) {

        System.out.println("4");

        //<
        System.out.println("4.9 : " + getName());

        this.sharedData = sharedData;
        synchronized (this.sharedData){
//            this.sharedData.addThreadId(this.currentThread().getId());
            //<
            this.sharedData.addThreadName(this.getName());
        }
    }

    @Override
    public void run() {
        while (true){

            System.out.println("run : " + this.getName());

            synchronized (sharedData){
                System.out.println("6 : " + sharedData.waitedThread());

                if (sharedData.waitedThread().equals(this.getName())){
                    logger.info("[{}] : {}", this.getName(), sharedData.getCounter());
                    sharedData.doIt();
                }
                //<
//                if (sharedData.waitedThread() == this.currentThread().getId()){
//                    logger.info("[{}] : {}", this.currentThread().getId(), sharedData.getCounter());
//                    sharedData.doIt();
//                }

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
