package src.readerwriterlock;




public class ReaderWriterLock {
    private int readerCount = 0;
    private int writerCount = 0;
    private int writeRequests = 0;



    public synchronized void lockRead() throws InterruptedException {
        while (writerCount > 0 || writeRequests > 0) {
            wait();
        }
        readerCount++;
        System.out.printf("Reader lock. Active Readers: {%d}\n", readerCount);
    }

    public synchronized void unlockRead() {
        readerCount--;
        System.out.printf("Reader unlock. Active Readers: {%d}\n",  readerCount);
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        while (readerCount > 0 || writerCount > 0) {
            wait();
        }
        writeRequests--;
        writerCount++;
        System.out.println("Writer acquired lock");
    }

    public synchronized void unlockWrite() {
        writerCount--;
        System.out.println( "Writer released lock");
        notifyAll();
    }


    public static void main(String[] args) {
        ReaderWriterLock lock = new ReaderWriterLock();

        Thread reader1 = new Thread(new ReaderTask(lock));
        Thread reader2 = new Thread(new ReaderTask(lock));
        Thread writer = new Thread(new WriterTask(lock));

        reader1.start();
        reader2.start();
        writer.start();
    }
}