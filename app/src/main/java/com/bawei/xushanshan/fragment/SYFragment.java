package com.bawei.xushanshan.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.xushanshan.R;
import com.bawei.xushanshan.base.BaseFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


public class SYFragment extends BaseFragment {

    private View inflate;
    private Button sys_but;
    private EditText sys_edit;
    private Button sc_but;
    private ImageView sc_img;
    int REQUEST_CODE;
    @Override
    protected View layoutId(LayoutInflater inflater, ViewGroup container) {
        inflate = inflater.inflate(R.layout.fragment_sy, container, false);
        sys_but = inflate.findViewById(R.id.sys_but);
        sys_edit = inflate.findViewById(R.id.sys_edit);
        sc_but = inflate.findViewById(R.id.sc_but);
        sc_img = inflate.findViewById(R.id.sc_img);
        ZXingLibrary.initDisplayOpinion(this.getActivity());
        return inflate;
    }

    @Override
    protected void initView() {
        sys_but.setOnClickListener(new View.OnClickListener() {

            private Intent intent;

            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        sc_but.setOnClickListener(new View.OnClickListener() {

            private Bitmap bitmap;

            @Override
            public void onClick(View view) {
                String textContent = sys_edit.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(getActivity(), "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                sys_edit.setText("");
                bitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                sc_img.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
