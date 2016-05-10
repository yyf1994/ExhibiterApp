package com.eastfair.exhibitorapp.login;


import com.eastfair.exhibitorapp.base.BasePresenter;
import com.eastfair.exhibitorapp.base.BaseView;

/**
 * Created by yyf on 2016/5/9.
 */
public class LoginContract {

    public interface View extends BaseView<Present> {
        void loginError(String msg);

        void loginSuccess();

        String getExhibitor();

        String getCode();//验证码

    }

    public interface Present extends BasePresenter {
        void login();//登陆
    }
}
