package ru.otus.kasymbekovPN.HW09.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация, служащая для указания ключевого поля
 * класса, взаимодействующего с БД.
 */
@Retention(RUNTIME)
public @interface Id {
}
