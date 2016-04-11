package com.eastfair.exhibiterapp.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.MyRecyclerviewAdapter;
import com.eastfair.exhibiterapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibiterapp.base.BaseFragment;
import com.eastfair.exhibiterapp.ui.activity.DetailActivity;
import com.eastfair.exhibiterapp.weight.RecycleViewDivider;
import com.eastfair.exhibiterapp.weight.SupportRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息界面
 */
public class MessageFragment extends BaseFragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private SupportRecyclerView recyclerView;
    private List<String> mData;
    //    private MessageAdapter mAdapter;
    private MyRecyclerviewAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int lastVisibleItem;

    public static MessageFragment newInstance(String param1) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MessageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");

        initView(view);

        return view;
    }

    private void initView(View view) {
        recyclerView = (SupportRecyclerView) view.findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        //设置recyclerview
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL));
        recyclerView.setHasFixedSize(true);

        //设置加载时进度条颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorPrimaryDark);

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        //加载数据
        getData1(view);
        //下拉刷新
        dropdownrefresh();

        //上拉加载
        upload();


    }

    /**
     * 下拉刷新
     */
    private void dropdownrefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {//下拉触发的函数，这里是谁1s然后加入一个数据，然后更新界面
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mData.add(0, "item:refresh...");
                        handler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });
    }

    /**
     * 上拉加载
     */
    private void upload() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {

                    new Thread(new Runnable() {//下拉触发的函数，这里是谁1s然后加入一个数据，然后更新界面
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for (int i = 0; i < 5; i++) {
                                int index = i + 1;
                                mData.add("more item" + index);
                            }
                            handler.sendEmptyMessage(0);
                        }
                    }).start();
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    /**
     * 上拉加载
     */
    private void upload1() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
//                    mAdapter.changeMoreStatus(LOADING_MORE);
                    new Thread(new Runnable() {//下拉触发的函数，这里是谁1s然后加入一个数据，然后更新界面
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for (int i = 0; i < 5; i++) {
                                int index = i + 1;
                                mData.add("more item" + index);
                            }
                            handler.sendEmptyMessage(1);
                        }
                    }).start();
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    private void getData1(View view) {
        mData = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mData.add("data" + i);
        }
        if (mData == null) {
            recyclerView.setEmptyView(view.findViewById(R.id.empty_view));
        }
        mAdapter = new MyRecyclerviewAdapter(getActivity(), mData) {

            @Override
            public void onBindViewHolder(MyRecyclerviewHolder holder, int position) {
                bindData(holder, position, mData.get(position));
            }

            @Override
            public int getItemLayoutId(int viewType) {
//                if (viewType == TYPE_ITEM) {
                    return R.layout.message_item;
//                } else if (viewType == TYPE_FOOTER) {
//                    return R.layout.recycler_load_more_layout;
//                }
//                return 1;
//                return viewType;
            }

       /*     @Override
            public int getItemViewType(int position) {
                if (position + 1 == getItemCount()) {
                    return TYPE_FOOTER;
                } else {
                    return TYPE_ITEM;
                }
            }*/

            @Override
            public void bindData(MyRecyclerviewHolder holder, int position, Object item) {
                if (item == null) {
                    return;
                }
                holder.setText(R.id.tv_gsname, item.toString());
                holder.setText(R.id.tv_miaoshu, item.toString());
                holder.setText(R.id.tv_time, item.toString());
//                holder.setImageView(R.id.image,item.toString());
                holder.setClickListener(R.id.tv_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "tv_delete", Toast.LENGTH_SHORT).show();
                    }
                });
//                switch (load_more_status) {
//                    case PULLUP_LOAD_MORE:
//                        holder.setText(R.id.foot_view_item_tv, "上拉加载更多...");
//                        break;
//                    case LOADING_MORE:
//                        holder.setText(R.id.foot_view_item_tv, "正在加载更多数据...");
//                        break;
//                }
            }
        };
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(getActivity(), "click " + pos, Toast.LENGTH_SHORT).show();
                SkipActivity(DetailActivity.class);
            }
        });

        mAdapter.setOnItemLongClickListener(new MyRecyclerviewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int pos) {
                Toast.makeText(getActivity(), "clickLONG " + pos, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private MyHandler handler = new MyHandler();

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    swipeRefreshLayout.setRefreshing(false);
                    mAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }
}
