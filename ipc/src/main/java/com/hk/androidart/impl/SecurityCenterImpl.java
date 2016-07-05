package com.hk.androidart.impl;

import android.os.RemoteException;

import com.hk.androidart.aidl.ISecurityCenter;

/**
 * Created by Hvcker on 2016/6/21.
 */
public class SecurityCenterImpl extends ISecurityCenter.Stub {

    private static final char SECRET_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for(int i =0;i<chars.length;i++){
            chars[i]^=SECRET_CODE;
        }
        return new String(chars);
    }

    @Override
    public String dectypt(String password) throws RemoteException {
        return encrypt(password);
    }
}
