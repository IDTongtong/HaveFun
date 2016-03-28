package com.lanou.tong.fun.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContent());
        initView();
        initData();
    }

    // 绑定布局
    protected abstract int setContent();
    // 加入数据
    protected abstract void initData();
    // 创建视图
    protected abstract void initView();
    // 绑定视图
    protected <T extends View>T bindView(int id) {
        return (T) findViewById(id);
    }
}
