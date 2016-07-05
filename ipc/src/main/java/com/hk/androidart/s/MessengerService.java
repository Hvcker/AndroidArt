package com.hk.androidart.s;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hk.androidart.cfg.MyConstants;

/**
 * Created by Hvcker on 2016/6/13.
 */
public class MessengerService extends Service {

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_CLIENT:
                    Log.d("hvcker", "receive msg from Client:" + msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message replayMessage = Message.obtain(null,MyConstants.MSG_FROM_SERVER);
                    Bundle bundle = new Bundle();
                    bundle.putString("replay","ok,replay you later");
                    replayMessage.setData(bundle);
                    try {
                        client.send(replayMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("hvcker",mMessenger.getBinder().toString());
        return mMessenger.getBinder();
    }
}
