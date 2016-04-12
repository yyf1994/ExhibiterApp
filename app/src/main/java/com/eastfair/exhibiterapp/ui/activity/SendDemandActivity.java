package com.eastfair.exhibiterapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.weight.SupportRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SendDemandActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 发需求
     */
    @Bind(R.id.tv_time)
     TextView tv_time;
    @Bind(R.id.tv_miaoshu)
     TextView tv_description;
    @Bind(R.id.tv_gsname)
     TextView tv_gsname;
    @Bind(R.id.tv_zhanweihao)
     TextView tv_positionnumber;//展位号
    @Bind(R.id.tv_gsproperty)
     TextView tv_gsproperty;
    @Bind(R.id.tv_gsarea)
     TextView tv_gsarea;
    @Bind(R.id.tv_gsprofile)
     TextView tv_gsprofile;
    @Bind(R.id.tv_phonenum)
     TextView tv_phonenum;
    @Bind(R.id.tv_exhibits)
     TextView tv_exhibits;
    @Bind(R.id.tv_name1)
     TextView tv_name1;
    @Bind(R.id.tv_name2)
     TextView tv_name2;
    @Bind(R.id.tv_name3)
     TextView tv_name3;
    @Bind(R.id.img_detial)
    ImageView img_detial;
    @Bind(R.id.img_photo1)
    ImageView img_photo1;
    @Bind(R.id.img_photo2)
    ImageView img_photo2;
    @Bind(R.id.img_photo3)
    ImageView img_photo3;
    @Bind(R.id.toolbar_title)
     Toolbar toolbar_title;

     TextView text_Title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senddemand);
        ButterKnife.bind(this);
        initView();
        registerEvents();
    }


    public void registerEvents() {
        img_photo1.setOnClickListener(this);
        img_photo2.setOnClickListener(this);
        img_photo3.setOnClickListener(this);
        tv_name1.setOnClickListener(this);
        tv_name2.setOnClickListener(this);
        tv_name3.setOnClickListener(this);
        tv_phonenum.setOnClickListener(this);
        tv_exhibits.setOnClickListener(this);
    }


    private void initView() {
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("消息详情");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 展商人员
             */
            case R.id.img_photo1:
                break;
            case R.id.img_photo2:
                break;
            case R.id.img_photo3:
                break;
            case R.id.tv_name1:
                break;
            case R.id.tv_name2:
                break;
            case R.id.tv_name3:
                break;
            //客服电话
            case R.id.tv_phonenum:
                break;
            //参展展品
            case R.id.tv_exhibits:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
