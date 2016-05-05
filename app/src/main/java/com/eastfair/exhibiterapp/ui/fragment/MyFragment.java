package com.eastfair.exhibiterapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseFragment;
import com.eastfair.exhibiterapp.ui.activity.SendDemandActivity;
import com.eastfair.exhibiterapp.ui.activity.capture.CaptureActivity;
import com.eastfair.exhibiterapp.ui.activity.me.MyMessageActivity;
import com.eastfair.exhibiterapp.ui.activity.me.MyRedPackageActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的界面
 */
public class MyFragment extends BaseFragment {

    @Bind(R.id.img_myphoto)
    ImageView img_myphoto;
    @Bind(R.id.tv_myname)
    TextView tv_myname;
    @Bind(R.id.tv_num)
    TextView tv_mynum;
    @Bind(R.id.img_mycode)
    ImageView img_mycode;
    @Bind(R.id.tv_mymessage)
    TextView tv_mymessage;
    @Bind(R.id.tv_myredpackage)
    TextView tv_myredpackage;
    @Bind(R.id.btn_myout)
    Button btn_myout;

    @Bind(R.id.myfragment_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.img_title)
    ImageView img_regist;
    @Bind(R.id.img_search)
    ImageView img_search;

    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MyFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        setListener();
        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");
        return view;
    }

    private void initView(View view) {
        //初始化toolbar
        initToolbar((AppCompatActivity) getActivity(),R.id.toolbar_title);
        text_Title.setText("我的");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        img_regist.setImageResource(R.mipmap.faxuqiu);
        toolbar_title.setNavigationIcon(R.mipmap.saoyisao);
        img_search.setVisibility(View.GONE);

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

    /**
     * 发需求按钮点击事件
     */
    @OnClick(R.id.img_title)
    public void sendDeman() {
        Intent intent = new Intent(getActivity(), SendDemandActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.img_myphoto)
    public void myphoto() {
        Toast.makeText(getActivity(), "img_myphoto", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_myname)
    public void myname() {
        Toast.makeText(getActivity(), "tv_myname", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_num)
    public void mynum() {
        Toast.makeText(getActivity(), "tv_num", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.img_mycode)
    public void mycode() {
        Toast.makeText(getActivity(), "img_mycode", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_mymessage)
    public void mymessage() {
        SkipActivity(MyMessageActivity.class);
    }

    @OnClick(R.id.tv_myredpackage)
    public void myredpackage() {
        SkipActivity(MyRedPackageActivity.class);
    }

    @OnClick(R.id.btn_myout)
    public void myout() {
        Toast.makeText(getActivity(), "tv_myout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
