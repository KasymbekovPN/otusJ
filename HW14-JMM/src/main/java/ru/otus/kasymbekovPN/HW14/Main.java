package ru.otus.kasymbekovPN.HW14;

/*
    java -jar HW14-JMM-jar-with-dependencies.jar 1 10 2 1 100
    агрументы:
        1) Нижний порог счета
        2) Верхний порог счета
        3) Количество потоков
        4) Количество циклов счета (от минимума до максимума и обратно)
        5) Таймаут в работе каждого потока
 */
public class Main {
    public static void main(String... args) throws InterruptedException {
        SawtoothCounter sawtoothCounter = SawtoothCounter.createInstance(args);
        sawtoothCounter.start();
    }
}
