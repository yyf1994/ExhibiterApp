package com.eastfair.exhibitorapp.login;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;
import com.eastfair.exhibitorapp.main.MainActivity;
import com.eastfair.exhibitorapp.welcome.WelcomeActivity;
import com.eastfair.exhibitorapp.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    /**
     * 登陆界面
     */
    @Bind(R.id.actv_zhanshang)
    AutoCompleteTextView actv_zhanshang;
    @Bind(R.id.edit_code)
    EditText edit_code;
    @Bind(R.id.tv_call)
    TextView tv_call;
    @Bind(R.id.btn_login)
    Button btn_login;

    private boolean isFirst;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

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

    @OnClick(R.id.tv_call)
    public void call() {
        CallPhoneNum("tel:10086");
    }

    /**
     * 判断身份验证码
     */
    public boolean getUserEdtext() {

        return true;
    }

    private void initView() {
        //数据
        data_list = new ArrayList<String>();
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, data_list);
        //加载适配器
        actv_zhanshang.setAdapter(arr_adapter);
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
            //如果展商详情完整，则显示主页面
            SkipActivity(MainActivity.class);
            //如果展商详情不完整，则显示完善页面
//            SkipActivity(InfoActivity.class);
            this.finish();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
