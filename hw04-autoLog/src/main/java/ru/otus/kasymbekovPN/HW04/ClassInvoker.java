package ru.otus.kasymbekovPN.HW04;

import ru.otus.kasymbekovPN.HW04.accumulator.ICalc;
import ru.otus.kasymbekovPN.HW04.accumulator.MethodSign;
import ru.otus.kasymbekovPN.HW04.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

class ClassInvoker {

    static ICalc createClass(ICalc instance, boolean logMode){
        return logMode
                ? (ICalc)Proxy.newProxyInstance(ClassInvoker.class.getClassLoader(),
                    new Class<?>[]{ICalc.class},
                    new ICalcInvocationHandler(instance))
                : instance;
    }

    static class ICalcInvocationHandler implements InvocationHandler {
        private final ICalc iCalc;
        private final Set<MethodSign> logMethods;

        ICalcInvocationHandler(ICalc iCalc){
            this.iCalc = iCalc;

            logMethods = new TreeSet<>();
            for (Method method : iCalc.getClass().getMethods()) {
                if (method.isAnnotationPresent(Log.class)){
                    logMethods.add(new MethodSign(method));
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (logMethods.contains(new MethodSign(method))){
                log(method.getName(), args);
            }
            return method.invoke(iCalc, args);
        }
    }

    private static void log(String methodName, Object[] args){
        StringBuilder argumentLine = new StringBuilder();
        for (Object arg : args) {
            argumentLine.append(arg.toString());
        }
        System.out.println("Executed method : " + methodName + "; args : " + argumentLine);
    }
}
