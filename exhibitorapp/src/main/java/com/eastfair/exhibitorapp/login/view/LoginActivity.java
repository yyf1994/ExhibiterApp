package com.eastfair.exhibitorapp.login.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;
import com.eastfair.exhibitorapp.login.LoginContract;
import com.eastfair.exhibitorapp.login.persenter.LoginPresenter;
import com.eastfair.exhibitorapp.main.MainActivity;
import com.eastfair.exhibitorapp.welcome.WelcomeActivity;
import com.eastfair.exhibitorapp.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View{


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
    private LoginContract.Present mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initParams();
    }
    private void initParams() {
        mPresent = new LoginPresenter(this);
    }
    @OnClick(R.id.btn_login)
    public void login() {
        mPresent.login();
    }

    @OnClick(R.id.tv_call)
    public void call() {
        CallPhoneNum("tel:10086");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
            //如果展商详情完整，则显示主页面
            SkipActivity(MainActivity.class);
            //如果展商详情不完整，则显示完善页面
//            SkipActivity(InfoActivity.class);
            this.finish();
        }
    }

    @Override
    public String getExhibitor() {
        return actv_zhanshang.getText().toString();
    }

    @Override
    public String getCode() {
        return edit_code.getText().toString();
    }

    @Override
    public void setPresenter(LoginContract.Present presenter) {

    }
}
