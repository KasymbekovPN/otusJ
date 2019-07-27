package ru.otus.kasymbekovPN.HW03.starter.annotations;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface StarterBeforeAll {
    String value() default "";
    String offset() default "";
}
