package com.emagicindia.mockinteractor.mainthread;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by mkodekar on 1/14/2016.
 */
public class MainThreadImpl implements MainThread {

    private Handler handler;

    public MainThreadImpl() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
