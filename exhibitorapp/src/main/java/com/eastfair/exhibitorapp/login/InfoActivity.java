package com.eastfair.exhibitorapp.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;
import com.eastfair.exhibitorapp.main.MainActivity;
import com.eastfair.exhibitorapp.exhibits.ExhibitsListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends BaseActivity {

    /**
     * 完善信息
     */
    @Bind(R.id.img_qiyephoto)
    ImageView img_qiyephoto;
    @Bind(R.id.tv_gsname)
    EditText edit_gsname;
    @Bind(R.id.tv_zhanweihao)
    EditText edit_positionnumber;//展位号
    @Bind(R.id.tv_gsprofile)
    EditText edit_gsprofile;
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
    @Bind(R.id.btn_tiyan)
    Button btn_tiyan;
    @Bind(R.id.tv_choose_fenlei)
    TextView tv_choose_fenlei;
    @Bind(R.id.tv_choose_purpose)
    TextView tv_choose_purpose;
    @Bind(R.id.tv_choose_property)
    TextView tv_choose_property;
    @Bind(R.id.tv_choose_area)
    TextView tv_choose_area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    private void initView() {
        text_Title.setText("完善信息");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        img_qiyephoto.setVisibility(View.VISIBLE);
        toolbar_title.setNavigationIcon(R.mipmap.back);
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
     * 立即体验
     */
    @OnClick(R.id.btn_tiyan)
    public void tiyan() {
        SkipActivity(MainActivity.class);
        this.finish();
    }

    /**
     * 分类
     */
    @OnClick(R.id.tv_choose_fenlei)
    public void fenlei() {
//        SkipActivity(MainActivity.class);
//        this.finish();
    }

    /**
     * 目的
     */
    @OnClick(R.id.tv_choose_purpose)
    public void purpose() {
//        SkipActivity(MainActivity.class);
//        this.finish();
    }

    /**
     * 性质
     */
    @OnClick(R.id.tv_choose_property)
    public void property() {
//        SkipActivity(MainActivity.class);
//        this.finish();
    }

    /**
     * 地区
     */
    @OnClick(R.id.tv_choose_area)
    public void area() {
//        SkipActivity(MainActivity.class);
//        this.finish();
    }
    /**
     * 添加展商人员
     */
    @OnClick(R.id.img_photo1)
    public void addpeople() {
        SkipActivity(AddPeopleActivity.class);
        this.finish();
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
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
