package com.timenet.timenet.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 我是奋斗 on 2016/4/9.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<Object> data;
    private Activity mActivity;
    public ViewPagerAdapter(ArrayList<Object> data,Activity mActivity) {
        this.data=data;
        this.mActivity=mActivity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object==view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
