package com.timenet.timenet.utils;

import android.util.Log;

/**
 * Created by 我是奋斗 on 2016/4/8.
 * 控制打印输出的类
 */
public class LogUtils {
    public static final boolean canLog=true;

    public static void Log(String message){
        if(canLog) {
            Log.e("TAG", message);
        }
    }
}
