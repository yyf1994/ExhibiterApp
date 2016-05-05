package com.eastfair.exhibitorapp.ui.activity.exhibitors;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewAdapter;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibitorapp.base.BaseActivity;
import com.eastfair.exhibitorapp.ui.activity.DetailActivity;
import com.eastfair.exhibitorapp.weight.RecycleViewDivider;
import com.eastfair.exhibitorapp.weight.SupportRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExhibitorsSearchActivity extends BaseActivity {

    /**
     * 展商搜索界面
     */
    @Bind(R.id.toolbar_search)
     Toolbar toolbar_search;
    @Bind(R.id.edit_search)
     EditText edit_search;
    @Bind(R.id.img_search)
     ImageView img_search;
    @Bind(R.id.tv_searchresult)
     TextView tv_searchresult;
    @Bind(R.id.rv_searchresult)
    SupportRecyclerView recyclerView;

    private MyRecyclerviewAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitors_search);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        setSupportActionBar(toolbar_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_search.setNavigationIcon(R.mipmap.back);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setHasFixedSize(true);

        edit_search.setHint("请输入展商关键字");
        tv_searchresult.setVisibility(View.GONE);

    }

    private void getData() {
        mData = new ArrayList<>();

        for(int i = 0;i<10;i++){
            mData.add("data"+i);
        }
        setUI();
    }

    private void setUI() {
        if(mData == null){
            recyclerView.setEmptyView(findViewById(R.id.empty_view));
        }
        mAdapter = new MyRecyclerviewAdapter(this,mData){

            @Override
            public void onBindViewHolder(MyRecyclerviewHolder holder, int position) {
                bindData(holder, position, mData.get(position));
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_contact1;
            }

            @Override
            public void bindData(MyRecyclerviewHolder holder, int position, Object item) {
                if(item ==null){
                    return;
                }
                holder.setText(R.id.tv_gsname, item.toString());
                holder.setText(R.id.tv_zhanweihao, item.toString());
//                holder.setImageView(R.id.image,item.toString());
            }
        };
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(ExhibitorsSearchActivity.this, "click " + pos, Toast.LENGTH_SHORT).show();
                SkipActivity(DetailActivity.class);
            }
        });

        mAdapter.setOnItemLongClickListener(new MyRecyclerviewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int pos) {
                Toast.makeText(ExhibitorsSearchActivity.this, "clickLONG " + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 展商人员按钮点击事件
     */
    @OnClick(R.id.img_search)
    public void search() {
        tv_searchresult.setVisibility(View.VISIBLE);
        tv_searchresult.setText("查询出3条数据");
        getData();
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
