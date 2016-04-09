package com.timenet.timenet.pagers;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.timenet.timenet.R;
import com.timenet.timenet.adapter.ViewPagerAdapter;
import com.timenet.timenet.basepager.BasePager;
import com.timenet.timenet.domain.HomeMoviesBean;
import com.timenet.timenet.url.Url;
import com.timenet.timenet.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by 我是奋斗 on 2016/4/8.
 */
public class HomePager extends BasePager {
    private View homeView;
    private ListView listView;
    ViewPagerAdapter vpAdapter;
    /**
     * 主页listview头布局
     */
    private View homeHeader;
    /**
     * 主页顶部viewpager
     */
    private ViewPager headVp;

    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        homeView=View.inflate(mActivity, R.layout.home_pager,null);
        listView = (ListView) homeView.findViewById(R.id.lv_home);
        initHeadView();
        return homeView;
    }

    /**
     * 初始化主页头布局
     */
    private void initHeadView() {
        homeHeader=View.inflate(mActivity, R.layout.home_header, null);
        headVp = (ViewPager) homeHeader.findViewById(R.id.vp_home_header);
//        headVp.setAdapter();
    }

    @Override
    public void initData() {
       getData();
    }

    /**
     * 联网获取数据
     */
    private void getData() {
        RequestParams entity=new RequestParams(Url.HOME_MOVIES);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.Log("onSuccess");
                praseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.Log("onError");

            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.Log("onCancelled");

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 解析json数据
     * @param json
     */
    private void praseJson(String json) {
        Gson gson=new Gson();
        HomeMoviesBean homeMoviesBean = gson.fromJson(json, HomeMoviesBean.class);
        vpAdapter = new ViewPagerAdapter(homeMoviesBean, mActivity);
        headVp.setAdapter(vpAdapter);
    }
}
