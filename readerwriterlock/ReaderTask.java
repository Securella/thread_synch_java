package src.readerwriterlock;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ReaderTask implements Runnable {


    private final ReaderWriterLock lock;

    public ReaderTask(ReaderWriterLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lockRead();
            Thread.sleep(1000);
            lock.unlockRead();
        } catch (InterruptedException e) {
            Logger.getLogger(ReaderTask.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}