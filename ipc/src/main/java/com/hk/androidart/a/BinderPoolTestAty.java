package com.hk.androidart.a;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.hk.androidart.R;
import com.hk.androidart.aidl.ICompute;
import com.hk.androidart.aidl.ISecurityCenter;
import com.hk.androidart.impl.BinderPool;
import com.hk.androidart.impl.ComputeImpl;
import com.hk.androidart.impl.SecurityCenterImpl;
import com.hvcker.doc.base.aty.HvckerActivity;

public class BinderPoolTestAty extends HvckerActivity {

    private ISecurityCenter mSecurityCenter;

    private ICompute mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool_test);
    }

    public void doWork(View view){
        BinderPool binderPool = BinderPool.getInstance(this);
        IBinder securityBinder = binderPool
                .queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);
        Log.d("hvcker","visit ISecurityCenter");
        String msg = "helloworld-安卓";
        Log.d("hvcker","content:"+msg);
        try {
            String password = mSecurityCenter.encrypt(msg);
            Log.d("hvcker","encrypt:"+password);
            Log.d("hvcker","decrypt:"+mSecurityCenter.dectypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Log.d("hvcker","visit ICompute");
        IBinder computebinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mCompute = ComputeImpl.asInterface(computebinder);
        try {
            Log.d("hvcker","4+6="+mCompute.add(4,6));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
