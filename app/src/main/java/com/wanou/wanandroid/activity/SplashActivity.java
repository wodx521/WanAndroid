package com.wanou.wanandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wanou.framelibrary.utils.CountDownUtils;
import com.wanou.wanandroid.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CountDownUtils.getTimer(3, null, "");
        CountDownUtils.setTimeFinishListener(new CountDownUtils.CountTimeFinishListener() {
            @Override
            public void onTimeFinishListener() {
                LoginActivity.startActivity(SplashActivity.this, null, LoginActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CountDownUtils.cancelTimer();
    }
}
