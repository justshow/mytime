package com.timenet.timenet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SplashActivity extends Activity {
    private Switch tb_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        tb_button = (Switch)findViewById(R.id.tb_button);
        tb_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(SplashActivity.this,"点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
