package src.philosophers_synchron;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher implements Runnable {
    private int myId;
    private Table myTable;
    private int totalPhilosophers;
    public Philosopher(int id, Table table, int totalPhilosophers) {
        myId = id;
        myTable = table;
        this.totalPhilosophers = totalPhilosophers;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("Philosopher " + myId + " thinks. Iteration " + i);
                Thread.sleep((int) (Math.random() * 100));

                if (myId != totalPhilosophers - 1) { // for all philosopher except last
                    myTable.getLeftChopstick(myId);
                    System.out.println("Philosopher " + myId + " pick up left");
                    Thread.sleep((int) (Math.random() * 10));

                    myTable.getRightChopstick(myId);
                    System.out.println("Philosopher " + myId + " pick up right");
                } else { // for last philosopher
                    myTable.getRightChopstick(myId);
                    System.out.println("Philosopher " + myId + " pick up right");
                    Thread.sleep((int) (Math.random() * 10));

                    myTable.getLeftChopstick(myId);
                    System.out.println("Philosopher " + myId + " pick up left");
                }

                System.out.println("Philosopher " + myId + " eats. Iteration " + i);
                Thread.sleep((int) (Math.random() * 100));

                //
                if (myId != totalPhilosophers-1) {
                    myTable.releaseLeftChopstick(myId);
                    System.out.println("Philosopher " + myId + " drop left");
                    Thread.sleep((int) (Math.random() * 10));

                    myTable.releaseRightChopstick(myId);
                    System.out.println("Philosopher " + myId + " drop right");
                } else {
                    myTable.releaseRightChopstick(myId);
                    System.out.println("Philosopher " + myId + " drop right");
                    Thread.sleep((int) (Math.random() * 10));

                    myTable.releaseLeftChopstick(myId);
                    System.out.println("Philosopher " + myId + " drop left");
                }
                Thread.sleep((int) (Math.random() * 10));

            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
