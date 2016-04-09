package com.timenet.timenet.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.timenet.timenet.domain.HomeMoviesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我是奋斗 on 2016/4/9.
 */
public class HomeListAdapter extends BaseAdapter {
    private List<HomeMoviesBean.MoviesEntity> data=new ArrayList<>();
    private HomeMoviesBean bean;
    private Activity mActivity;
    public HomeListAdapter(HomeMoviesBean bean,Activity mActivity) {
        this.bean=bean;
        List<HomeMoviesBean.MoviesEntity> movies = bean.getMovies();
        data=movies;
        this.mActivity=mActivity;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
