package com.eastfair.exhibitorapp.welcome;


import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;
import com.eastfair.exhibitorapp.login.view.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 启动图
 */
public class LogoActivity extends BaseActivity {

    @Bind(R.id.logo_imageview)
     ImageView logoImageview;
    @Bind(R.id.view)
     LinearLayout view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_logo);
        ButterKnife.bind(this);
        /**
         * 渐变动画
         */
        view.setAnimation(AnimationUtils.loadAnimation(LogoActivity.this,
                R.anim.two_in));
        initHandle();
    }

    /**
     * 2秒自动跳转
     */

    private void initHandle() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                SkipActivity(LoginActivity.class);
                finish();
            }
        }, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
