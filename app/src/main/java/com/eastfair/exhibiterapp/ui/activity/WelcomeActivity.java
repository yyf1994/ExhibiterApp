package com.eastfair.exhibiterapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.MyPagerAdapter;
import com.eastfair.exhibiterapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.xiaopan.android.preference.PreferencesUtils;

public class WelcomeActivity extends BaseActivity implements OnPageChangeListener {

    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.imageView_enter)
    ImageView imageViewEnter;

    private List<View> pageViews;
    private List<ImageView> dots;
    private int lastPosition;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_five);
        ButterKnife.bind(this);
        init();

    }

    public void init() {
        // 存储界面的容器
        pageViews = new ArrayList<View>();
        // 存储图片的界面
        dots = new ArrayList<ImageView>();
        // 添加5个界面
        for (int i = 1; i < 4; i++) {
            View view = this.getLayoutInflater().inflate(R.layout.page_view,
                    null);
            ((ImageView) view.findViewById(R.id.iv))
                    .setImageResource(getResources().getIdentifier(
                            "welcomezh" + i, "mipmap", getPackageName()));
            pageViews.add(view);
        }
        vp.setAdapter(new MyPagerAdapter(pageViews));
        vp.setOnPageChangeListener(this);// TODO Auto-generated method stub
    }

    /**
     * 展商人员按钮点击事件
     */
    @OnClick(R.id.linearLayout)
    public void tiaozhuan() {
        // 标记第一次进入程序时候进入引导页后，把标示存储数据库，判别下次logo界面直接跳转主界面
        PreferencesUtils.putString(WelcomeActivity.this, "welcome", "welcome");
        SkipActivity(MainActivity.class);
        finish();
    }

    /**
     * viewpage监听
     */
    @Override
    public void onPageSelected(int position) {
        if (position == 2) {
            linearLayout.setVisibility(ViewPager.VISIBLE);
            //http://appjf.eastfair.com/bsd18_cn/使用帮助.html
        }
        if (position == 1) {
            linearLayout.setVisibility(ViewPager.GONE);
        }
        if (position == 0) {
            linearLayout.setVisibility(ViewPager.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
