package com.eastfair.exhibiterapp.ui.activity.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.ui.activity.MainActivity;
import com.eastfair.exhibiterapp.ui.activity.WelcomeActivity;
import com.eastfair.exhibiterapp.util.CodeUtil;
import com.eastfair.exhibiterapp.util.RegexChk;
import com.eastfair.exhibiterapp.util.SharePreferenceUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


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
    String getCode = null; //获取验证码的值
    private TimeCount time;//倒计时

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();

    }

    @OnClick(R.id.btn_login)
    public void login() {
        Boolean loginTag =  getUserEdtext();
        if(loginTag){
            LoginSuccess();
        }

    }

//    @OnClick(R.id.text_forget)
//    public void forget(){
//        SkipActivity(ForgetActivity.class);
//    }

//    @OnClick(R.id.img_title)
//    public void regist(){
//        SkipActivity(RegistActivity.class);
//    }

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
    /**
     * 判断用户是否输入手机号
     */
    public boolean getUserTel() {

        String tel = edit_phonennum.getText().toString();
        RegexChk cRegexChk = new RegexChk();
        if (tel.equals("")
                || cRegexChk.checkPhone(tel) == false) {
            edit_phonennum.setText("");
            toastS("手机号不合法，请重新输入");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断用户是否输入手机号,图片验证码和验证码
     */
    public boolean getUserEdtext() {

        String tel = edit_phonennum.getText().toString();
        String code = CodeUtil.getInstance().getCode();//图片验证码
        String getCode = edit_pic.getText().toString();//编辑框中的图片验证码
        Log.d("code +getCode",code+"----"+getCode);
        RegexChk cRegexChk = new RegexChk();
        if (tel.equals("")
                || cRegexChk.checkPhone(tel) == false) {
            toastS("手机号不合法，请重新输入");
            return false;
        }else if(!getCode.equalsIgnoreCase(code)){
            toastS("图片验证码输入错误");
            img_code.setImageBitmap(CodeUtil.getInstance().createBitmap());
            edit_code.setText("");
            return false;
        }
        else if (edit_code.equals("") ) {
            toastS("验证码错误");
            return false;
        } else {
            return true;
        }
    }

    private void initView() {
        img_code.setImageBitmap(CodeUtil.getInstance().createBitmap());
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
//        System.out.println("验证码：" + CodeUtil.getInstance().getCode());
    }

    /**
     * 判断用户是否输入账号，密码
     */
    public void LoginSuccess() {

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
            SkipActivity(MainActivity.class);
            this.finish();
        }
//        startProgressDialog();
//        loginAction(username, password);

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
        public void onTick(long millisUntilFinished){//计时过程显示
            btn_getcode.setClickable(false);
            btn_getcode.setText(millisUntilFinished /1000+"s");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
