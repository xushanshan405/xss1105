package com.bawei.zuoye1105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;



public class MainActivity extends AppCompatActivity {

    private Button btn_rb1, btn_rb2, btn_rb3, btn_rb4;
    private TextView text_view;
    private DbController mDbController;
    private PersonInfor personInfor1,personInfor2,personInfor3,personInfor4;
    private TextView dataArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbController = DbController.getInstance(MainActivity.this);
        initView();
        Envent();
        similateData();
    }

    private void similateData() {
        personInfor1 = new PersonInfor(null,"710","徐姗姗","男");
        personInfor2 = new PersonInfor(null,"711","杜欢宁","女");
        personInfor3 = new PersonInfor(null,"712","筱姗姗","男");
        personInfor4 = new PersonInfor(null,"713","哼唧唧","女");
    }

    private void Envent() {
        //增加
        btn_rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbController.insertOrReplace(personInfor1);

                mDbController.insertOrReplace(personInfor2);

                mDbController.insertOrReplace(personInfor3);

                mDbController.insertOrReplace(personInfor4);

                showDataList();
            }
        });
        //刪除
        btn_rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbController.delete("哼唧唧");

                showDataList();
            }
        });
        //修改
        btn_rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbController.update(personInfor1);

                showDataList();
            }
        });
        //查詢
        btn_rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataList();
            }
        });
    }

    private void showDataList() {
        StringBuilder sb = new StringBuilder();
        List<PersonInfor> personInfors = mDbController.searchAll();
        for(PersonInfor personInfor:personInfors){
            sb.append("id:").append(personInfor.getId())
                    .append("perNo:").append(personInfor.getPerNo())
                    .append("name:").append(personInfor.getName())
                    .append("sex:").append(personInfor.getSex())
                    .append("\n");
        }
        text_view.setText(sb.toString());
    }

    private void initView() {

        btn_rb1 = findViewById(R.id.btn_rb1);

        btn_rb2 = findViewById(R.id.btn_rb2);

        btn_rb3 = findViewById(R.id.btn_rb3);

        btn_rb4 = findViewById(R.id.btn_rb4);

        text_view= findViewById(R.id.text_view);

    }
}
