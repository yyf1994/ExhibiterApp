package com.eastfair.exhibiterapp.ui.activity.me;

import android.os.Bundle;
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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的红包
 */
public class MyRedPackageActivity extends BaseActivity {

    @Bind(R.id.rv_redpackage)
    SupportRecyclerView recyclerView;
    @Bind(R.id.toolbar_title)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.text_redpackage)
    TextView text_redpackage;

    private MyRecyclerviewAdapter mAdapter;
    private List<String> mData;

    //List数据
    private List<String> title = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myredpackage);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        text_redpackage.setText("您共有5个红包");
        getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyRedPackageActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                MyRedPackageActivity.this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setHasFixedSize(true);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("我的红包");
    }

    private void getData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("data" + i);
        }
        if (mData == null) {
            recyclerView.setEmptyView(findViewById(R.id.empty_view));
        }
        mAdapter = new MyRecyclerviewAdapter(MyRedPackageActivity.this, mData) {

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
                if (item == null) {
                    return;
                }
                holder.setText(R.id.tv_qiyeyangpin, item.toString());
                holder.setText(R.id.tv_time, item.toString());
                holder.setText(R.id.tv_gsname, item.toString());
//                holder.setImageView(R.id.image,item.toString());
                //按钮的点击事件
                holder.setClickListener(R.id.btn_lingqu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MyRedPackageActivity.this, "lingqu", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

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
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}