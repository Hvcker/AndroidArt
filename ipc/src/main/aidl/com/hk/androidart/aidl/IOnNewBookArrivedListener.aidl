// IOnNewBookArrivedListener.aidl
package com.hk.androidart.aidl;

import com.hk.androidart.aidl.Book;

// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
