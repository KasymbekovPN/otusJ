package ru.otus.kasymbekovPN.HW04;

import ru.otus.kasymbekovPN.HW04.accumulator.Accumulator;
import ru.otus.kasymbekovPN.HW04.accumulator.ICalc;

import java.util.Optional;

/*
    run from ../target:
        line for run with logs:
            java -jar hw04.jar -l
        line for run without log:
            java -jar hw04.jar
 */
public class Demo{

    private final static String LOG_ON_CMD = "-l";

    public static void main(String... args) {

        final boolean logMode = parseArguments(args);

        Optional<ICalc> aClass = ClassInvoker.iCreateClass(new Accumulator(), logMode);
        if (aClass.isPresent()){
            ICalc iCalc = aClass.get();
            System.out.println("Result after +123.456 : " + iCalc.add(123.456));
            System.out.println("Result after -45.321 : " + iCalc.sub(145.321));
            System.out.println("Result after *147.568 : " + iCalc.prod(147.568));
            System.out.println("Result after /(-78.25) : " + iCalc.div(-78.25));
            System.out.println("Result after +100 (without log) : " + iCalc.addWithoutLog(100.0));
        }
    }

    static private boolean parseArguments(String... args){
        return args.length >= 1 && args[0].equals(LOG_ON_CMD);
    }
}
