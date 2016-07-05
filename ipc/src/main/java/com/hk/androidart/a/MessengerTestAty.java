package com.hk.androidart.a;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.hk.androidart.R;
import com.hk.androidart.cfg.MyConstants;
import com.hk.androidart.s.MessengerService;
import com.hvcker.doc.base.aty.HvckerActivity;

public class MessengerTestAty extends HvckerActivity {
    private Messenger mService;

    //处理服务端发过来的消息
    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_SERVER:
                    Log.d("hvcker", "receive msg from server:" + msg.getData().getString("replay"));
                    break;
            }
        }
    }

    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("hvcker", "client:" + service.toString());
            //用服务端传过来的IBinder创建一个能与服务端交互的Messenger
            mService = new Messenger(service);
            Message msg = Message.obtain(null, MyConstants.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "hello,this is client!");
            msg.setData(data);
            msg.replyTo = mGetReplyMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_messenger_test);
    }

    public void click(View view) {
        //绑定服务端Service
        bindService(new Intent(this, MessengerService.class), mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        unbindService(mConnection);
    }
}
