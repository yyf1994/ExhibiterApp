package com.eastfair.exhibiterapp.login.presenter;

import android.text.TextUtils;

import com.eastfair.exhibiterapp.login.LoginContract;
import com.eastfair.exhibiterapp.login.model.LoginHttp;
import com.eastfair.exhibiterapp.util.RegexChk;

/**
 * Created by yyf on 2016/5/9.
 */
public class LoginPresenter implements LoginContract.Present {
    private final LoginContract.View mView;

    public LoginPresenter(LoginContract.View view)
    {
        this.mView = view;

        //我这里直接把activity作为view，所以不需要
        //mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
    /**
     * 登录参数校验
     *
     * @return
     */
    private boolean validator() {
        RegexChk cRegexChk = new RegexChk();
        if (TextUtils.isEmpty(mView.getPhoneNum())) {
            mView.loginError("请输入手机号");
            return false;
        }

        if (TextUtils.isEmpty(mView.getEditImgCode())) {
            mView.loginError("请输入图片验证码");
            return false;
        }
        if (TextUtils.isEmpty(mView.getCode())) {
            mView.loginError("请输入验证码");
            return false;
        }
        if(cRegexChk.checkPhone(mView.getPhoneNum()) == false){
            mView.loginError("手机号不合法，请重新输入");
            return false;
        }
        if(!mView.getEditImgCode().equalsIgnoreCase(mView.getImageCode())){
            mView.loginError("图片验证码输入错误");
            mView.setImgCode();
            return false;
        }
        return true;
    }

    @Override
    public void login() {
        if(!validator())
        {
            return;
        }

        boolean result = LoginHttp.getInstance().httpLogin(mView.getPhoneNum(), mView.getCode());

        if(result){
            mView.loginSuccess();
        }else {
            mView.loginError("请检查输入内容是否有误");
        }
    }

}
