package com.eastfair.exhibitorapp.exhibits;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewAdapter;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibitorapp.base.BaseActivity;
import com.eastfair.exhibitorapp.weight.RecycleViewDivider;
import com.eastfair.exhibitorapp.weight.SupportRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExhibitsListActivity extends BaseActivity {

    /**
     * 展品列表
     */
    @Bind(R.id.rv_exhibitslist)
    SupportRecyclerView recyclerView;
    @Bind(R.id.exhibitslist_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.img_title)
    ImageView img_addexhibit;
     private MyRecyclerviewAdapter mAdapter;

    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitslist);
        ButterKnife.bind(this);
        initView();
        setListener();
    }


    private void initView() {
        getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ExhibitsListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                ExhibitsListActivity.this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setHasFixedSize(true);
        text_Title.setText("展品列表");
        img_addexhibit.setImageResource(R.mipmap.faxuqiu);
        toolbar_title.setNavigationIcon(R.mipmap.back);

    }
    private void setListener() {
        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
    /**
     * 添加展品
     */
    @OnClick(R.id.img_title)
    public void addexhibit() {
        SkipActivity(AddExhibitsActivity.class);
        finish();
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
                holder.setBoldText(R.id.tv_zpname,  item.toString());
                holder.setText(R.id.tv_zpproperty, item.toString());
                holder.setText(R.id.tv_gsname, item.toString());
//                holder.setImageView(R.id.image,item.toString());
            }
        };
        recyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
