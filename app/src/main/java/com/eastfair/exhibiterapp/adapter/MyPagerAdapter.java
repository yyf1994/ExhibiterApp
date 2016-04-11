package com.eastfair.exhibiterapp.adapter;
import java.util.List;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class MyPagerAdapter extends PagerAdapter {
	private List<View> pageViews;

	public MyPagerAdapter(List<View> pageViews) {
		this.pageViews = pageViews;
	}

	@Override
	public int getCount() {
		return pageViews.size();
	}

	@Override
	public boolean isViewFromObject(View v, Object obj) {
		return v.equals(obj);
	}

	@Override
	public int getItemPosition(Object obj) {
		return super.getItemPosition(obj);
	}

	@Override
	public void destroyItem(View v, int position, Object obj) {
		((ViewPager) v).removeView(pageViews.get(position));
	}

	@Override
	public Object instantiateItem(View v, int position) {
		((ViewPager) v).addView(pageViews.get(position));
		return pageViews.get(position);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {

	}

	@Override
	public void finishUpdate(View arg0) {

	}
}
