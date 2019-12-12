package introduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class MsNotifierImpl implements MsNotifier {

    private static Logger logger = LoggerFactory.getLogger(MsNotifierImpl.class);

    private final AtomicBoolean runFlag = new AtomicBoolean(true);
    private final ExecutorService processor = Executors.newSingleThreadExecutor(
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setName("registrarImpl-processor-thread");
                return thread;
            }
    );

    private final MsNotifierHandler msNotifierHandler;

    public MsNotifierImpl(MsNotifierHandler msNotifierHandler) {
        this.msNotifierHandler = msNotifierHandler;
        processor.submit(this::handleProcessor);
    }

    private void handleProcessor(){
        while(runFlag.get()){
            msNotifierHandler.handle();
            sleep();
        }

        processor.submit(this::shutdownProcessor);
    }

    private void shutdownProcessor(){
        processor.shutdown();
        logger.info("MsNotifierImpl : processor has been shut down.");
    }

    private static void sleep(){
        try{
            Thread.sleep(100);
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void stop() {
        runFlag.set(false);
    }
}
