package com.timenet.timenet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.timenet.timenet.R;

public class SplashActivity extends Activity {
    private ImageView iv_welcome;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv_welcome = (ImageView) findViewById(R.id.iv_welcome);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startGuideActivity();
            }
        }, 2000);
    }

    private void startGuideActivity() {
        Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startGuideActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
