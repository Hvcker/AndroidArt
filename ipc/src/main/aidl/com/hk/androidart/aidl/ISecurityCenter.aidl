// ISecurityCenter.aidl
package com.hk.androidart.aidl;

// Declare any non-default types here with import statements

interface ISecurityCenter {
    String encrypt(String content);
    String dectypt(String password);
}
