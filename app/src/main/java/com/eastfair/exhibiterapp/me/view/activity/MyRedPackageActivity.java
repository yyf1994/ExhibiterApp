package com.eastfair.exhibiterapp.me.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.MyRecyclerviewAdapter;
import com.eastfair.exhibiterapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.detail.view.DetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的红包
 */
public class MyRedPackageActivity extends BaseActivity {

    @Bind(R.id.rv_redpackage)
    RecyclerView recyclerView;
    @Bind(R.id.myredpackage_toolbar)
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
        recyclerView.setHasFixedSize(true);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar_title.setNavigationIcon(R.mipmap.back);
        text_Title.setText("我的红包");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);

    }

    private void getData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("data" + i);
        }
        final List imagelist = new ArrayList();
        Map<String,Object> image1 = new HashMap<>();
        image1.put("hongbao1",R.mipmap.hongbao1);
        image1.put("hongbao2",R.mipmap.hoongbao2);
        image1.put("hongbao3",R.mipmap.hongbao3);
        Map<String,Object> image2 = new HashMap<>();
        image1.put("hongbao1",R.mipmap.hongbao1);
        image1.put("hongbao2",R.mipmap.hoongbao2);
        Map<String,Object> image3 = new HashMap<>();
        image1.put("hongbao1",R.mipmap.hongbao1);
        for(int i = 0;i<mData.size();i++){
            imagelist.add(image1);
            if(i%3==0){

            }else if(i%3==1){
                imagelist.add(image1);
            }else if(i%3==2){
                imagelist.add(image2);
            }

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

                Random generator = new Random();
                Map<String,Object> map = (Map<String, Object>) imagelist.get(position);
                map.get("hongbao1");
//                holder.setBackground(R.id.layout_redpackage, (image.get(position))[generator.nextInt(position)]);

//                holder.setText(R.id.tv_exhibitorname, item.toString());
//                holder.setText(R.id.tv_exhibitsname, item.toString());
//                holder.setImageView(R.id.image,item.toString());
                //按钮的点击事件
//                holder.setClickListener(R.id.tv_lingqu, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MyRedPackageActivity.this, "lingqu", Toast.LENGTH_SHORT).show();
//                    }
//                });
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