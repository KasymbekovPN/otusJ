package ru.otus.kasymbekovPN.HW15.messageSystem.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class MessageSystemImpl_ implements MessageSystem_{

    private static final Logger logger = LoggerFactory.getLogger(MessageSystem_.class);
    private static final int MESSAGE_QUEUE_SIZE = 1_000;
    private static final int MSG_HANDLER_THREAD_LIMIT = 2;

    private final AtomicBoolean runFlag = new AtomicBoolean(true);

    private final Map<String, MsClient_> clientMap = new ConcurrentHashMap<>();
    private final BlockingQueue<Message__> messageQueue = new ArrayBlockingQueue<>(MESSAGE_QUEUE_SIZE);

    private final ExecutorService msgProcessor = Executors.newSingleThreadExecutor(runnable ->{
        Thread thread = new Thread(runnable);
        thread.setName("msg-processor-thread");
        return thread;
    });

    private final ExecutorService msgHandler = Executors.newFixedThreadPool(MSG_HANDLER_THREAD_LIMIT, new ThreadFactory() {
        private final AtomicInteger threadNameSeq = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("msg-handler-thread-" + threadNameSeq.incrementAndGet());
            return thread;
        }
    });

    public MessageSystemImpl_() {
        msgProcessor.submit(this::msgProcessor);
    }

    private void msgProcessor(){
            logger.info("msgProcessor started");
            while (runFlag.get()) {
                try {
                    Message__ msg = messageQueue.take();
                    if (msg == Message__.VOID_MESSAGE) {
                        logger.info("received the stop message");
                    } else {
                        MsClient_ clientTo = clientMap.get(msg.getTo());
                        if (clientTo == null) {
                            logger.warn("client not found");
                        } else {
                            msgHandler.submit(() -> handleMessage(clientTo, msg));
                        }
                    }
                } catch (InterruptedException ex) {
                    logger.error(ex.getMessage(), ex);
                    Thread.currentThread().interrupt();
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
            msgHandler.submit(this::messageHandlerShutdown);
            logger.info("msgProcessor finished");
    }

    private void messageHandlerShutdown() {
        msgHandler.shutdown();
        logger.info("msgHandler has been shut down");
    }

    private void handleMessage(MsClient_ msClient, Message__ msg) {
        try {
            msClient.handle(msg);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error("message:{}", msg);
        }
    }

    private void insertStopMessage() throws InterruptedException {
        boolean result = messageQueue.offer(Message__.VOID_MESSAGE);
        while (!result) {
            Thread.sleep(100);
            result = messageQueue.offer(Message__.VOID_MESSAGE);
        }
    }


    @Override
    public void addClient(MsClient_ msClient) {
        logger.info("new client:{}", msClient.getName());
        if (clientMap.containsKey(msClient.getName())) {
            throw new IllegalArgumentException("Error. client: " + msClient.getName() + " already exists");
        }
        clientMap.put(msClient.getName(), msClient);
    }

    @Override
    public void removeClient(String clientId) {
        MsClient_ removedClient = clientMap.remove(clientId);
        if (removedClient == null) {
            logger.warn("client not found: {}", clientId);
        } else {
            logger.info("removed client:{}", removedClient);
        }
    }

    @Override
    public boolean newMessage(Message__ msg) {
        if (runFlag.get()) {
            return messageQueue.offer(msg);
        } else {
            logger.warn("MS is being shutting down... rejected:{}", msg);
            return false;
        }
    }

    @Override
    public void dispose() throws InterruptedException {
        runFlag.set(false);
        insertStopMessage();
        msgProcessor.shutdown();
        msgHandler.awaitTermination(60, TimeUnit.SECONDS);
    }
}
