package introduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

//< ?? rename -> 'Notifier'
@Service
//@RequiredArgsConstructor
public class RegistrarImpl implements Registrar {

    private static Logger logger = LoggerFactory.getLogger(RegistrarImpl.class);

    private final AtomicBoolean runFlag = new AtomicBoolean(true);
    private final ExecutorService processor = Executors.newSingleThreadExecutor(
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setName("registrarImpl-processor-thread");
                return thread;
            }
    );

    private final RegistrarHandler registrarHandler;

    public RegistrarImpl(RegistrarHandler registrarHandler) {

        //<
        logger.info("--- RegistrarImpl constructor ---");

        this.registrarHandler = registrarHandler;
        processor.submit(this::handleProcessor);

        //<
        logger.info("--- RegistrarImpl constructor END---");
    }

    private void handleProcessor(){

        while(runFlag.get()){
            registrarHandler.handle();
            sleep();
        }
    //<
//        //<
//        System.out.println(1);
//
//        while (runFlag.get()){
//            //<
//            System.out.println(2);
//
//            sleep();
//
//            if (runFlag.get()){
//                //<
//                System.out.println(3);
//
//                registrarHandler.handle();
//            }
//        }

        processor.submit(this::shutdownProcessor);
    }

    private void shutdownProcessor(){
        processor.shutdown();
        logger.info("processor has been shut down");
    }

    private static void sleep(){
        try{
            //< !!! in variable
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
