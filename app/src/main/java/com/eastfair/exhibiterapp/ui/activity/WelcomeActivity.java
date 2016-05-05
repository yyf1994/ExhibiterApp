package com.eastfair.exhibiterapp.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.MyPagerAdapter;
import com.eastfair.exhibiterapp.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity implements OnPageChangeListener {

    @Bind(R.id.vp)
    ViewPager viewPager;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.imageView_enter)
    ImageView imageViewEnter;

    // 定义ViewPager适配器
    private MyPagerAdapter vpAdapter;
    // 定义一个ArrayList来存放View
    private ArrayList<View> views;
    // 引导图片资源
    private static final int[] pics = {R.mipmap.yindao1, R.mipmap.yindao2};

    int currentPageScrollStatus;
    int pos = 0;
    private int mPos = 0;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_five);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        // 实例化ArrayList对象
        views = new ArrayList<View>();
        // 实例化ViewPager适配器
        vpAdapter = new MyPagerAdapter(views);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            //防止图片不能填满屏幕
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //加载图片资源
            iv.setImageResource(pics[i]);
            views.add(iv);
        }

        // 设置数据
        viewPager.setAdapter(vpAdapter);
        // 设置监听
        viewPager.setOnPageChangeListener(this);
        setMaxPos(views.size() - 1);
    }

    /**
     * 滑动状态改变时调用
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {
    /*
    * 记录page滑动状态 state=1 代表着 正在滑动 state=2 代表着 滑动完毕
    */
        currentPageScrollStatus = arg0;
    }

    /**
     * 当前页面滑动时调用
     */
    @Override
    public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {
        if (pos == 0) {
            // 如果 positionOffsetPixels是0页面也别滑动了，代表在第一页还有往左划
            if (positionOffsetPixels == 0 && currentPageScrollStatus == 1) {

            }
        } else if (pos == mPos) {
            // 已经在最后一页还想往右划
            if (positionOffsetPixels == 0 && currentPageScrollStatus == 1) {
                //添加一个标示 因为这里会如果滑动到最后的话,还想在往后滑动  currentPageScrollStatus一直为1,会执行多次。
                //如果想执行一次的话,添加一个标示,第二次就会更改count的值
                if (count == 0) {
                    SkipActivity(MainActivity.class);
                    finish();
                }
                count++;
            }
        }
        //如果不是最后一页就重置count=0
        if (pos != views.size() - 1) {
            count = 0;
        }
    }

    /**
     * 新的页面被选中时调用
     */
    @Override
    public void onPageSelected(int postion) {
        // 切换page 设置当前postion
        setCurrentPos(postion);
    }

    public void setMaxPos(int maxPos) {
        mPos = maxPos;
    }

    private void setCurrentPos(int postion) {
        pos = postion;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
