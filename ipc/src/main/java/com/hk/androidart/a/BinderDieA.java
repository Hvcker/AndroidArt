package com.hk.androidart.a;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.hk.androidart.R;
import com.hk.androidart.s.BinderDieService;
import com.hvcker.doc.base.aty.HvckerActivity;

public class BinderDieA extends HvckerActivity {

    private Messenger mServerMessenger;

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(mServerMessenger == null){
                return;
            }

            mServerMessenger.getBinder().unlinkToDeath(mDeathRecipient,0);
            mServerMessenger = null;

            //TODO:这里重新绑定远程Service
            Log.d("hvcker","binder die.tname:"+Thread.currentThread().getName());

        }
    };

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServerMessenger = new Messenger(service);
            try {
                service.linkToDeath(mDeathRecipient,0);
                Log.d("hvcker","server reply");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("hvcker","onServiceDisconnected.tname:"+Thread.currentThread().getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_die);
    }

    public void click(View view){
        Intent intent = new Intent(this, BinderDieService.class);
        bindService(intent,mConn,BIND_AUTO_CREATE);
    }
}
