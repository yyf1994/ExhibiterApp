package com.eastfair.exhibiterapp.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.ui.activity.exhibitors.ExhibitorsDetailActivity;
import com.eastfair.exhibiterapp.ui.activity.exhibits.ExhibitsListActivity;
import com.eastfair.exhibiterapp.util.RegexChk;

public class DetailActivity extends BaseActivity implements View.OnClickListener {
    private DetailActivity mySelf() {
        return DetailActivity.this;
    }

    /**
     * 详情界面
     */
    private TextView tv_time;
    private TextView tv_description;
    private TextView tv_gsname;
    private TextView tv_positionnumber;//展位号
    private TextView tv_gsproperty;
    private TextView tv_gsarea;
    private TextView tv_gsprofile;
    private TextView tv_phonenum;
    private TextView tv_exhibits;
    private TextView tv_name1;
    private TextView tv_name2;
    private TextView tv_name3;
    private ImageView img_detial;
    private ImageView img_photo1;
    private ImageView img_photo2;
    private ImageView img_photo3;
    private Toolbar toolbar_title;
    private TextView text_Title ;



    @Override
    public void findViews() {
        setContentView(R.layout.activity_detail);
        initView();
    }

    @Override
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

    @Override
    public void init() {

    }


    private void initView() {
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_description = (TextView) findViewById(R.id.tv_miaoshu);
        tv_gsname = (TextView) findViewById(R.id.tv_gsname);
        tv_positionnumber = (TextView) findViewById(R.id.tv_zhanweihao);
        tv_gsproperty = (TextView) findViewById(R.id.tv_gsproperty);
        tv_gsarea = (TextView) findViewById(R.id.tv_gsarea);
        tv_gsprofile = (TextView) findViewById(R.id.tv_gsprofile);
        tv_phonenum = (TextView) findViewById(R.id.tv_phonenum);
        tv_exhibits = (TextView) findViewById(R.id.tv_exhibits);
        tv_name1 = (TextView) findViewById(R.id.tv_name1);
        tv_name2 = (TextView) findViewById(R.id.tv_name2);
        tv_name3 = (TextView) findViewById(R.id.tv_name3);
        img_detial = (ImageView) findViewById(R.id.img_detial);
        img_photo1 = (ImageView) findViewById(R.id.img_photo1);
        img_photo2 = (ImageView) findViewById(R.id.img_photo2);
        img_photo3 = (ImageView) findViewById(R.id.img_photo3);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
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
                CallPhoneNum("tel:10086");
                break;
            case R.id.img_photo2:
                CallPhoneNum("tel:10086");
                break;
            case R.id.img_photo3:
                CallPhoneNum("tel:10086");
                break;
            case R.id.tv_name1:
                CallPhoneNum("tel:10086");
                break;
            case R.id.tv_name2:
                CallPhoneNum("tel:10086");
                break;
            case R.id.tv_name3:
                CallPhoneNum("tel:10086");
                break;
            //客服电话
            case R.id.tv_phonenum:
                CallPhoneNum("tel:10086");
                break;
            //参展展品
            case R.id.tv_exhibits:
                SkipActivity(ExhibitsListActivity.class);
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
