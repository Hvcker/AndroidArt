package com.hk.androidart.a;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.hk.androidart.R;
import com.hk.androidart.aidl.Book;
import com.hk.androidart.aidl.IBookManager;
import com.hk.androidart.aidl.IOnNewBookArrivedListener;
import com.hk.androidart.s.BookManagerService;
import com.hvcker.doc.base.aty.HvckerActivity;

import java.util.List;

public class AIDLA extends HvckerActivity {


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.d("hvcker", "receive new book:" + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteBookManeger = IBookManager.Stub.asInterface(service);
            try {
                List<Book> list = mRemoteBookManeger.getBookList();
                Log.d("hvcker", "query book list,list type:" + list.getClass().getCanonicalName());
                Log.d("hvcker", "query book list:" + list.toString());

                Book newBook = new Book(3, "Hvcker");
                mRemoteBookManeger.addBook(newBook);

                List<Book> newList = mRemoteBookManeger.getBookList();
                Log.d("hvcker", "query book list:" + newList.toString());

                mRemoteBookManeger.registerListener(mOnNewBookArrivedListener);

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
        setContentView(R.layout.activity_aidl);
    }


    public void click(View view) {
        bindService(new Intent(this, BookManagerService.class), mConnection, BIND_AUTO_CREATE);
    }



    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };

    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManager mRemoteBookManeger;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mRemoteBookManeger!=null&&mRemoteBookManeger.asBinder().isBinderAlive()){
            Log.i("hvcker","unregister listener:"+mOnNewBookArrivedListener);
            try {
                mRemoteBookManeger.unRegisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        unbindService(mConnection);
    }
}
