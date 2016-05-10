package com.eastfair.exhibiterapp.main.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.main.adapter.MainPagerAdapter;
import com.eastfair.exhibiterapp.model.TabEntity;
import com.eastfair.exhibiterapp.exhibition.view.fragment.ExhibitionFragment;
import com.eastfair.exhibiterapp.exhibitors.view.fragment.ExhibitorsTabLayoutFragment;
import com.eastfair.exhibiterapp.exhibits.view.fragment.ExhibitsFragment;
import com.eastfair.exhibiterapp.message.view.fragment.MessageFragment;
import com.eastfair.exhibiterapp.me.view.fragment.MyFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bottom_navigation)
    CommonTabLayout bottomNavigation;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    private MessageFragment mMessageFragment;
    private ExhibitionFragment mExhibitionFragment;
    private ExhibitorsTabLayoutFragment exhibitorsTabLayoutFragment;
    private ExhibitsFragment mExhibitsFragment;
    private MyFragment mMyFragment;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MainPagerAdapter mViewPagerAdapter;
    Random mRandom = new Random();
    private String[] mTitles = {"消息", "展商", "展品", "展场","我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.xiaoxi, R.mipmap.zhanshang,
            R.mipmap.zhanpin, R.mipmap.zhanchang,R.mipmap.wode};
    private int[] mIconSelectIds = {
            R.mipmap.xiaoxixuanzhong, R.mipmap.zhanshangxuanzhong,
            R.mipmap.zhanpinxuanzhong, R.mipmap.zhanchangdianji,R.mipmap.wodedianji};
    private Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        setAdapter();
        //设置未读消息
        setMessage();
    }

    private void setMessage() {
        //两位数
        bottomNavigation.showMsg(0, 55);
        bottomNavigation.setMsgMargin(0, -5, 5);

        //三位数
//        bottomNavigation.showMsg(1, 100);
//        bottomNavigation.setMsgMargin(1, -5, 5);

        //设置未读消息红点
//        bottomNavigation.showDot(2);
//        MsgView rtv_2_2 = bottomNavigation.getMsgView(2);
//        if (rtv_2_2 != null) {
//            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
//        }

        //设置未读消息背景
//        bottomNavigation.showMsg(3, 5);
//        bottomNavigation.setMsgMargin(3, 0, 5);
//        MsgView rtv_2_3 = bottomNavigation.getMsgView(3);
//        if (rtv_2_3 != null) {
//            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
//        }
    }

    private void setAdapter() {
        mViewPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),mFragments,mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    private void initData() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mMessageFragment = new MessageFragment();
        mExhibitionFragment = new ExhibitionFragment();
        exhibitorsTabLayoutFragment = new ExhibitorsTabLayoutFragment();
        mExhibitsFragment = new ExhibitsFragment();
        mMyFragment = new MyFragment();
        mFragments.add(mMessageFragment);
        mFragments.add(exhibitorsTabLayoutFragment);
        mFragments.add(mExhibitsFragment);
        mFragments.add(mExhibitionFragment);
        mFragments.add(mMyFragment);

    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void initView() {
        bottomNavigation.setTabData(mTabEntities);
        bottomNavigation.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    bottomNavigation.showMsg(0, mRandom.nextInt(100) + 1);
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toast.makeText(MainActivity.this, "result" + scanResult, Toast.LENGTH_SHORT).show();
//            resultTextView.setText(scanResult);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
