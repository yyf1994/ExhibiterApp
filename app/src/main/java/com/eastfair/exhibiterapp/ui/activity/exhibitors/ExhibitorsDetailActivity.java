package com.eastfair.exhibiterapp.ui.activity.exhibitors;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.ui.activity.exhibits.ExhibitsListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExhibitorsDetailActivity extends BaseActivity{

    /**
     * 展商详情界面
     */
    @Bind(R.id.img_qiyephoto)
    ImageView img_qiyephoto;
    @Bind(R.id.tv_title)
     TextView tv_title;
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
    @Bind(R.id.img_photo1)
     ImageView img_photo1;
    @Bind(R.id.img_photo2)
     ImageView img_photo2;
    @Bind(R.id.img_photo3)
     ImageView img_photo3;
    @Bind(R.id.exhibitordetail_toolbar)
     Toolbar toolbar_title;
    @Bind(R.id.text_title)
     TextView text_Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitordetail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("展商详情");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        tv_title.setVisibility(View.GONE);
        img_qiyephoto.setVisibility(View.VISIBLE);
        toolbar_title.setNavigationIcon(R.mipmap.back);


    }

    /**
     * 展商人员按钮点击事件
     */
    @OnClick(R.id.img_photo1)
    public void personclick() {
        CallPhoneNum("tel:10086");
    }

    /**
     * 客服电话按钮点击事件
     */
    @OnClick(R.id.tv_phonenum)
    public void kefunum() {
        CallPhoneNum("tel:10086");
    }

    /**
     * 参展展品按钮点击事件
     */
    @OnClick(R.id.tv_exhibits)
    public void exhibits() {
        SkipActivity(ExhibitsListActivity.class);
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
