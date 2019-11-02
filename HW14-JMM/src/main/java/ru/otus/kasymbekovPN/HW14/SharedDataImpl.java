package ru.otus.kasymbekovPN.HW14;

import java.util.ArrayList;
import java.util.List;

public class SharedDataImpl implements SharedData {

    private int minCounterValue;
    private int maxCounterValue;
    private boolean up;

    private int counter;
    private List<String> threadIds;
    private int threadIdsIndex;

    public SharedDataImpl(int minCounterValue, int maxCounterValue) {
        this.minCounterValue = minCounterValue;
        this.maxCounterValue = maxCounterValue;
        this.counter = minCounterValue;
        this.threadIds = new ArrayList<>();
        this.threadIdsIndex = -1;
        this.up = true;
    }

    @Override
    public String waitedThread() {
        if (threadIdsIndex == -1){
            return null;
        } else {
            return threadIds.get(threadIdsIndex);
        }
    }

    @Override
    public void doIt() {
        if (threadIds.size() - 1 == threadIdsIndex){
            threadIdsIndex = 0;

            if (up){
                if (counter == maxCounterValue){
                    counter--;
                    up = !up;
                } else {
                    counter++;
                }
            } else {
                if (counter == minCounterValue){
                    counter++;
                    up = !up;
                } else {
                    counter--;
                }
            }

        } else {
            threadIdsIndex++;
        }
    }

    @Override
    public void addThreadName(String threadName) {

        //<
        System.out.println("threadId : " + threadName);



        threadIds.add(threadName);

        //<
        System.out.println("threadIds : " + threadIds);

        if (threadIdsIndex == -1){
            threadIdsIndex = 0;
        }
    }

    @Override
    public int getCounter() {
        return counter;
    }
}
