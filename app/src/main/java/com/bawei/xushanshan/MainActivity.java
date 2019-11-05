package com.bawei.xushanshan;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bawei.xushanshan.adapter.ViewAdapter;
import com.bawei.xushanshan.fragment.SYFragment;
import com.bawei.xushanshan.fragment.WdFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rg)
     RadioGroup rg;
    private ArrayList<Fragment> fragments;
    private ViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //EventBus
        EventBus.getDefault().register(this);
        //ButterKnife
        ButterKnife.bind(this);
        rg = findViewById(R.id.rg);
        vp = findViewById(R.id.vp);
        //创建Fragment集合
        fragments = new ArrayList<>();
        fragments.add(new SYFragment());
        fragments.add(new WdFragment());
        viewAdapter = new ViewAdapter(fragments, getSupportFragmentManager());
        vp.setAdapter(viewAdapter);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb1:
                        //定下标
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        vp.setCurrentItem(1);
                        break;
                }
            }
        });
    }
    //解绑EventBus
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
