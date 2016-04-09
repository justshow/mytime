package com.timenet.timenet.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 我是奋斗 on 2016/4/9.
 * 全局数据，及一些初始化
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xutils
        x.Ext.init(this);
    }
}
