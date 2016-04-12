package com.eastfair.exhibiterapp.ui.activity.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 输入新密码
 */
public class NewPassActivity extends BaseActivity {
    /**
     * 新密码  确认密码
     */
    @Bind(R.id.edit_newpass)
    EditText ed_newpass;
    @Bind(R.id.edit_querenpass)
    EditText ed_querenpass;
    @Bind(R.id.btn_finish)
    Button btn_finish;
    @Bind(R.id.toolbar_title)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpass);
        ButterKnife.bind(this);
        initView();
    }

    //完成按钮点击事件
    @OnClick(R.id.btn_finish)
    public void btnfinish() {
        if (getUserEdtext() == true) {
            toastS("提交成功");
//                    accountTel = ed_tel.getText().toString();
//                    SkipActivity(ForgetNewActivity.class);
        }
    }

    /**
     * 判断两次密码是否一致
     */
    public boolean getUserEdtext() {

        return true;
    }

    private void initView() {
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
