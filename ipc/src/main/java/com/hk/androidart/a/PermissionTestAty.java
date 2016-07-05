package com.hk.androidart.a;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.hk.androidart.R;
import com.hk.androidart.aidl.IAddPermission;
import com.hk.androidart.s.PermissionService;
import com.hk.androidart.s.PermissionService2;
import com.hvcker.doc.base.aty.HvckerActivity;

public class PermissionTestAty extends HvckerActivity {

    private boolean mIsJudgeOnBindConnBind = false;

    private boolean mIsJudgeOnTranscatBind = false;

    private ServiceConnection mJudgeOnBindConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("hvcker", service == null ? "null" : service.toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);
    }

    public void testPermission(View view) {
        Intent intent = new Intent(this, PermissionService.class);
        bindService(intent, mJudgeOnBindConn, BIND_AUTO_CREATE);
        mIsJudgeOnBindConnBind = true;
    }

    private IAddPermission mAddPermission;

    private ServiceConnection mJudgeOnTranscatConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAddPermission = IAddPermission.Stub.asInterface(service);
            try {
                mAddPermission.doSth();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void testPermission2(View view) {
        Intent intent = new Intent(this, PermissionService2.class);
        bindService(intent, mJudgeOnTranscatConn, BIND_AUTO_CREATE);
        mIsJudgeOnTranscatBind = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIsJudgeOnBindConnBind) {
            unbindService(mJudgeOnBindConn);
        }

        if (mIsJudgeOnTranscatBind) {
            unbindService(mJudgeOnTranscatConn);
        }
    }
}
