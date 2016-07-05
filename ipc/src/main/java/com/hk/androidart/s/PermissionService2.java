package com.hk.androidart.s;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import com.hk.androidart.aidl.IAddPermission;

public class PermissionService2 extends Service {
    public PermissionService2() {
    }

    private IBinder mBinder = new IAddPermission.Stub() {
        @Override
        public void doSth() throws RemoteException {
            Log.d("hvcker","do sth");
        }

        //用这种方法判断权限只是AIDL接口方法会失效，客户端还是会得到访问客户端的binder
        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int check = checkCallingOrSelfPermission("com.hk.androidart.permission.ACCESS_BOOK_SERVICE");
            if(check == PackageManager.PERMISSION_DENIED){
                return false;
            }

            String packageName = null;

            //拿到调用者的包名
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if(packages!=null&&packages.length>0){
                packageName = packages[0];
            }

            if(!packageName.startsWith("com.hk")){
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
