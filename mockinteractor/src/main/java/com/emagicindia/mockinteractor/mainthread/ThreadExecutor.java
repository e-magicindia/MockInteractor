package com.emagicindia.mockinteractor.mainthread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by mkodekar on 1/14/2016.
 */
public class ThreadExecutor {

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();

    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadExecutor() {
        threadPoolExecutor =
                new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT,
                        WORK_QUEUE);
    }

    public void run(final Interact interactor) {
        if (interactor == null) {
            throw new IllegalArgumentException("Interactor must not be null");
        }
        threadPoolExecutor.submit(new Runnable() {
            @Override public void run() {
                interactor.run();
            }
        });
    }
}
