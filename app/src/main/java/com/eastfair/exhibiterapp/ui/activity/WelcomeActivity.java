package com.eastfair.exhibiterapp.ui.activity;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.android.preference.PreferencesUtils;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.MyPagerAdapter;
import com.eastfair.exhibiterapp.base.BaseActivity;

public class WelcomeActivity extends BaseActivity implements OnClickListener,
		OnPageChangeListener {
	ImageView imageView_enter;
	private ViewPager vPager;
	private LinearLayout linearLayout;
	private List<View> pageViews;
	private List<ImageView> dots;
	private int lastPosition;
	Intent intent;

	@Override
	public void findViews() {
		setContentView(R.layout.welcome_five);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		vPager = (ViewPager) findViewById(R.id.vp);
		imageView_enter = (ImageView) findViewById(R.id.imageView_enter);
		imageView_enter.setOnClickListener(this);
		// 存储界面的容器
		pageViews = new ArrayList<View>();
		// 存储图片的界面
		dots = new ArrayList<ImageView>();
		// 添加5个界面
		for (int i = 1; i < 4; i++) {
			View view = this.getLayoutInflater().inflate(R.layout.page_view,
					null);
			/**
			 * 根据不同的Language选择不同的语言，显示不同的引导图片
			 */
			/*String language = PreferencesUtils.getString(WelcomeActivity.this,
					"language");
			if (language != null && language.equals("en")) {
				((ImageView) view.findViewById(R.id.iv))
						.setImageResource(getResources().getIdentifier(
								"welcomeen" + i, "drawable", getPackageName()));
			} else if (language != null && language.equals("cn")) {
				// 设置背景颜色----获得资源文件下的图片--（图片，"drawable"，包名）
				((ImageView) view.findViewById(R.id.iv))
						.setImageResource(getResources().getIdentifier(
								"welcomezh" + i, "drawable", getPackageName()));
			}*/
			((ImageView) view.findViewById(R.id.iv))
					.setImageResource(getResources().getIdentifier(
							"welcomezh" + i, "mipmap", getPackageName()));
			pageViews.add(view);
		}
		vPager.setAdapter(new MyPagerAdapter(pageViews));
		vPager.setOnPageChangeListener(this);// TODO Auto-generated method stub

	}

	@Override
	public void registerEvents() {
		linearLayout.setOnClickListener(this);

	}

	@Override
	public void init() {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/**
		 * 要跳转的界面
		 */
		case R.id.linearLayout:
			// 标记第一次进入程序时候进入引导页后，把标示存储数据库，判别下次logo界面直接跳转主界面
			PreferencesUtils.putString(WelcomeActivity.this, "welcome", "welcome");
			SkipActivity(MainActivity.class);
			finish();
			break;
		}
	}
	/**
	 * viewpage监听
	 */
	@Override
	public void onPageSelected(int position) {
		if (position == 2) {
			linearLayout.setVisibility(ViewPager.VISIBLE);
			//http://appjf.eastfair.com/bsd18_cn/使用帮助.html
		}
		if (position == 1) {
			linearLayout.setVisibility(ViewPager.GONE);
		}
		if (position == 0) {
			linearLayout.setVisibility(ViewPager.GONE);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
