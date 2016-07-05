// IBookManager.aidl
package com.hk.androidart.aidl;

import com.hk.androidart.aidl.Book;

import com.hk.androidart.aidl.IOnNewBookArrivedListener;

// Declare any non-default types here with import statements

interface IBookManager {
   List<Book> getBookList();

   void addBook(in Book book);

   void registerListener(IOnNewBookArrivedListener listener);

   void unRegisterListener(IOnNewBookArrivedListener listener);
}
