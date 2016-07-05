package com.hk.androidart.s;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.hk.androidart.impl.BinderPool;

public class BinderPoolService extends Service {
    public BinderPoolService() {
    }

    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }
}
