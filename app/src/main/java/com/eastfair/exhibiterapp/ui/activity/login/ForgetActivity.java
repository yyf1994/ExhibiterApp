package com.eastfair.exhibiterapp.ui.activity.login;

import android.os.Bundle;
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
import com.eastfair.exhibiterapp.util.RegexChk;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码界面---验证码
 */
public class ForgetActivity extends BaseActivity{
    /**
     * 填写手机号，输入验证码
     */
    @Bind(R.id.edit_phonenum)
    EditText ed_tel;
    @Bind(R.id.edit_code)
    EditText ed_code;

    @Bind(R.id.btn_yanzhengma)
    Button btn_code;
    @Bind(R.id.btn_next)
    Button btn_next;//验证码倒计时
    /**
     * 根据不同的布局设置不同的显示方式
     * */
    @Bind(R.id.rvlayout_phonenum)
    RelativeLayout rl_phonenum;
    @Bind(R.id.rvlayout_getphonenum)
    RelativeLayout  rl_getphonenum;
    @Bind(R.id.text_getphonenum)
    TextView text_phonenum;
    @Bind(R.id.toolbar_title)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title ;

    private TimeCount time;//倒计时

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        initView();
        init();
    }

    public void init() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
//        if (countdown.isRunning()) {
//            tv_code_time.setEnabled(false);
//        }
    }

    @OnClick(R.id.btn_next)
    public void next(){
        if (getUserEdtext() == true) {
            toastS("提交成功");
//                    accountTel = ed_tel.getText().toString();
            SkipActivity(NewPassActivity.class);
        }
    }

    @OnClick(R.id.btn_yanzhengma)
    public void getcode(){
        if (getUserTel() == true) {
            time.start();//开始计时
//                    networkDataCode(ed_tel.getText().toString());
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
        if (tel.equals("")
                || cRegexChk.checkPhone(tel) == false) {

//            ViewAnimationUtils.shake(ed_tel);
            toastS("手机号不合法，请重新输入");
            return false;
        }
//        else if (ed_code.equals("") || !ed_code.getText().toString().equals(codeNew)) {
        else if (ed_code.equals("") ) {
//            ViewAnimationUtils.shake(ed_code);
            toastS("验证码错误");
            return false;
        } else {
            return true;
        }
    }
    /**
     * 获取验证码--成功后倒计时-- countdown.start();
     */
   /* private void networkDataCode(String code) {
        startProgressDialog();
        Map<String, String> map = new HashMap<String, String>();
        map.put("request", "4");
        map.put("Mobile", code);
        PostJsonRequestWith<RegisterCodeEntity> userRequestPost = new PostJsonRequestWith<RegisterCodeEntity>(
                RAMStorage.urlSystem, map, RegisterCodeEntity.class, new Response.Listener<RegisterCodeEntity>() {
            @Override
            public void onResponse(RegisterCodeEntity response) {

                Log.e("TAG", response.toString());
                if (response.getStatus().equals("1")) {
                        if (response.getSendState().equals("1")) {
                            codeNew = response.getCode();
                           // toastS("code"+codeNew);
                            countdown.start();
                            toastS("发送成功");
                        }else if(response.getSendState().equals("0")){
                            toastS("发送失败");
                        }else if (response.getSendState().equals("2")) {
                            toastS("此用户不存在");
                        }
                } else {
                    toastS(response.getErrorMsg());

                }
                stopProgressDialog();
            }
        }, null, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.toString());
                toastSError();
                stopProgressDialog();

            }
        });
        ApplicationController.getInstance().addToRequestQueue(
                userRequestPost);
    }*/

    private void initView() {
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("忘记密码");
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
            btn_code.setText(millisUntilFinished /1000+"s");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

