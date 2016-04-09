package com.timenet.timenet.basepager;

import android.app.Activity;
import android.view.View;

/**
 * Created by 我是奋斗 on 2016/4/8.
 *
 * 软件架构的基类页面
 */
public abstract class BasePager  {
    //用于传递给子类，以便加载试图
    public final Activity mActivity;
    public View rootView;
    public boolean isInit;

    public BasePager(Activity activity) {
        this.mActivity = activity;
        //抽象方法，用于子类初始化视图,并返回给父类，父类利用多态，可以方便的添加到fragment中
        rootView = initView();
    }
    //具体如何加载视图，交给子类自己做
    public abstract View initView();

    //具体要初始化那些数据，由子类自己决定
    public void initData(){

    }
}
