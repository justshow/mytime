package com.timenet.timenet.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/4/9.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
