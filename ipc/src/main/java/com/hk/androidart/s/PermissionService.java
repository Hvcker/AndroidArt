package com.hk.androidart.s;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;

public class PermissionService extends Service {
    public PermissionService() {
    }

    private Messenger mMessenger = new Messenger(new Handler());

    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.hk.androidart.permission.ACCESS_BOOK_SERVICE");
        if (check == PackageManager.PERMISSION_DENIED) {
            Log.d("hvcker", "null");
            return null;
        }

        Log.d("hvcker", "not null");

        return mMessenger.getBinder();
    }

}
