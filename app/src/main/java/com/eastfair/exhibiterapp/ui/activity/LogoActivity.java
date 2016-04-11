package com.eastfair.exhibiterapp.ui.activity;


import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;

/**
 * 启动图
 */
public class LogoActivity extends BaseActivity {
    private ImageView logo_imageview;
    private View view;

    @Override
    public void findViews() {
        setContentView(R.layout.activity_logo);
        view = findViewById(R.id.view);
        logo_imageview = (ImageView) findViewById(R.id.logo_imageview);
        /**
         * 渐变动画
         */
        view.setAnimation(AnimationUtils.loadAnimation(LogoActivity.this,
                R.anim.two_in));


    }

    @Override
    public void registerEvents() {

    }

    @Override
    public void init() {

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


}
