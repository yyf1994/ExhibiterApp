package com.eastfair.exhibitorapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;
import com.eastfair.exhibitorapp.ui.activity.exhibits.ExhibitsListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity {

    /**
     * 详情界面
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
    @Bind(R.id.text_title)
     TextView text_Title ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initView();
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


    private void initView() {
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("消息详情");
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
