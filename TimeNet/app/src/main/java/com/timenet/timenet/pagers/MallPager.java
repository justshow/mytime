package com.timenet.timenet.pagers;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.timenet.timenet.basepager.BasePager;

/**
 * Created by 我是奋斗 on 2016/4/8.
 */
public class MallPager extends BasePager {
    public MallPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView textView=new TextView(mActivity);
        textView.setText("这是商城页");
        //ASPODJFAOP
        return textView;
    }

    @Override
    public void initData() {

        super.initData();
    }
}
