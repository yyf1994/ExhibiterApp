package com.eastfair.exhibitorapp.ui.activity.exhibits;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExhibitsDetailActivity extends BaseActivity {

    /**
     * 展品详情界面
     */

    @Bind(R.id.exhibitsdetail_toolbar)
     Toolbar toolbar_title;
    @Bind(R.id.text_title)
     TextView text_Title;
    @Bind(R.id.img_detial)
    ImageView img_detial;
    @Bind(R.id.tv_exhibits_name)
    TextView tv_exhibits_name;
    @Bind(R.id.tv_exhibits_type)
    TextView tv_exhibits_type;
    @Bind(R.id.tv_activities)
    TextView tv_activities;
    @Bind(R.id.btn_update)
    Button btn_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitsdetail);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("展品详情");
        toolbar_title.setNavigationIcon(R.mipmap.back);
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
    }

    /**
     * 修改
     */
    @OnClick(R.id.btn_update)
    public void update() {
        SkipActivity(AddExhibitsActivity.class);
        finish();
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
