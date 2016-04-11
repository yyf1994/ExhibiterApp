package com.eastfair.exhibiterapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eastfair.exhibiterapp.weight.ItemSlideHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public abstract class MessageAdapter<T> extends RecyclerView.Adapter<MyRecyclerviewHolder> implements ItemSlideHelper.Callback{

    private Context context;
    private List<T> mDatas;
    protected LayoutInflater mInflater;
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;
    private RecyclerView mRecyclerView;

    public MessageAdapter(Context ctx, List<T> datas) {
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
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));
    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {

        if(holder.itemView instanceof LinearLayout){
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            if(viewGroup.getChildCount() == 2){
                return viewGroup.getChildAt(1).getLayoutParams().width;
            }
        }


        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }

    public void add(int pos, T item) {
        mDatas.add(pos, item);
        notifyItemInserted(pos);
    }

    public void delete(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        mClickListener = listener;

    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {

        mLongClickListener = listener;

    }

    abstract public int getItemLayoutId(int viewType);

    abstract public void bindData(MyRecyclerviewHolder holder, int position, T item);

    public interface OnItemClickListener {

        public void onItemClick(View itemView, int pos);

    }

    public interface OnItemLongClickListener {

     public void onItemLongClick(View itemView, int pos);

}

}

