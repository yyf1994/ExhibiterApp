package com.eastfair.exhibiterapp.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.FragmentPagerAdapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * 展商界面
 */
public class ExhibitorsTabLayoutFragment extends Fragment {

    private FragmentPagerAdapter1 pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

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
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        //初始化各fragment
        exhibitorsFollowFragment = new ExhibitorsFollowFragment();
        exhibitorsInterestFragment = new ExhibitorsInterestFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(exhibitorsFollowFragment);
        list_fragment.add(exhibitorsInterestFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("关注");
        list_title.add("感兴趣");

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));

        pagerAdapter = new FragmentPagerAdapter1(getChildFragmentManager(),list_fragment,list_title);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;

    }

}
