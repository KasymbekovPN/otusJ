package ru.otus.kasymbekovPN.HW03;

import ru.otus.kasymbekovPN.HW03.starterAnnotations.*;

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

    public static void main(String... args) {

        Optional<String> className = Starter.classNameBuilder(args);
        if (className.isPresent()){
            try{
                Class<?> aClass = Class.forName(className.get());
                Map<Class, List<Method>> sepMethods = Starter.methodSeparator(aClass.getDeclaredMethods());
                Starter.invokeWrapper(StarterBeforeAll.class, sepMethods, null);
                Map<String, Boolean> testResult = Starter.testWrapper(sepMethods, aClass.getConstructors()[0].newInstance());
                Starter.invokeWrapper(StarterAfterAll.class, sepMethods,null);

                Starter.testResultChecking(testResult);

            } catch (ClassNotFoundException e){
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Argument must be only one");
        }
    }

    private static Optional<String> classNameBuilder(String... args){
        return args.length == 1
                ? Optional.of(args[0])
                : Optional.empty();
    }

    private static Map<Class, List<Method>> methodSeparator(Method[] generalMethodList){
        Map<Class, List<Method>> sepMethods = new HashMap<>();

        for (Method method : generalMethodList) {
            for (Class annotation : ANNOTATION_LIST) {
                if (!sepMethods.containsKey(annotation)) {
                    sepMethods.put(annotation, new ArrayList<>());
                }
                if (method.isAnnotationPresent(annotation)) {

                    sepMethods.get(annotation).add(method);
                }
            }
        }

        return sepMethods;
    }

    private static void invokeWrapper(Class key, Map<Class, List<Method>> sepMethods, Object instance){
        if (sepMethods.containsKey(key)){
            for (Method method : sepMethods.get(key)) {
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

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Map<String, Boolean> testWrapper(Map<Class, List<Method>> sepMethods, Object instance){

        Map<String, Boolean> res = new HashMap<>();

        if (sepMethods.containsKey(StarterTest.class)){
            for (Method method : sepMethods.get(StarterTest.class)) {
                boolean success = true;
                String msg = method.getAnnotation(StarterTest.class).value();

                Starter.invokeWrapper(StarterBeforeEach.class, sepMethods, /*false,*/ instance );

                try {
                    method.invoke(instance);
                    if (!msg.isEmpty()){
                        System.out.println(method.getAnnotation(StarterTest.class).offset() + msg);
                    }

                } catch (IllegalAccessException e) {
                    success = false;
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    success = false;
                    e.printStackTrace();
                }

                String key = msg + " (" + method.getName() + ")";
                res.put(key, success);

                Starter.invokeWrapper(StarterAfterEach.class, sepMethods, instance);
            }
        }

        return res;
    }

    private static void testResultChecking(Map<String, Boolean> testResult) {
        int count = 0;

        System.out.println("\nResults");
        for (Map.Entry<String, Boolean> entry : testResult.entrySet()) {
            System.out.println(entry.getValue() + "\t : " + entry.getKey());
            if (entry.getValue()){
                count++;
            }
        }
        System.out.println("\nDone : " + count + "/" + testResult.size());
    }
}
