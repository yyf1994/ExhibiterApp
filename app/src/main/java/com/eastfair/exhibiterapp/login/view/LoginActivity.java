package com.eastfair.exhibiterapp.login.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.login.LoginContract;
import com.eastfair.exhibiterapp.login.presenter.LoginPresenter;
import com.eastfair.exhibiterapp.regist.RegistActivity;
import com.eastfair.exhibiterapp.util.CodeUtil;
import com.eastfair.exhibiterapp.util.RegexChk;
import com.eastfair.exhibiterapp.util.SharePreferenceUtil;
import com.eastfair.exhibiterapp.welcome.view.WelcomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {


    /**
     * 登陆界面
     */
    @Bind(R.id.edit_phonenum)
    EditText edit_phonennum;
    @Bind(R.id.edit_pic)
    EditText edit_pic;
    @Bind(R.id.img_code)
    ImageView img_code;
    @Bind(R.id.text_huanyizhang)
    TextView text_huanyizhang;
    @Bind(R.id.edit_password)
    EditText edit_code;
    @Bind(R.id.btn_getcode)
    Button btn_getcode;
    @Bind(R.id.btn_login)
    Button btn_login;

    private boolean isFirst;
    private String getCode = null; //获取验证码的值
    private TimeCount time;//倒计时
    private LoginContract.Present mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initParams();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresent.start();
    }

    @OnClick(R.id.btn_login)
    public void login() {
        mPresent.login();

    }

    //换一张
    @OnClick(R.id.text_huanyizhang)
    public void huanyizhang() {
        img_code.setImageBitmap(CodeUtil.getInstance().createBitmap());
    }

    //获取验证码
    @OnClick(R.id.btn_getcode)
    public void getcode() {
        if (getUserTel() == true) {
            time.start();//开始计时
//                    networkDataCode(ed_tel.getText().toString());
        }
    }

    private void initView() {
        img_code.setImageBitmap(CodeUtil.getInstance().createBitmap());
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
    }

    private void initParams() {
        mPresent = new LoginPresenter(this);
    }

    @Override
    public void loginError(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        /**
         * 登陆请求
         */
        isFirst = (boolean) SharePreferenceUtil.getParam(LoginActivity.this, "isFirst", true);
        Log.d("isFirst", "isFirst" + isFirst);
        if (isFirst) {
            SkipActivity(WelcomeActivity.class);
            SharePreferenceUtil.setParam(this, "isFirst", false);
            this.finish();
        } else {
//            SkipActivity(MainActivity.class);
            SkipActivity(RegistActivity.class);
            this.finish();
        }
    }

    @Override
    public String getPhoneNum() {
        return edit_phonennum.getText().toString();
    }

    @Override
    public String getEditImgCode() {
        return edit_pic.getText().toString();
    }

    @Override
    public String getImageCode() {
        return CodeUtil.getInstance().getCode();//图片验证码
    }

    @Override
    public String getCode() {
        return edit_code.getText().toString();
    }

    /**
     * 判断用户是否输入手机号是否正确
     */
    @Override
    public boolean getUserTel() {
        String tel = edit_phonennum.getText().toString();
        RegexChk cRegexChk = new RegexChk();
        if (TextUtils.isEmpty(tel)
                || cRegexChk.checkPhone(tel) == false) {
            edit_phonennum.setText("");
            toastS("手机号不合法，请重新输入");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setImgCode() {
        img_code.setImageBitmap(CodeUtil.getInstance().createBitmap());
        edit_pic.setText("");
    }

    @Override
    public void setPresenter(LoginContract.Present presenter) {

    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            btn_getcode.setText("重新获取");
            btn_getcode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            btn_getcode.setClickable(false);
            btn_getcode.setText(millisUntilFinished / 1000 + "s");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
