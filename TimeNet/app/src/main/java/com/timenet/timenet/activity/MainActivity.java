package com.timenet.timenet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.timenet.timenet.R;
import com.timenet.timenet.basepager.BasePager;
import com.timenet.timenet.pagers.FinderPager;
import com.timenet.timenet.pagers.HomePager;
import com.timenet.timenet.pagers.MallPager;
import com.timenet.timenet.pagers.MyCenterPager;
import com.timenet.timenet.pagers.TicketPager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private FrameLayout flMain;
    private RadioGroup rgMain;
    private RadioButton btnMainHome;
    private RadioButton btnMainTicket;
    private RadioButton btnMainMall;
    private RadioButton btnMainFinder;
    private RadioButton btnMainMycenter;
    private ArrayList<BasePager> pagerList;
    private int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.初始化试图
        findViews();
        initPager();


    }
    private void initPager() {
        pagerList = new ArrayList<>();
        HomePager homePager = new HomePager(this);
        TicketPager ticketPager=new TicketPager(this);
        MallPager mallPager=new MallPager(this);
        FinderPager finderPager = new FinderPager(this);
        MyCenterPager myCenterPager = new MyCenterPager(this);
        pagerList.add(homePager);
        pagerList.add(ticketPager);
        pagerList.add(mallPager);
        pagerList.add(finderPager);
        pagerList.add(myCenterPager);
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-04-08 19:39:15 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        flMain = (FrameLayout)findViewById( R.id.fl_main );
        rgMain = (RadioGroup)findViewById( R.id.rg_main );


        //给radiobutton设置监听
        rgMain.setOnCheckedChangeListener(new CheckedChangeListener());
        //默认第一项选中
        rgMain.check(R.id.btn_main_home);
    }
    class CheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.e("TAG", "checkchange()");
            //根据checkedid从list集合中取出不同的pager对象，添加到Fragment中
            switch (checkedId) {
                default:
                    position=0;
                    break;
                case R.id.btn_main_home :
                    position = 0;
                    break;
                case R.id.btn_main_ticket:
                    position = 1;
                    break;
                case R.id.btn_main_mall:
                    position = 2;
                    break;
                case R.id.btn_main_finder:
                    position = 3;
                    break;
                case R.id.btn_main_mycenter:
                    position = 4;
                    break;
            }
            setFragment();
        }
    }
    private void setFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_main,new Fragment(){
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                BasePager basePager=getBasePager();
                if(basePager!=null) {

                    return basePager.rootView;
                }
                return null;
            }
        });
        ft.commit();
    }
    private BasePager getBasePager() {

        BasePager basePager = pagerList.get(position);//多态
        if(basePager!=null&&!basePager.isInit) {
            basePager.isInit=true;
            basePager.initData();
        }
        return basePager;
    }


}

