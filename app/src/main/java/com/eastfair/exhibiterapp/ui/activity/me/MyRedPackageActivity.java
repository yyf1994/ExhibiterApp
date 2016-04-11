package com.eastfair.exhibiterapp.ui.activity.me;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.MyRecyclerviewAdapter;
import com.eastfair.exhibiterapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.ui.activity.DetailActivity;
import com.eastfair.exhibiterapp.weight.RecycleViewDivider;
import com.eastfair.exhibiterapp.weight.SupportRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的红包
 * */
public class MyRedPackageActivity extends BaseActivity implements View.OnClickListener {

    private SupportRecyclerView recyclerView;
    private MyRecyclerviewAdapter mAdapter;
    private Toolbar toolbar_title;
    private TextView text_Title ;
    private List<String> mData;
    private TextView text_redpackage ;
    //支持下拉刷新的ViewGroup
//    private PtrClassicFrameLayout mPtrFrame;
    //List数据
    private List<String> title = new ArrayList<>();
    //添加Header和Footer的封装类
//    private RecyclerAdapterWithHF mhfAdapter;

//    private Button btn_get;

    private MyRedPackageActivity mySelf() {
        return MyRedPackageActivity.this;
    }

    @Override
    public void findViews() {
        setContentView(R.layout.activity_myredpackage);
        initView();
    }

    @Override
    public void registerEvents() {
//        btn_get.setOnClickListener(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lingqu:
//                Toast.makeText(MyRedPackageActivity.this,"lingqu",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void initView() {

        recyclerView = (SupportRecyclerView) findViewById(R.id.rv_redpackage);
//        btn_get = (Button) findViewById(R.id.btn_lingqu);
        text_redpackage = (TextView) findViewById(R.id.text_redpackage);
        text_redpackage.setText("您共有5个红包");
        getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyRedPackageActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                MyRedPackageActivity.this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setHasFixedSize(true);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("我的红包");
    }

    private void getData() {
        mData = new ArrayList<>();
        for(int i = 0;i<100;i++){
            mData.add("data"+i);
        }
        if(mData == null){
            recyclerView.setEmptyView(findViewById(R.id.empty_view));
        }
        mAdapter = new MyRecyclerviewAdapter(MyRedPackageActivity.this,mData){

            @Override
            public void onBindViewHolder(MyRecyclerviewHolder holder, int position) {
                bindData(holder, position, mData.get(position));
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.rv_redpackage_item;
            }

            @Override
            public void bindData(MyRecyclerviewHolder holder, int position, Object item) {
                if(item ==null){
                    return;
                }
                holder.setText(R.id.tv_qiyeyangpin,  item.toString());
                holder.setText(R.id.tv_time, item.toString());
                holder.setText(R.id.tv_gsname, item.toString());
//                holder.setImageView(R.id.image,item.toString());
                //按钮的点击事件
                holder.setClickListener(R.id.btn_lingqu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MyRedPackageActivity.this,"lingqu",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

//        mhfAdapter = new RecyclerAdapterWithHF(mAdapter);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(MyRedPackageActivity.this, "click " + pos, Toast.LENGTH_SHORT).show();
                SkipActivity(DetailActivity.class);
            }
        });

        mAdapter.setOnItemLongClickListener(new MyRecyclerviewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int pos) {
                Toast.makeText(MyRedPackageActivity.this, "clickLONG " + pos, Toast.LENGTH_SHORT).show();
            }
        });

     /*   mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
        //下拉刷新支持时间
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        //下拉刷新一些设置 详情参考文档
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        //进入Activity就进行自动下拉刷新
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);

        //下拉刷新
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {


            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                title.clear();
                //模拟数据
                for (int i = 0; i <= 5; i++) {
                    title.add(String.valueOf("下拉刷新"+i));
                }
                //模拟联网 延迟更新列表
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mPtrFrame.refreshComplete();
                        mPtrFrame.setLoadMoreEnable(true);

                    }
                }, 1000);


            }
        });

        //上拉加载
        mPtrFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {

                //模拟联网延迟更新数据
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        //模拟数据
                        for (int i = 0; i <= 5; i++) {
                            title.add(String.valueOf("加载更多"+i));
                        }
                        mAdapter.notifyDataSetChanged();
                        mPtrFrame.loadMoreComplete(true);
                        Toast.makeText(MyRedPackageActivity.this, "load more complete", Toast.LENGTH_SHORT)
                                .show();
                    }
                }, 1000);
            }
        });*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}