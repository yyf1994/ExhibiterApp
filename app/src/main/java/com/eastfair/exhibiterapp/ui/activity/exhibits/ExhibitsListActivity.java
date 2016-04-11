package com.eastfair.exhibiterapp.ui.activity.exhibits;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

public class ExhibitsListActivity extends BaseActivity implements View.OnClickListener {
    private ExhibitsListActivity mySelf() {
        return ExhibitsListActivity.this;
    }

    /**
     * 展品列表
     */
    private SupportRecyclerView recyclerView;
    private MyRecyclerviewAdapter mAdapter;
    private List<String> mData;
    private Toolbar toolbar_title;
    private TextView text_Title;


    @Override
    public void findViews() {
        setContentView(R.layout.activity_exhibitslist);
        initView();
    }

    @Override
    public void registerEvents() {

    }

    @Override
    public void init() {

    }


    private void initView() {
        recyclerView = (SupportRecyclerView) findViewById(R.id.rv_exhibitslist);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ExhibitsListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                ExhibitsListActivity.this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setHasFixedSize(true);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("展品列表");
    }
    private void getData() {
        mData = new ArrayList<>();
        for(int i = 0;i<100;i++){
            mData.add("data"+i);
        }
        if(mData == null){
            recyclerView.setEmptyView(findViewById(R.id.empty_view));
        }
        mAdapter = new MyRecyclerviewAdapter(ExhibitsListActivity.this,mData){

            @Override
            public void onBindViewHolder(MyRecyclerviewHolder holder, int position) {
                bindData(holder, position, mData.get(position));
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.exhibits_item;
            }

            @Override
            public void bindData(MyRecyclerviewHolder holder, int position, Object item) {
                if(item ==null){
                    return;
                }
                holder.setText(R.id.tv_zpname,  item.toString());
                holder.setText(R.id.tv_zpproperty, item.toString());
                holder.setText(R.id.tv_gsname, item.toString());
//                holder.setImageView(R.id.image,item.toString());
            }
        };
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(ExhibitsListActivity.this, "click " + pos, Toast.LENGTH_SHORT).show();
                SkipActivity(ExhibitsDetailActivity.class);
            }
        });

        mAdapter.setOnItemLongClickListener(new MyRecyclerviewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int pos) {
                Toast.makeText(ExhibitsListActivity.this, "clickLONG " + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
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
