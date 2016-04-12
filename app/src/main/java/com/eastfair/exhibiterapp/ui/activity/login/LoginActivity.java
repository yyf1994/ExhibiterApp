package com.eastfair.exhibiterapp.ui.activity.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.ui.activity.WelcomeActivity;
import com.eastfair.exhibiterapp.ui.activity.regist.RegistActivity;
import com.eastfair.exhibiterapp.util.RegexChk;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity{


    /**
     * 登陆界面
     */
    /**
     * nameed 用户名
     * passworded 密码
     * forgettv 忘记密码
     * newuser_tv 新用户注册
     * logintv 登陆
     */
    @Bind(R.id.edit_phonenum)
    EditText edit_username;
    @Bind(R.id.edit_password)
    EditText edit_password;
    @Bind(R.id.btn_login)
    Button btn_login;
    @Bind(R.id.text_forget)
    TextView text_forget;
    @Bind(R.id.toolbar_title)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.text_title1)
    TextView text_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.btn_login)
    public void login(){
        getUserEdtext();
    }

    @OnClick(R.id.text_forget)
    public void forget(){
        SkipActivity(ForgetActivity.class);
    }

    @OnClick(R.id.text_title1)
    public void regist(){
        SkipActivity(RegistActivity.class);
    }


    private void initView() {
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");
        text_Title.setText("登录");
        text_regist.setText("注册");
    }

    /**
     * 判断用户是否输入账号，密码
     */
    public void getUserEdtext() {
        String username = edit_username.getText().toString();
        String password = edit_password.getText().toString();
        if (new RegexChk().checkPhone(username) == false) {
            edit_username.setText("");
            edit_password.setText("");
            toastS("手机号不合法，请重新输入");
            return;
        }
        if (new RegexChk().checkIdPassNull(password) == false) {
            edit_password.setText("");
            edit_username.setText("");
            toastS("密码不能有空格,请重新输入6-18位密码！");
            return;
        }
        /**
         * 登陆请求
         */
//        SkipActivity(MainActivity.class);
        SkipActivity(WelcomeActivity.class);
        this.finish();
//        startProgressDialog();
//        loginAction(username, password);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
