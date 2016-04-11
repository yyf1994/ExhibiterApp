/**
 * created by jiang, 12/3/15
 * Copyright (c) 2015, jyuesong@gmail.com All Rights Reserved.
 * *                #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */

package com.eastfair.exhibiterapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiang on 12/3/15.
 * 根据当前权限进行判断相关的滑动逻辑
 */
public abstract class ContactAdapter<T> extends RecyclerView.Adapter<MyRecyclerviewHolder>
        implements StickyRecyclerHeadersAdapter<MyRecyclerviewHolder> {
    private Context context;
    private List<T> mDatas;
    protected LayoutInflater mInflater;
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    public ContactAdapter(Context ctx,List<T> datas) {
        mDatas = (datas != null) ? datas : new ArrayList<T>();
        context = ctx;
        mInflater = LayoutInflater.from(ctx);
    }


    @Override
    public MyRecyclerviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final MyRecyclerviewHolder holder = new MyRecyclerviewHolder(context, mInflater.inflate(getItemLayoutId(viewType), parent, false));
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerviewHolder holder, int position) {
        bindData(holder, position, mDatas.get(position));
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public long getHeaderId(int position) {
        return getHeaderIds(position);
    }

    @Override
    public MyRecyclerviewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_header, parent, false);
        return new MyRecyclerviewHolder(context,view);
    }

    @Override
    public void onBindHeaderViewHolder(MyRecyclerviewHolder holder, int position) {
        BindHeaderViewHolder(holder,position);
    }

    public void add(int pos, T item) {
        mDatas.add(pos, item);
        notifyItemInserted(pos);
    }

    public void delete(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }


    public int getPositionForSection(char section) {

        return PositionForSection(section);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        mClickListener = listener;

    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {

        mLongClickListener = listener;

    }

    abstract public int getItemLayoutId(int viewType);

    abstract public void bindData(MyRecyclerviewHolder holder, int position, T item);

    abstract public long getHeaderIds( int position);

    abstract public void BindHeaderViewHolder(MyRecyclerviewHolder holder, int position);
    abstract public int PositionForSection(char section);


    public interface OnItemClickListener {

        public void onItemClick(View itemView, int pos);

    }

    public interface OnItemLongClickListener {

        public void onItemLongClick(View itemView, int pos);

    }
}
