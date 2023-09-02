package org.narel.job;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PercentJob {

    private final Executor executor = Executors.newSingleThreadExecutor();

    public void accrueInterest() {
        executor.execute(new Task());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {

        }
    }
}
