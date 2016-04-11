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

/**
 * 我的界面
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{

    private ImageView img_myphoto;
    private TextView tv_myname;
    private TextView tv_mynum;
    private ImageView img_mycode;
    private TextView tv_mymessage;
    private TextView tv_myredpackage;
    private TextView tv_myout;

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
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
//        TextView tv = (TextView)view.findViewById(R.id.tv_location);
//        tv.setText(agrs1);

        initView(view);
        registerEvents();
        return view;
    }

    private void initView(View view) {
        img_myphoto = (ImageView) view.findViewById(R.id.img_myphoto);
        tv_myname = (TextView)view.findViewById(R.id.tv_myname);
        tv_mynum = (TextView)view.findViewById(R.id.tv_num);
        img_mycode = (ImageView) view.findViewById(R.id.img_mycode);
        tv_mymessage = (TextView)view.findViewById(R.id.tv_mymessage);
        tv_myredpackage = (TextView)view.findViewById(R.id.tv_myredpackage);
        tv_myout = (TextView)view.findViewById(R.id.tv_myout);
    }

    public void registerEvents() {
        img_myphoto.setOnClickListener(this);
        tv_myname.setOnClickListener(this);
        tv_mynum.setOnClickListener(this);
        img_mycode.setOnClickListener(this);
        tv_mymessage.setOnClickListener(this);
        tv_myredpackage.setOnClickListener(this);
        tv_myout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.img_myphoto:
                Toast.makeText(getActivity(),"img_myphoto",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_myname:
                Toast.makeText(getActivity(),"tv_myname",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_num:
                Toast.makeText(getActivity(),"tv_num",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_mycode:
                Toast.makeText(getActivity(),"img_mycode",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_mymessage:
                SkipActivity(MyMessageActivity.class);
                break;
            case R.id.tv_myredpackage:
                SkipActivity(MyRedPackageActivity.class);
                break;
            case R.id.tv_myout:
                Toast.makeText(getActivity(),"tv_myout",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}
