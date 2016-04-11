package com.eastfair.exhibiterapp.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.util.RegexChk;

/**
 * 输入新密码
 */
public class NewPassActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 新密码  确认密码
     */
    private EditText ed_newpass, ed_querenpass;

    private Button btn_finish;

    private Toolbar toolbar_title;
    private TextView text_Title ;

    private NewPassActivity mySelf() {
        return NewPassActivity.this;
    }

    @Override
    public void findViews() {
        setContentView(R.layout.activity_newpass);
        initView();

    }

    @Override
    public void registerEvents() {
        btn_finish.setOnClickListener(this);
    }

    @Override
    public void init() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 点击下一步
             */
            case R.id.btn_finish:
                if (getUserEdtext() == true) {
                    toastS("提交成功");
//                    accountTel = ed_tel.getText().toString();
//                    SkipActivity(ForgetNewActivity.class);
                }

                break;
        }
    }
    /**
     * 判断两次密码是否一致
     */
    public boolean getUserEdtext() {

     return true;
    }

    private void initView() {

        ed_newpass = (EditText) findViewById(R.id.edit_newpass);
        ed_querenpass = (EditText) findViewById(R.id.edit_querenpass);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("输入新密码");
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
