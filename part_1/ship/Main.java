package dz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService thr = Executors.newFixedThreadPool(2);

        thr.execute(new Ship("First"));
        thr.execute(new Ship("Second"));
        thr.execute(new Ship("Third"));
        thr.shutdown();
    }
}
