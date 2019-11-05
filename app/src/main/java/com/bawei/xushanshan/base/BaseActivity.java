package com.bawei.xushanshan.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.xushanshan.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutid());
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layoutid();

}
