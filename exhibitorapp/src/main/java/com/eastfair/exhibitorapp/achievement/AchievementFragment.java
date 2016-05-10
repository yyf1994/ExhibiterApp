package com.eastfair.exhibitorapp.achievement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.adapter.AchievementAdapter;
import com.eastfair.exhibitorapp.base.BaseFragment;
import com.eastfair.exhibitorapp.export.ExportActivity;
import com.eastfair.exhibitorapp.demand.SendDemandActivity;
import com.eastfair.exhibitorapp.capture.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 成就界面
 */
public class AchievementFragment extends BaseFragment {

    @Bind(R.id.tv_daochu)
    TextView tv_daochu;
    @Bind(R.id.tv_shaixuan)
    TextView tv_all;
    @Bind(R.id.achievement_list)
    ExpandableListView achievement_list;

    @Bind(R.id.exhibits_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.img_title)
    ImageView img_regist;

    /**
     * 父数据源
     */
    private List<String> parents;
    /**
     * 子数据源
     */
    private List<List<String>> childs;

    public static AchievementFragment newInstance(String param1) {
        AchievementFragment fragment = new AchievementFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public AchievementFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exhibits, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
       /* String agrs1 = bundle.getString("agrs1");
        TextView tv = (TextView)view.findViewById(R.id.tv_location);
        tv.setText(agrs1);*/
        getData();
        initView(view);
        setListener();
        return view;
    }

    private void getData() {
        parents = new ArrayList<>();
        parents.add("我关注的");
        parents.add("关注我的");
        parents.add("打过电话的");

        childs = new ArrayList<>();
        for (int i = 0; i < parents.size(); i++) {
            List<String> c = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                c.add("child:" + j + "");
            }
            childs.add(c);
        }

    }

    private void setListener() {
        /** 设置展开的监听事件 */
        achievement_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                /** 循环遍历，把不是当前选中的其他组关闭 */
                for (int i = 0; i < parents.size(); i++) {
                    if (i != groupPosition) {
                        achievement_list.collapseGroup(i);
                    }
                }
            }
        });

        /** 设置子item的单击事件 */
        achievement_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), childs.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }


    private void initView(View view) {

        tv_all.setVisibility(View.VISIBLE);
        tv_all.setText("显示全部");
        achievement_list.setAdapter(new AchievementAdapter(parents, childs, getActivity()));
        /**默认全部展开*/
        int groupCount = achievement_list.getCount();

        for (int i=0; i<groupCount; i++) {

            achievement_list.expandGroup(i);

        };
        /** 去除每组对应的显示效果，默认是有箭头的，这里我们需要自己的效果，就把默认的去除掉 */
        achievement_list.setGroupIndicator(null);

        //初始化toolbar
        initToolbar((AppCompatActivity) getActivity(), R.id.toolbar_title);
        text_Title.setText("成就");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        img_regist.setImageResource(R.mipmap.faxuqiu);
        toolbar_title.setNavigationIcon(R.mipmap.saoyisao);

    }

    /**
     * 发需求按钮点击事件
     */
    @OnClick(R.id.img_title)
    public void sendDeman() {
        Intent intent = new Intent(getActivity(), SendDemandActivity.class);
        startActivity(intent);
    }

    public void deleteUser(final int position) {
//        mAdapter.remove(mAdapter.getItem(position));
        showToast("删除成功");

    }

    public void showToast(String value) {
        Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.tv_daochu)
    public void daochu() {
        SkipActivity(ExportActivity.class);
    }

    @OnClick(R.id.tv_shaixuan)
    public void shaixuan() {
        Toast.makeText(getActivity(), "quanbu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
