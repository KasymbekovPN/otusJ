package ru.otus.kasymbekovPN.HW04;

import ru.otus.kasymbekovPN.HW04.accumulator.Accumulator;
import ru.otus.kasymbekovPN.HW04.accumulator.AccumulatorWithoutInterface;
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
    public static void main(String... args) {

        Optional<ICalc> aClass = ClassInvoker.createClass(Accumulator.class, args);

        if (aClass.isPresent()){
            ICalc iCalc = aClass.get();
            System.out.println("Result after +123.456 : " + iCalc.add(123.456));
            System.out.println("Result after -45.321 : " + iCalc.sub(145.321));
            System.out.println("Result after *147.568 : " + iCalc.prod(147.568));
            System.out.println("Result after /(-78.25) : " + iCalc.div(-78.25));
            System.out.println("Result after +100 (without log) : " + iCalc.addWithoutLog(100.0));
        }

        Optional<ICalc> aClass2 = ClassInvoker.createClass(AccumulatorWithoutInterface.class, args);
        System.out.println(aClass2.isPresent());
    }
}
