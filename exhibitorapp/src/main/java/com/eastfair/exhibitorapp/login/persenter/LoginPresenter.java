package com.eastfair.exhibitorapp.login.persenter;

import android.text.TextUtils;

import com.eastfair.exhibitorapp.login.LoginContract;
import com.eastfair.exhibitorapp.login.model.LoginHttp;


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
        if (TextUtils.isEmpty(mView.getExhibitor())) {
            mView.loginError("请选择展商");
            return false;
        }

        if (TextUtils.isEmpty(mView.getCode())) {
            mView.loginError("请输入验证码");
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

        boolean result = LoginHttp.getInstance().httpLogin(mView.getExhibitor(), mView.getCode());

        if(result){
            mView.loginSuccess();
        }else {
            mView.loginError("展商或验证码有误");
        }
    }

}
