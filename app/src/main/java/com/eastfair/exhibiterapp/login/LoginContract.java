package com.eastfair.exhibiterapp.login;

import com.eastfair.exhibiterapp.base.BasePresenter;
import com.eastfair.exhibiterapp.base.BaseView;

/**
 * Created by yyf on 2016/5/9.
 */
public class LoginContract {

    public interface View extends BaseView<Present> {
        void loginError(String msg);

        void loginSuccess();

        String getPhoneNum();

        String getEditImgCode();//编辑框中的图片验证码

        String getImageCode();//imageview中的验证码

        String getCode();//验证码

        boolean getUserTel();

        void setImgCode();//设置验证码
    }

    public interface Present extends BasePresenter {
        void login();//登陆
    }
}
