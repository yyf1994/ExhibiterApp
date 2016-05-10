package com.eastfair.exhibitorapp.me;

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

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseFragment;
import com.eastfair.exhibitorapp.demand.SendDemandActivity;
import com.eastfair.exhibitorapp.capture.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的界面
 */
public class MyFragment extends BaseFragment {

    @Bind(R.id.img_companyphoto)
    ImageView img_companyphoto;
    @Bind(R.id.tv_companyname)
    TextView tv_companyname;
    @Bind(R.id.tv_zhanweihao)
    TextView tv_zhanweihao;
    @Bind(R.id.img_mycode)
    ImageView img_mycode;
    @Bind(R.id.tv_companyproperty)
    TextView tv_companyproperty;
    @Bind(R.id.tv_companyarea)
    TextView tv_companyarea;
    @Bind(R.id.tv_companyprofile)
    TextView tv_companyprofile;
    @Bind(R.id.tv_phonenum)
    TextView tv_phonenum;
    @Bind(R.id.tv_exhibits)
    TextView tv_exhibits;
    @Bind(R.id.btn_update)
    Button btn_update;

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

    @OnClick(R.id.img_mycode)
    public void mycode() {
        Toast.makeText(getActivity(), "img_mycode", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_update)
    public void myupdate() {
        Toast.makeText(getActivity(), "tv_myupdate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
