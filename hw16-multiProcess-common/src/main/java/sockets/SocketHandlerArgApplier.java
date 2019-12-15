package sockets;

/**
 * Интерфейс, служащий для реализации класса, применяющего аргументы комендной строки к {@link SocketHandler}
 */
public interface SocketHandlerArgApplier {
    void apply(String... args) throws Exception;
}
