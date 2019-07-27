package ru.otus.kasymbekovPN.HW03.starter;

import ru.otus.kasymbekovPN.HW03.starter.annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Starter {

    final static private List<Class> ANNOTATION_LIST;
    static {
        List<Class> tmp = new ArrayList<>();
        tmp.add(StarterBeforeAll.class);
        tmp.add(StarterAfterAll.class);
        tmp.add(StarterBeforeEach.class);
        tmp.add(StarterAfterEach.class);
        tmp.add(StarterTest.class);

        ANNOTATION_LIST = tmp;
    }

    public static void run(String... args) {

        Optional<String> className = Starter.makeClassName(args);
        if (className.isPresent()){
            try{
                Class<?> aClass = Class.forName(className.get());
                SeparatedMethods separatedMethods = Starter.separateMethods(aClass.getDeclaredMethods());

                TestResults testResults = new TestResults();
                if (Starter.invokeOther(StarterBeforeAll.class, separatedMethods, null)){
                    testResults = Starter.invokeTest(separatedMethods, aClass);
                }
                Starter.invokeOther(StarterAfterAll.class, separatedMethods,null);

                testResults.print();

            } catch (ClassNotFoundException e){
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Argument must be only one");
        }
    }

    private static Optional<String> makeClassName(String... args){
        return args.length == 1
                ? Optional.of(args[0])
                : Optional.empty();
    }

    private static SeparatedMethods separateMethods(Method[] generalMethodList){
        SeparatedMethods sepMethods = new SeparatedMethods();
        for (Method method : generalMethodList) {
            for (Class annotation : ANNOTATION_LIST) {
                if (method.isAnnotationPresent(annotation)){
                    sepMethods.put(annotation, method);
                }
            }
        }

        return sepMethods;
    }

    private static boolean invokeOther(Class key, SeparatedMethods separatedMethods, Object instance){

        List<Method> methodList = separatedMethods.getMethodList(key);
        int size = methodList.size();
        int counter = 0;

        for (Method method : methodList) {
            try{
                method.invoke(instance);

                String msg = "";
                String offset = "";
                if (key == StarterBeforeAll.class) {
                    msg = method.getAnnotation(StarterBeforeAll.class).value();
                    offset = method.getAnnotation(StarterBeforeAll.class).offset();
                } else if (key == StarterAfterAll.class){
                    msg = method.getAnnotation(StarterAfterAll.class).value();
                    offset = method.getAnnotation(StarterAfterAll.class).offset();
                } else if (key == StarterBeforeEach.class){
                    msg = method.getAnnotation(StarterBeforeEach.class).value();
                    offset = method.getAnnotation(StarterBeforeEach.class).offset();
                } else if (key == StarterAfterEach.class){
                    msg = method.getAnnotation(StarterAfterEach.class).value();
                    offset = method.getAnnotation(StarterAfterEach.class).offset();
                }

                if (!msg.isEmpty()){
                    System.out.println(offset + msg);
                }

                counter++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return size == counter;
    }

    private static TestResults invokeTest(SeparatedMethods separatedMethods, Class aClass){

        TestResults res = new TestResults();

        List<Method> methodList = separatedMethods.getMethodList(StarterTest.class);
        for (Method method : methodList) {
            boolean success = true;
            String msg = method.getAnnotation(StarterTest.class).value();
            String key = msg + " (" + method.getName() + ")";
            try {
                Object instance = aClass.getConstructors()[0].newInstance();

                if (Starter.invokeOther(StarterBeforeEach.class, separatedMethods, instance)){
                    method.invoke(instance);
                    if (!msg.isEmpty()){
                        System.out.println(method.getAnnotation(StarterTest.class).offset() + msg);
                    }
                }

                Starter.invokeOther(StarterAfterEach.class, separatedMethods, instance);
            } catch (InstantiationException e) {
                success = false;
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                success = false;
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                success = false;
                e.printStackTrace();
            }

            res.put(key, success);
        }

        return res;
    }
}
