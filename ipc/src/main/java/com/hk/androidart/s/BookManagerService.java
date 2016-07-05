package com.hk.androidart.s;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.hk.androidart.aidl.Book;
import com.hk.androidart.aidl.IBookManager;
import com.hk.androidart.aidl.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {

    private static final String TAG = "hvcker";

    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if(!mListenerList.contains(listener)){
//                mListenerList.add(listener);
//            }else{
//                Log.d(TAG,"already exists.");
//            }
//
//            Log.d(TAG,"registerListener,size:"+mListenerList.size());
            mListenerList.register(listener);
            Log.d(TAG,"unregisterListener,current size:"+mListenerList.beginBroadcast());
            mListenerList.finishBroadcast();
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if(mListenerList.contains(listener)){
//                mListenerList.remove(listener);
//                Log.d(TAG,"unregister listener succeed.");
//            }else{
//                Log.d(TAG,"not found,can not unregister.");
//            }
//            Log.d(TAG,"unregisterListener,current size:"+mListenerList.size());
            mListenerList.unregister(listener);
            Log.d(TAG,"unregisterListener,current size:"+mListenerList.beginBroadcast());
            mListenerList.finishBroadcast();
        }


    };

    public BookManagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "iOS"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (!mIsServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + (++i);
                onNewBookArrived(new Book(bookId, "new book#" + bookId));
            }
        }
    }

    private void onNewBookArrived(Book book) {
        mBookList.add(book);
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener l = mListenerList.getBroadcastItem(i);
            if(l!=null){
                try {
                    l.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();
    }
}
