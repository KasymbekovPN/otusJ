package ru.otus.kasymbekovPN.HW15.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.Message;
import ru.otus.kasymbekovPN.HW15.serverMessageSystem.MsClient;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@ComponentScan
@RequiredArgsConstructor
public class MessageSystemConfig {

    private static final int MESSAGE_QUEUE_SIZE = 1_000;
    private static final int MESSAGE_HANDLER_THREAD_LIMIT = 2;

    @Qualifier("msgSysImplRunFlag")
    @Bean
    public AtomicBoolean runFlag(){
        return new AtomicBoolean(true);
    }

    @Qualifier("msgSysImplClientMap")
    @Bean
    public Map<String, MsClient> clientMap(){
        return new ConcurrentHashMap<>();
    }

    @Qualifier("msgSysImplMessageQueue")
    @Bean
    public BlockingQueue<Message> messageQueue(){
        return new ArrayBlockingQueue<>(MESSAGE_QUEUE_SIZE);
    }

    @Qualifier("msgSysImplMessageProcessor")
    @Bean
    public ExecutorService messageProcessor(){
        return Executors.newSingleThreadExecutor(
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setName("message-processor-thread");
                return thread;
            }
        );
    }

    @Qualifier("msgSysImplMessageHandler")
    @Bean
    public ExecutorService messageHandler(){
        return Executors.newFixedThreadPool(
            MESSAGE_HANDLER_THREAD_LIMIT,
            new ThreadFactory() {
                private final AtomicInteger threadNameCounter = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    thread.setName("message-handler-thread-"+threadNameCounter.incrementAndGet());
                    return thread;
                }
            }
        );
    }
}
