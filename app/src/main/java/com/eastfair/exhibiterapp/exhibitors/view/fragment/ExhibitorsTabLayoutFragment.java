package com.eastfair.exhibiterapp.exhibitors.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.main.adapter.MainPagerAdapter;
import com.eastfair.exhibiterapp.base.BaseFragment;
import com.eastfair.exhibiterapp.demand.view.SendDemandActivity;
import com.eastfair.exhibiterapp.capture.CaptureActivity;
import com.eastfair.exhibiterapp.exhibitors.view.activity.ExhibitorsSearchActivity;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展商界面
 */
public class ExhibitorsTabLayoutFragment extends BaseFragment{
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.exhibitors_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title ;
    @Bind(R.id.img_title)
    ImageView img_regist ;
    @Bind(R.id.img_search)
    ImageView img_search ;
    @Bind(R.id.sliding_tabs)
    SegmentTabLayout tabLayout;
    private String[] mTitles = {"关注", "展商"};

    private MainPagerAdapter pagerAdapter;
    private List<Fragment> list_fragment;                                //定义要装fragment的列表

    private ExhibitorsFollowFragment exhibitorsFollowFragment;              //展商关注
    private ExhibitorsInterestFragment exhibitorsInterestFragment;            //展商感兴趣

    public static ExhibitorsTabLayoutFragment newInstance(String param1) {
        ExhibitorsTabLayoutFragment fragment = new ExhibitorsTabLayoutFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ExhibitorsTabLayoutFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exhibitors_tablayout, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");
        initView();
        setListener();
        return view;
    }

    private void setListener() {
        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }


    private void initView() {
        //初始化toolbar
        initToolbar((AppCompatActivity) getActivity(),R.id.exhibitors_toolbar);
        img_regist.setImageResource(R.mipmap.faxuqiu);
        toolbar_title.setNavigationIcon(R.mipmap.saoyisao);
        img_search.setVisibility(View.VISIBLE);

        //初始化各fragment
        exhibitorsFollowFragment = new ExhibitorsFollowFragment();
        exhibitorsInterestFragment = new ExhibitorsInterestFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(exhibitorsFollowFragment);
        list_fragment.add(exhibitorsInterestFragment);
        titleTabLayout();
    }

    private void titleTabLayout() {
        pagerAdapter = new MainPagerAdapter(getChildFragmentManager(), list_fragment, mTitles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setTabData(mTitles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
    }

    /**
     * 搜索按钮点击事件
     * */
    @OnClick(R.id.img_search)
    public void search(){
        SkipActivity(ExhibitorsSearchActivity.class);
    }

    /**
     * 发需求按钮点击事件
     */
    @OnClick(R.id.img_title)
    public void sendDeman() {
        Intent intent = new Intent(getActivity(), SendDemandActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
