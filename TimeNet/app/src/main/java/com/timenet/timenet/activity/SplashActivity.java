package com.timenet.timenet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import com.timenet.timenet.R;
import com.timenet.timenet.utils.LogUtils;
import com.timenet.timenet.utils.SpUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SplashActivity extends Activity {
    public static final String EVER_GUIDE = "ever_guide";
    private ImageView iv_welcome;
    private Handler handler=new Handler();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        iv_welcome = (ImageView) findViewById(R.id.iv_welcome);
        //拷贝assets下文件到data/data/包名/xxx.db
        copyDB("china_city_name.db");


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startGuideActivity();
            }
        }, 2000);

    }

    private void startGuideActivity() {
        //判断是否进入过引导界面
        boolean everGuide = SpUtils.getData(this, EVER_GUIDE);
        if(everGuide) {
            intent = new Intent(this, MainActivity.class);
        }else {
            intent = new Intent(SplashActivity.this,GuideActivity.class);
        }
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
    /**
     * 把assets目录下的assert下的address.db拷贝到
     * 		/data/data/包名/files/
     * @param dbname
     */
    private void copyDB(String dbname) {
        File file = new File(getFilesDir(),dbname);
        if(file.exists()&&file.length()>0){
            LogUtils.Log(dbname + "数据库已经存在，不需要拷贝了...");
        }else{
            try {
                InputStream is = getAssets().open(dbname);
                FileOutputStream fos = new FileOutputStream(file);
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                is.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
