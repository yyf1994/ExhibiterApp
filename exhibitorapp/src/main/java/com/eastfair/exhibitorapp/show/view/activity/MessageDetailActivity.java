package com.eastfair.exhibitorapp.show.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageDetailActivity extends BaseActivity {

    /**
     * 消息详情界面
     */
    @Bind(R.id.tv_activities)
     TextView tv_activities;
    @Bind(R.id.tv_gzname)
     TextView tv_gzname;
    @Bind(R.id.tv_company)
     TextView tv_company;
    @Bind(R.id.tv_job)
     TextView tv_job;
    @Bind(R.id.tv_phonenum)
     TextView tv_phonenum;
    @Bind(R.id.tv_email)
     TextView tv_email;
    @Bind(R.id.tv_follow)
     TextView tv_follow;
    @Bind(R.id.tv_purpose)
     TextView tv_purpose;
    @Bind(R.id.tv_property)
     TextView tv_property;
    @Bind(R.id.tv_area)
     TextView tv_area;


    @Bind(R.id.meaasgedetail_toolbar)
     Toolbar toolbar_title;
    @Bind(R.id.text_title)
     TextView text_Title;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.layout_viewers_detail)
    LinearLayout layout_enterprise_detail;
    private String tag = "1" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagedetail);
        ButterKnife.bind(this);
        initView();
        setListener();
    }


    private void initView() {
        tag = getIntent().getStringExtra("tag");
        text_Title.setText("消息详情");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        toolbar_title.setNavigationIcon(R.mipmap.back);
        tp = tv_title.getPaint();
        tp.setFakeBoldText(true);
        if(tag.equals("1")){
            layout_enterprise_detail.setVisibility(View.GONE);
        }else{
            layout_enterprise_detail.setVisibility(View.VISIBLE);
        }

    }
    private void setListener() {
        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 客服电话按钮点击事件
     */
    @OnClick(R.id.tv_phonenum)
    public void tel() {
        CallPhoneNum("tel:10086");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
