package com.eastfair.exhibitorapp.login.model;

/**
 * Created by yyf on 2016/5/9.
 */
public class LoginHttp {
    private LoginHttp(){};

    private static class InstanceHolder{
        private static final LoginHttp instance = new LoginHttp();
    }

    public static LoginHttp getInstance()
    {
        return InstanceHolder.instance;
    }

    /**
     * 模拟网络请求
     * @param exhibitor
     * @param code
     * @return
     */
    public boolean httpLogin(String exhibitor, String code) {
        if(exhibitor.equals("北京") && code.equals("123456"))
        {
            return true;
        }

        return false;
    }
}
