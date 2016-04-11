package com.eastfair.exhibiterapp.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.ui.activity.regist.RegistActivity;
import com.eastfair.exhibiterapp.util.RegexChk;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private LoginActivity mySelf() {
        return LoginActivity.this;
    }

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
    private EditText edit_username;
    private EditText edit_password;
    private Button btn_login;
    private TextView text_forget;
    private Toolbar toolbar_title;
    private TextView text_Title ;
    private TextView text_regist ;



    @Override
    public void findViews() {
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    public void registerEvents() {
        btn_login.setOnClickListener(this);
        text_forget.setOnClickListener(this);
        text_regist.setOnClickListener(this);
    }

    @Override
    public void init() {

    }


    private void initView() {
        edit_username = (EditText) findViewById(R.id.edit_phonenum);
        edit_password = (EditText) findViewById(R.id.edit_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        text_forget = (TextView) findViewById(R.id.text_forget);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        text_regist = (TextView) toolbar_title.findViewById(R.id.text_title1);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");
        text_Title.setText("登录");
        text_regist.setText("注册");
        

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 登陆
             */
            case R.id.btn_login:
                getUserEdtext();
                break;
            /**
             * 忘记密码
             */
            case R.id.text_forget:
                SkipActivity(ForgetActivity.class);
                break;
            /**
             * 注册
             */
            case R.id.text_title1:
                SkipActivity(RegistActivity.class);
//                finish();
                break;
        }
    }
    /**
     * 判断用户是否输入账号，密码
     */
    public void getUserEdtext() {
        String username = edit_username.getText().toString();
        String password = edit_password.getText().toString();
        if ( new RegexChk().checkPhone(username) == false) {
            edit_username.setText("");
            edit_password.setText("");
            toastS("手机号不合法，请重新输入");
            return;
        }
       if (new RegexChk().checkIdPassNull(password)==false) {
           edit_password.setText("");
           edit_username.setText("");
            toastS( "密码不能有空格,请重新输入6-18位密码！");
            return ;
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

}
