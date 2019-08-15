package ru.otus.kasymbekovPN.HW05.benchmark;

import java.util.ArrayList;
import java.util.List;

public class Benchmark implements BenchmarkMBean{
    private volatile int size = 0;
    private final int loopCounter;
    private final List<Integer> list;

    public Benchmark(int loopCounter){
        this.loopCounter = loopCounter;
        this.list = new ArrayList<>();
    }

    public void run() {
        try {
            for (int i = 0; i < loopCounter; i++){

                for (int idx = 0; idx < size; idx++)
                    list.add(idx);

                for (int idx = 0; idx < (size * 0.95); idx++)
                    list.remove(list.size()-1);

                Thread.sleep(50);
            }
        } catch (OutOfMemoryError | InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("new size : " + size);
        this.size = size;
    }
}
