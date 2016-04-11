package com.eastfair.exhibiterapp.ui.activity.exhibitors;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ExhibitorsSearchActivity extends BaseActivity implements View.OnClickListener {
    private ExhibitorsSearchActivity mySelf() {
        return ExhibitorsSearchActivity.this;
    }

    /**
     * 展商搜索界面
     */
    private Toolbar toolbar_search;
    private EditText edit_search;
    private ImageView img_search;
    private TextView tv_searchresult;
    private SupportRecyclerView recyclerView;
    private MyRecyclerviewAdapter mAdapter;
    private List<String> mData;

    @Override
    public void findViews() {
        setContentView(R.layout.activity_exhibitors_search);
        initView();
    }

    @Override
    public void registerEvents() {
        img_search.setOnClickListener(this);
    }

    @Override
    public void init() {

    }


    private void initView() {
        edit_search = (EditText) findViewById(R.id.edit_search);
        img_search = (ImageView) findViewById(R.id.img_search);
        tv_searchresult = (TextView) findViewById(R.id.tv_searchresult);
        toolbar_search = (Toolbar) findViewById(R.id.toolbar_search);
        recyclerView = (SupportRecyclerView) findViewById(R.id.rv_searchresult);
        setSupportActionBar(toolbar_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 搜索
             */
            case R.id.img_search:
                tv_searchresult.setVisibility(View.VISIBLE);
                tv_searchresult.setText("查询出3条数据");
               getData();
                break;
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
