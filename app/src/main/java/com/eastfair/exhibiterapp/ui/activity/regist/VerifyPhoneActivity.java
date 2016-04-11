package com.eastfair.exhibiterapp.ui.activity.regist;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.ui.activity.MainActivity;
import com.eastfair.exhibiterapp.ui.activity.NewPassActivity;
import com.eastfair.exhibiterapp.util.RegexChk;

/**
 * 验证手机
 */
public class VerifyPhoneActivity extends BaseActivity implements View.OnClickListener{
    /**
     * 填写手机号，输入验证码
     */
    private EditText ed_tel, ed_code;

    private Button btn_code,btn_next;//验证码倒计时

    /**
     * 根据不同的布局设置不同的显示方式
     * verifytag = 1,有手机号，进入验证手机页面，直接发送验证码
     * verifytag = 2,无手机号，进入验证手机页面，需要填写手机号码，然后再发送验证码
     * */
    private RelativeLayout rl_phonenum,rl_getphonenum;

    private TextView text_phonenum;

    private Toolbar toolbar_title;
    private TextView text_Title ;

    private TimeCount time;//倒计时
    private String verifytag;

    @Override
    public void findViews() {
        setContentView(R.layout.activity_forget);
        initView();

    }

    @Override
    public void registerEvents() {
        btn_code.setOnClickListener(this);
        btn_next.setOnClickListener(this);

    }

    @Override
    public void init() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        if(verifytag.equals("1")){
            time.start();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 点击验证码按钮
             */
            case R.id.btn_yanzhengma:
                /**
                 * 点击获取验证码--判断手机--请求获取验证码的接口--成功--验证码倒计时
                 */
                if(verifytag.equals("2")){
                    if (getUserTel() == true) {
                        time.start();//开始计时
//                    networkDataCode(ed_tel.getText().toString());
                    }
                }else{
                    time.start();//开始计时
                }

                break;
            /**
             * 点击下一步
             */
            case R.id.btn_next:

                if (getUserEdtext() == true) {
//                    accountTel = ed_tel.getText().toString();
                    if(verifytag.equals("1")){
                        SkipActivity(MainActivity.class);
                    }else{
                        Intent intent = new Intent(VerifyPhoneActivity.this, CardInfoActivity.class);
                        /**
                         * verifytag = 1,有手机号，进入验证手机页面，直接发送验证码
                         * verifytag = 2,无手机号，进入验证手机页面，需要填写手机号码，然后再发送验证码
                         * */
                        intent.putExtra("verifytag", "2");
                        startActivity(intent);
                    }
                    finish();
                }

                break;
        }
    }

    /**
     * 判断用户是否输入手机号
     */
    public boolean getUserTel() {

        String tel = ed_tel.getText().toString();
        RegexChk cRegexChk = new RegexChk();
        if (tel.equals("")
                || cRegexChk.checkPhone(tel) == false) {
            ed_tel.setText("");
//            ViewAnimationUtils.shake(ed_tel);
            toastS("手机号不合法，请重新输入");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断用户是否输入手机号,和验证码
     */
    public boolean getUserEdtext() {

        String tel = ed_tel.getText().toString();
        RegexChk cRegexChk = new RegexChk();
        if(verifytag.equals("2")){
            if (tel.equals("") || cRegexChk.checkPhone(tel) == false) {
//            ViewAnimationUtils.shake(ed_tel);
                toastS("手机号不合法，请重新输入");
                return false;
            }
        }
        if (ed_code.equals("") ) {
//            ViewAnimationUtils.shake(ed_code);
            toastS("验证码错误");
            return false;
        } else {
            return true;
        }
    }

    private void initView() {

        Intent intent = getIntent();
        verifytag = intent.getExtras().getString("verifytag");
        text_phonenum = (TextView) findViewById(R.id.text_getphonenum);
        ed_tel = (EditText) findViewById(R.id.edit_phonenum);
        ed_code = (EditText) findViewById(R.id.edit_code);
        btn_code = (Button) findViewById(R.id.btn_yanzhengma);
        btn_next = (Button) findViewById(R.id.btn_next);
        rl_phonenum = (RelativeLayout) findViewById(R.id.rvlayout_phonenum);
        rl_getphonenum = (RelativeLayout) findViewById(R.id.rvlayout_getphonenum);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("验证手机");

        if(verifytag.equals("1")){
            rl_phonenum.setVisibility(View.GONE);
            rl_getphonenum.setVisibility(View.VISIBLE);
            btn_next.setText("立即体验");
        }else if(verifytag.equals("2")){
            rl_phonenum.setVisibility(View.VISIBLE);
            rl_getphonenum.setVisibility(View.GONE);
            btn_next.setText("下一步");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {//计时完毕时触发
            btn_code.setText("重新获取");
            btn_code.setClickable(true);
        }
        @Override
        public void onTick(long millisUntilFinished){//计时过程显示
            btn_code.setClickable(false);
            btn_code.setText(millisUntilFinished /1000+" /s");
        }
    }
}

