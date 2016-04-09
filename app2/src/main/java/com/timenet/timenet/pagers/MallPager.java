package com.timenet.timenet.pagers;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timenet.timenet.R;
import com.timenet.timenet.basepager.BasePager;
import com.timenet.timenet.bean.ScrollImg;
import com.timenet.timenet.bean.Titles;
import com.timenet.timenet.utilsDao.Url;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我是奋斗 on 2016/4/8.
 */
public class MallPager extends BasePager {

@ViewInject(R.id.viewpager)
private ViewPager viewpager;
@ViewInject(R.id.gridview)
private GridView gridview;
@ViewInject(R.id.listview)
private ListView listview;
    private View view;

    private List<Titles> titles;

    public MallPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        view = View.inflate(mActivity, R.layout.buy_item, null);
        x.view().inject(view);
        //
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        titles=new ArrayList<>();
        titles.add(new Titles(R.drawable.act_my_home_card,"玩具"));
        titles.add(new Titles(R.drawable.act_my_home_cart,"数码"));
        titles.add(new Titles(R.drawable.act_my_home_goods,"服饰"));
        titles.add(new Titles(R.drawable.act_my_home_tickets,"家具"));
        titles.add(new Titles(R.drawable.act_my_home_tickets,"影时光"));
        titles.add(new Titles(R.drawable.act_my_home_card, "新品"));
        titles.add(new Titles(R.drawable.act_my_home_cart, "预售"));
        titles.add(new Titles(R.drawable.act_my_home_goods, "特惠"));
         //设置gridView
        gridview.setAdapter(new MyGridViewAdapter());
        Log.e("TAG","111111111111");
        //Xutils联网请求
        progressJson();


    }

    private void progressJson() {
        RequestParams params=new RequestParams(Url.TIME_URI);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析json
                getJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getJson(String json) {
        ScrollImg scrollImg=parsedJson(json);
    }
    /**
     * 解析json数据
     * @param json
     * @return
     */
    private ScrollImg parsedJson(String json) {

        return  new Gson().fromJson(json,ScrollImg.class);
    }

    private class MyGridViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public Object getItem(int position) {
            return titles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("TAG","22222222222222");
            convertView=View.inflate(mActivity, R.layout.gridview, null);
            Titles title=titles.get(position);
            ImageView imageview= (ImageView) convertView.findViewById(R.id.imageview);
            TextView texts= (TextView) convertView.findViewById(R.id.texts);
            imageview.setImageResource(title.getImage());
            texts.setText(title.getTitle());
            return  convertView;
        }
    }

    private class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }
    }
}
