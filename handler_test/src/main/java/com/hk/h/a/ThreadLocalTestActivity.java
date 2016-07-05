package com.hk.h.a;

import android.os.Bundle;
import android.util.Log;

import com.hk.h.R;
import com.hvcker.doc.base.aty.HvckerActivity;

public class ThreadLocalTestActivity extends HvckerActivity {
    private ThreadLocal<Boolean> mBooleanLocal = new ThreadLocal<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_local_test);
        mBooleanLocal.set(true);
        Log.d("hvcker", "[" + Thread.currentThread().getName() + "]mBooleanLocal = " + mBooleanLocal.get());
        new Thread("Thread#1") {
            @Override
            public void run() {
                mBooleanLocal.set(false);
                Log.d("hvcker", "[" + Thread.currentThread().getName() + "]mBooleanLocal = " + mBooleanLocal.get());
            }
        }.start();

        new Thread("Thread#2") {
            @Override
            public void run() {
                Log.d("hvcker", "[" + Thread.currentThread().getName() + "]mBooleanLocal = " + mBooleanLocal.get());
            }
        }.start();
    }
}
