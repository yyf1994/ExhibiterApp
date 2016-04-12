package com.eastfair.exhibiterapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseFragment;
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
    @Bind(R.id.tv_myout)
    TextView tv_myout;

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
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        return view;
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

    @OnClick(R.id.tv_myout)
    public void myout() {
        Toast.makeText(getActivity(), "tv_myout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
