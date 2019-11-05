package com.bawei.xushanshan.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.xushanshan.Bean;
import com.bawei.xushanshan.R;
import com.bawei.xushanshan.adapter.RecyAdapter;
import com.bawei.xushanshan.base.BaseFragment;
import com.bawei.xushanshan.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class WdFragment extends BaseFragment {

    private View inflate;
    private RecyclerView recyc;
    private RecyAdapter recyAdapter;
    private ArrayList<Bean> beans;

    @Override
    protected View layoutId(LayoutInflater inflater, ViewGroup container) {
        inflate = inflater.inflate(R.layout.fragment_wd, container, false);

        return inflate;
    }

    @Override
    protected void initView() {
        recyc = inflate.findViewById(R.id.recyc);
        beans = new ArrayList<>();
        getData();
       /* */
    }

    private void getData() {
        Observable<Bean> observable = RetrofitUtil.getApiServer().shou();
        Observer<Bean> observer = new Observer<Bean>() {

            private List<Bean.ResultBean> result;

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Bean bean) {
                Log.d("asd", "onNext: "+bean.getMessage());
                result = bean.getResult();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyc.setLayoutManager(linearLayoutManager);
                recyAdapter = new RecyAdapter(result,getActivity());
                recyc.setAdapter(recyAdapter);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("shi", "onError: "+e.toString());
            }

            @Override
            public void onComplete() {

            }
        };observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
