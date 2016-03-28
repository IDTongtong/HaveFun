package com.lanou.tong.fun.activity;

import android.content.Intent;
import android.os.Handler;


import com.lanou.tong.fun.R;
import com.lanou.tong.fun.base.BaseActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zt on 16/3/3.
 */
public class WelcomeActivity extends BaseActivity {
    // 定义全局变量, 时长2s
    private final long SPLASH_LENGTH = 2000;
    Handler handler = new Handler();

    @Override
    protected int setContent() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_LENGTH);   // 2s后 跳转到主界面
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
