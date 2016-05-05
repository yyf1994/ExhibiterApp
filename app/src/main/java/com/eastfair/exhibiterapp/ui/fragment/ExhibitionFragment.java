package com.eastfair.exhibiterapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseFragment;
import com.eastfair.exhibiterapp.ui.activity.SendDemandActivity;
import com.eastfair.exhibiterapp.ui.activity.capture.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展场界面
 */
public class ExhibitionFragment extends BaseFragment {

    @Bind(R.id.exhibition_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.img_title)
    ImageView img_regist;
    @Bind(R.id.img_search)
    ImageView img_search;

    public static ExhibitionFragment newInstance(String param1) {
        ExhibitionFragment fragment = new ExhibitionFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ExhibitionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");

        initView(view);
        setListener();
        return view;
    }

    private void initView(View view) {
        //初始化toolbar
        initToolbar((AppCompatActivity) getActivity(),R.id.exhibition_toolbar);
        text_Title.setText("展场");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        img_regist.setImageResource(R.mipmap.faxuqiu);
        toolbar_title.setNavigationIcon(R.mipmap.saoyisao);
        img_search.setVisibility(View.GONE);
    }
    /**
     * 发需求按钮点击事件
     */
    @OnClick(R.id.img_title)
    public void sendDeman() {
        Intent intent = new Intent(getActivity(), SendDemandActivity.class);
        startActivity(intent);
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
