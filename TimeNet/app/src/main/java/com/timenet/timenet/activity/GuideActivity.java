package com.timenet.timenet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timenet.timenet.R;
import com.timenet.timenet.utils.LogUtils;
import com.timenet.timenet.utils.SpUtils;
import com.timenet.timenet.utils.Utils;

public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private ImageView redPoint;
    private float dx;
    private TextView pageNum;
    private ImageButton ib_to_main;
    private ImageButton ib_pass_guide;
    private int[] images=new int[]{R.drawable.lead_bg1,R.drawable.lead_bg2,R.drawable.lead_bg3
            ,R.drawable.lead_bg4};
    private int[] textImages=new int[]{R.drawable.lead_bg1_iv,R.drawable.lead_bg2_iv,
            R.drawable.lead_bg3_iv};
    private MyOnClickListener listener;
    private RelativeLayout guideView;
    private ImageView iv_bg;
    private ImageView iv_tv;
    private ImageView iv_guide_dec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        SpUtils.saveData(this,SplashActivity.EVER_GUIDE,true);

        initView();
        viewPager.setAdapter(new MyAdapter());
        addPoint();
        //监听viewpager的变化
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        //通过视图树监听获取点的间距
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        //imagebutton的点击监听
        listener=new MyOnClickListener();
        ib_to_main.setOnClickListener(listener);
        ib_pass_guide.setOnClickListener(listener);
    }
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_splash);
        linearLayout = (LinearLayout) findViewById(R.id.ll_points_splash);
        redPoint = (ImageView)findViewById(R.id.iv_splash_red_point);
        pageNum = (TextView) findViewById(R.id.tv_pager_num);
        ib_to_main = (ImageButton)findViewById(R.id.ib_to_main);
        ib_pass_guide = (ImageButton)findViewById(R.id.ib_pass_guide);
        iv_guide_dec = (ImageView) findViewById(R.id.iv_guide_dec);



    }
    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            LogUtils.Log("onclick");
            Intent intent=new Intent(GuideActivity.this,MainActivity.class);
            startActivity(intent);
            finish();



        }
    }
    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            redPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            dx = linearLayout.getChildAt(1).getX() - linearLayout.getChildAt(0).getX();
        }
    }

    /**
     * 添加底部点
     */
    private void addPoint() {
        for (int i = 0; i < images.length; i++) {
            ImageView imageView=new ImageView(this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(Utils.dip2px(this,10),
                    Utils.dip2px(this, 10));
            imageView.setImageResource(R.drawable.splash_gray_point);
            if(i!=0) {
                params.leftMargin = Utils.dip2px(this, 10);
            }
            imageView.setLayoutParams(params);
            linearLayout.addView(imageView);
        }
    }


    class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            guideView = (RelativeLayout) View.inflate(GuideActivity.this, R.layout.guide_viewpager_item, null);
            iv_bg = (ImageView) guideView.findViewById(R.id.iv_guide_bg);
            iv_tv = (ImageView) guideView.findViewById(R.id.iv_guide_tv);
            iv_bg.setBackgroundResource(images[position]);
            //如果是前三页，设置正常
            if(position<3) {
                iv_tv.setBackgroundResource(textImages[position]);
            }
            container.addView(guideView);
            return guideView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //初始margleft为0
            FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(Utils.dip2px(GuideActivity.this,10),
                    Utils.dip2px(GuideActivity.this,10));
            //根据viewpager的移动百分比计算红点的margleft
            float mvoex = positionOffset * dx + position * dx;
            params.leftMargin = (int) mvoex;
            redPoint.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            pageNum.setText((position + 1) + "/" + images.length);
            if(position==images.length-1) {
                iv_guide_dec.setVisibility(View.VISIBLE);
                ib_to_main.setVisibility(View.VISIBLE);
                ib_pass_guide.setVisibility(View.GONE);
            }else {
                iv_guide_dec.setVisibility(View.GONE);
                ib_to_main.setVisibility(View.GONE);
                ib_pass_guide.setVisibility(View.VISIBLE);
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
