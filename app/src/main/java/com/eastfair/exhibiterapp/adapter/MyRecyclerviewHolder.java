package com.eastfair.exhibiterapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.util.GlideCircleTransform;

/**
 * Created by Administrator on 2016/3/18.
 */
public class MyRecyclerviewHolder<T> extends  RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private Context mContext;

    public MyRecyclerviewHolder(Context ctx, View itemView) {
        super(itemView);
        mContext = ctx;
        mViews = new SparseArray<View>();
    }

    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getView(int viewId) {
        return findViewById(viewId);
    }

    public TextView getTextView(int viewId) {
        return (TextView) getView(viewId);
    }

    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }

    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }

    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }

    public MyRecyclerviewHolder setBoldText(int viewId, String value) {
        TextView view = findViewById(viewId);
        //设置粗体
        TextPaint tp = view.getPaint();
        tp.setFakeBoldText(true);
        view.setText(value);
        return this;
    }

    public MyRecyclerviewHolder setText(int viewId, String value) {
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }

    public MyRecyclerviewHolder setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public MyRecyclerviewHolder setImageView(int viewId, String url) {
        ImageView view = findViewById(viewId);
        Glide.with(mContext).load(url)
                .transform(new GlideCircleTransform(mContext))//设置圆角
                .placeholder(R.mipmap.ic_launcher)//占位符
                .error(R.mipmap.ic_launcher)//错误占位符
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(view);
        return this;
    }

    public MyRecyclerviewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;     }

}

