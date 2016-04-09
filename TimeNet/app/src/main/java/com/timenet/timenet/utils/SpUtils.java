package com.timenet.timenet.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by 我是奋斗 on 2016/3/23.
 */
public class SpUtils {

    public static void saveData(Context context,String key,boolean value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }
    public static boolean getData(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
    public static void saveString(Context context,String key,String value) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                String fileName = MD5Encoder.encode(key);
                File file = new File(Environment.getExternalStorageDirectory() + "/beijingnews", fileName);
                File fileParent = file.getParentFile();
                if(!fileParent.exists()) {
                    file.mkdirs();
                }
                if(!file.exists()) {
                    file.createNewFile();
                }
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(value.getBytes());
                    fos.flush();
                    fos.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
            sp.edit().putString(key, value).commit();
        }


    }
    public static String getString(Context context,String key) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
               String fileName= MD5Encoder.encode(key);
                File file = new File(Environment.getExternalStorageDirectory() + "/beijingnews", fileName);
                if(file.exists()) {
                    FileInputStream fis = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int length = -1;
                    byte[] buffer = new byte[1024];
                    while ((length=fis.read(buffer))!=-1){
                        bos.write(buffer,0,length);
                    }
                    fis.close();
                    bos.close();
                    return bos.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {

            SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
            return sp.getString(key, "");
        }

        return null;
    }
}
