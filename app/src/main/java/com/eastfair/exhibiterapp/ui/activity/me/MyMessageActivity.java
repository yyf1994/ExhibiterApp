package com.eastfair.exhibiterapp.ui.activity.me;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;

/**
 * 我的信息
 * */
public class MyMessageActivity extends BaseActivity implements View.OnClickListener {

    private EditText edit_myname;
    private EditText edit_mygsname;
    private EditText edit_myjob;
    private EditText edit_myemail;
    private EditText edit_myphonenum;
    private EditText edit_mypass;
    private EditText edit_myfenlei;
    private EditText edit_myproperty;
    private EditText edit_mymudi;
    private EditText edit_myarea;
    private Toolbar toolbar_title;
    private TextView text_Title;

    private MyMessageActivity mySelf() {
        return MyMessageActivity.this;
    }

    @Override
    public void findViews() {
        setContentView(R.layout.activity_mymessage);
        initView();
    }

    @Override
    public void registerEvents() {
        edit_myname.setOnClickListener(this);
        edit_mygsname.setOnClickListener(this);
        edit_myjob.setOnClickListener(this);
        edit_myemail.setOnClickListener(this);
        edit_myphonenum.setOnClickListener(this);
        edit_mypass.setOnClickListener(this);
        edit_myfenlei.setOnClickListener(this);
        edit_myproperty.setOnClickListener(this);
        edit_mymudi.setOnClickListener(this);
        edit_myarea.setOnClickListener(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_myname:

                break;
            case R.id.edit_gsname:
                break;

        }
    }

    private void initView() {
        edit_myname = (EditText) findViewById(R.id.edit_myname);
        edit_mygsname = (EditText) findViewById(R.id.edit_gsname);
        edit_myjob = (EditText) findViewById(R.id.edit_myjob);
        edit_myemail = (EditText) findViewById(R.id.edit_myemail);
        edit_myphonenum = (EditText) findViewById(R.id.edit_mynum);
        edit_myfenlei = (EditText) findViewById(R.id.edit_mycategory);
        edit_myproperty = (EditText) findViewById(R.id.edit_myproperty);
        edit_mymudi = (EditText) findViewById(R.id.edit_mygoal);
        edit_myarea = (EditText) findViewById(R.id.edit_myarea);
        edit_mypass = (EditText) findViewById(R.id.edit_mypass);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("我的信息");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}