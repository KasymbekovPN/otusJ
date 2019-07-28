package ru.otus.kasymbekovPN.HW04;

import ru.otus.kasymbekovPN.HW04.accumulator.ICalc;
import ru.otus.kasymbekovPN.HW04.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ClassInvoker {

    private final static String LOG_ON_CMD = "-l";

    private static boolean logOn;

    static Optional<ICalc> createClass(Class aClass, String... args){

        setLogMode(args);

        Optional<ICalc> opt = Optional.empty();
        try {
            Object instance = aClass.getConstructors()[0].newInstance();

            List<Class<?>> interfaces = Arrays.asList(instance.getClass().getInterfaces());
            if (interfaces.contains(ICalc.class)){
                InvocationHandler hand = new ICalcInvocationHandler(instance);

                opt = Optional.of((ICalc) Proxy.newProxyInstance(ClassInvoker.class.getClassLoader(),
                        new Class<?>[]{ICalc.class}, hand));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return opt;
    }

    static class ICalcInvocationHandler implements InvocationHandler {
        private final ICalc iCalc;

        ICalcInvocationHandler(Object iCalc){
            this.iCalc = (ICalc) iCalc;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (logOn){
                Method iCalcMethod = iCalc.getClass().getMethod(method.getName(), double.class);
                if (iCalcMethod.isAnnotationPresent(Log.class)){
                    log(iCalcMethod.getName(), args);
                }
            }

            return method.invoke(iCalc, args);
        }
    }

    private static void setLogMode(String... args){
        logOn = false;
        if (args.length >= 1){
            logOn = args[0].equals(LOG_ON_CMD);
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