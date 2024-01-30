package src.readerwriterlock;

import java.util.logging.Level;
import java.util.logging.Logger;
public  class WriterTask implements Runnable {
    private final ReaderWriterLock lock;


    public WriterTask(ReaderWriterLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lockWrite();
            Thread.sleep(1000);
            lock.unlockWrite();
        } catch (InterruptedException e) {
            Logger.getLogger(WriterTask.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
