package ru.otus.kasymbekovPN.HW05;

/*

java -XX:+UseSerialGC -Xms128m -Xmx128m -jar hw05-GC-jar-with-dependencies.jar
java -XX:+UseParallelGC -Xms128m -Xmx128m -jar hw05-GC-jar-with-dependencies.jar
java -XX:+UseG1GC -Xms128m -Xmx128m -jar hw05-GC-jar-with-dependencies.jar

java -XX:+UseSerialGC -Xms512m -Xmx512m -jar hw05-GC-jar-with-dependencies.jar
java -XX:+UseParallelGC -Xms512m -Xmx512m -jar hw05-GC-jar-with-dependencies.jar
java -XX:+UseG1GC -Xms512m -Xmx512m -jar hw05-GC-jar-with-dependencies.jar

java -XX:+UseSerialGC -Xms1024m -Xmx1024m -jar hw05-GC-jar-with-dependencies.jar
java -XX:+UseParallelGC -Xms1024m -Xmx1024m -jar hw05-GC-jar-with-dependencies.jar
java -XX:+UseG1GC -Xms1024m -Xmx1024m -jar hw05-GC-jar-with-dependencies.jar

 */

import com.sun.management.GarbageCollectionNotificationInfo;
import ru.otus.kasymbekovPN.HW05.benchmark.Benchmark;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import java.io.*;
import java.lang.management.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

public class GCTestDrive {

    static private StringBuilder buffer;
    static private int minorCounter = 0;
    static private int majorCounter = 0;
    static private int minorTimeAcc = 0;
    static private int majorTimeAcc = 0;
    static private long minorEnd = 0;
    static private long majorStart = -1;
    static private long majorEnd = 0;
    static private double duration = 0;

    static public void main(String... args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException{

        buffer = new StringBuilder("startTime|gcAction|gcCause|duration\n---|---|---|---\n");

        System.out.println("Starting pid : " + ManagementFactory.getRuntimeMXBean().getName());
        switchOnMonitoring();
        long beginTime = System.currentTimeMillis();

        int size = 100_000;
        int loopCounter = 1000000;

        MBeanServer mbServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus.kasymbekovPN:type=Benchmark");
        Benchmark mbean = new Benchmark(loopCounter);
        mbServer.registerMBean(mbean, name);
        mbean.setSize(size);

        mbean.run();

        duration = ((double)System.currentTimeMillis() - beginTime) / 1000.0;
        System.out.println("time : " + new DecimalFormat("#0.00").format(duration));

        try(Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(genFileName()), StandardCharsets.UTF_8))){
            writer.write(handBuffer(buffer).toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static  private String genFileName(){
        StringBuilder sb = new StringBuilder("result");
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> jvmArgs = runtimeMXBean.getInputArguments();
        for (String arg : jvmArgs) {
            sb.append(arg);
        }
        String time = new Date().toString().replace(":", "-");

        return sb.toString().replace(":", "") + time + ".md";
    }

    private static StringBuilder handBuffer(StringBuilder buffer){

        final NumberFormat formatter = new DecimalFormat("#0.00");

        double minorNumberPerMin = 60_000.0 * minorCounter / minorEnd;
        long majorTime = majorEnd - majorStart;
        double majorNumberPerMin = majorTime <= 0
                ? 0
                : 60_000.0 * majorCounter / majorTime;

        final StringBuilder result = new StringBuilder("###");
        final List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        for (String inputArgument : inputArguments) {
            result.append(inputArgument).append(" ");
        }

        result.append("\n\n")
                .append(buffer)
                .append("\n\n")
                .append("+ Количество минорных сборок : ")
                .append(minorCounter)
                .append("\n")

                .append("+ Среднее время минорной сборки : ")
                .append(minorTimeAcc / minorCounter)
                .append(" (мс)\n")

                .append("+ Количество минорных сборок в минуту : ")
                .append(formatter.format(minorNumberPerMin))
                .append(" (1/мин)\n")

                .append("+ Количество мажорных сборок : ")
                .append(majorCounter)
                .append("\n")

                .append("+ Среднее время мажорной сборки : ")
                .append(majorTimeAcc / majorCounter)
                .append(" (мс)\n")

                .append("+ Количество мажорных сборок в минуту : ")
                .append(formatter.format(majorNumberPerMin))
                .append(" (1/мин)\n")

                .append("+ Время до OutMemoryError : ")
                .append(duration)
                .append(" (c)\n");

        return result;
    }

    static private void switchOnMonitoring(){
        var gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (var gcBean : gcBeans) {
            System.out.println("GC name : " + gcBean.getName());
            var emitter =  (NotificationEmitter) gcBean;
            NotificationListener listener = (notification, handback) -> {

                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)){
                    GarbageCollectionNotificationInfo info
                            = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause  = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    System.out.println("start:" + startTime
                            + " Name:" + gcName
                            + ", action:" + gcAction +
                            ", gcCause:" + gcCause + "(" + duration + " ms)");

                    buffer.append(startTime).append("|").append(gcAction).append("|")
                            .append(gcCause).append("|").append(duration).append("\n");

                    if (gcAction.equals("end of minor GC")){
                        if (duration != 0) {
                            minorCounter++;
                            minorTimeAcc += duration;
                            minorEnd = startTime;
                        }
                    } else if (gcAction.equals("end of major GC")){
                        if (duration != 0){
                            majorCounter++;
                            majorTimeAcc += duration;
                            if (majorStart == -1){
                                majorStart = startTime;
                            }
                            majorEnd = startTime;
                        }
                    }
                }
            } ;
            emitter.addNotificationListener(listener, null, null);
        }
    }
}