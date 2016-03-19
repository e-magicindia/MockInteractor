package com.e_magicindia.mocker;


import android.content.Context;

import com.emagicindia.mockinteractor.BackGroundActionCallback;
import com.emagicindia.mockinteractor.mainthread.Interact;
import com.emagicindia.mockinteractor.mainthread.MainThreadImpl;

/**
 * Created by mkodekar on 1/14/2016.
 */

public class BackGroundAction implements Interact {

    private BackGroundActionCallback callback;
    private MainThreadImpl mainThread;
    ParserHtml parserHtml;
    Context mContext;

    public BackGroundAction(BackGroundActionCallback callback, Context context) {
        this.callback = callback;
        this.mContext = context;
        this.mainThread = new MainThreadImpl();
        parserHtml = new ParserHtml(mContext);
    }

    @Override public void run() {
        parserHtml.parser();
        mockLoadingTime();
        notifyActionComplete();
    }

    public void mockLoadingTime() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            //Empty
        }
    }

    public void notifyActionComplete() {
        mainThread.post(new Runnable() {
            @Override public void run() {
                callback.onMockActionComplete();
            }
        });
    }
}
