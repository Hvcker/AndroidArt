package com.hk.androidart.s;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;

/**
 * Created by Hvcker on 2016/6/13.
 */
public class BinderDieService extends Service {

    private Messenger mMessenger;

    @Override
    public void onCreate() {
        super.onCreate();
        mMessenger = new Messenger(new Handler());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
