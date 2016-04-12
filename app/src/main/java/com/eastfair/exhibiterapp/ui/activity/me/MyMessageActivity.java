package com.eastfair.exhibiterapp.ui.activity.me;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的信息
 * */
public class MyMessageActivity extends BaseActivity {

    @Bind(R.id.edit_myname)
     EditText edit_myname;
    @Bind(R.id.edit_gsname)
     EditText edit_mygsname;
    @Bind(R.id.edit_myjob)
     EditText edit_myjob;
    @Bind(R.id.edit_myemail)
     EditText edit_myemail;
    @Bind(R.id.edit_mynum)
     EditText edit_myphonenum;
    @Bind(R.id.edit_mypass)
     EditText edit_mypass;
    @Bind(R.id.edit_mycategory)
     EditText edit_myfenlei;
    @Bind(R.id.edit_myproperty)
     EditText edit_myproperty;
    @Bind(R.id.edit_mygoal)
     EditText edit_mymudi;
    @Bind(R.id.edit_myarea)
     EditText edit_myarea;
    @Bind(R.id.toolbar_title)
     Toolbar toolbar_title;
    @Bind(R.id.text_title)
     TextView text_Title;
    @Bind(R.id.btn_save)
    Button btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("我的信息");
    }

    /**
     * 保存按钮的点击
     * */
    @OnClick(R.id.btn_save)
    public void save(){

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}