package ru.otus.kasymbekovPN.HW04;

import ru.otus.kasymbekovPN.HW04.accumulator.ICalc;
import ru.otus.kasymbekovPN.HW04.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ClassInvoker {

    static Optional<ICalc> iCreateClass(ICalc instance, boolean logMode){
        return instance == null
                ? Optional.empty()
                : Optional.of((ICalc)Proxy.newProxyInstance(ClassInvoker.class.getClassLoader(),
                new Class<?>[]{ICalc.class},
                new ICalcInvocationHandler(instance, logMode)));
    }

    static class ICalcInvocationHandler implements InvocationHandler {
        private final ICalc iCalc;
        private final List<String> logMethodNames;

        ICalcInvocationHandler(ICalc iCalc, boolean logMode){
            this.iCalc = iCalc;
            logMethodNames = new ArrayList<>();
            if (logMode){
                for (Method method : iCalc.getClass().getMethods()) {
                    if (method.isAnnotationPresent(Log.class)){
                        logMethodNames.add(method.getName());
                    }
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (logMethodNames.contains(method.getName())){
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
