package com.bawei.xushanshan.base;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.xushanshan.R;


public abstract class BaseFragment extends Fragment {

    private View layoutId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutId = layoutId(inflater, container);
        return layoutId;
    }

    protected abstract View layoutId(LayoutInflater inflater, ViewGroup container);

    protected abstract void initView();
}
