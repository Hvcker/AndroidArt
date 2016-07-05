package com.hk.androidart.impl;

import android.os.RemoteException;

import com.hk.androidart.aidl.ICompute;

/**
 * Created by Hvcker on 2016/6/21.
 */
public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
