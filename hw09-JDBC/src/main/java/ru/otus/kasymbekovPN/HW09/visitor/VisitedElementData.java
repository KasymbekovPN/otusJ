package ru.otus.kasymbekovPN.HW09.visitor;

/**
 * Интерфейс для реализации класса, предоставляющего
 * доступ к данным посещаемого элемента.
 */
public interface VisitedElementData {

    /**
     * Геттер имени
     * @return имя
     */
    String getName();

    /**
     * Геттер инстанса
     * @return инстанса
     */
    Object getInstance();

    /**
     * Проверяет есть ли у посещаемого элемента требуемая аннотация
     * @param annotation аннотация
     * @return результат проверки
     */
    boolean isAnnotationPresent(Class annotation);
}
