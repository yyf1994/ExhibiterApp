package com.eastfair.exhibitorapp.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewAdapter;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibitorapp.base.BaseActivity;
import com.eastfair.exhibitorapp.model.Characters;
import com.eastfair.exhibitorapp.model.SectionCharacters;
import com.eastfair.exhibitorapp.net.NetWork;
import com.eastfair.exhibitorapp.service.ExhibiterService;
import com.eastfair.exhibitorapp.weight.RecycleViewDivider;
import com.eastfair.exhibitorapp.weight.SupportRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 现场红包界面
 */
public class RedPackageListActivity extends BaseActivity {

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerview)
    SupportRecyclerView recyclerView;

    @Bind(R.id.message_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.img_title)
    ImageView img_regist;


    private List<Characters> mData;
    private MyRecyclerviewAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int lastVisibleItem;
    private int mPageNum;
    private int mpageSize = 1;

    Call<SectionCharacters> call;

    public RedPackageListActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_message);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        //设置recyclerview
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, R.color.list_dicider_color));
        recyclerView.setHasFixedSize(true);

        //设置加载时进度条颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorPrimaryDark);

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        //初始化toolbar
        text_Title.setText("现场大红包");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        toolbar_title.setNavigationIcon(R.mipmap.back);
        toolbar_title.inflateMenu(R.menu.toolbar_menu);//设置右上角的填充菜单


        //设置adapter
        setAdapter();
        //初始化数据
        initData();
        //下拉刷新
        dropdownrefresh();
        //上拉加载
        upload();
        setListener();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }



    private void setListener() {

        mAdapter.setOnItemClickListener(new MyRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                /**
                 * 如果为普通消息，则跳转到消息详情页面;如果为红包消息，则跳转到红包列表页面
                 * 根据tag状态来判断为什么消息 现在假设1为红包，2为普通
                 * */
                Intent intent = new Intent(RedPackageListActivity.this,MessageDetailActivity.class);
                intent.putExtra("tag","2");
                startActivity(intent);
//
            }
        });

        mAdapter.setOnItemLongClickListener(new MyRecyclerviewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int pos) {

            }
        });
        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar_title.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_item1) {
                    Toast.makeText(RedPackageListActivity.this , R.string.item01 , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(RedPackageListActivity.this , R.string.item02 , Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

    }

    private void setAdapter() {

        mAdapter = new MyRecyclerviewAdapter(RedPackageListActivity.this, mData) {

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.message_item;
            }

            @Override
            public void bindData(MyRecyclerviewHolder holder, int position, Object item) {
                if (item == null) {
                    return;
                }

                holder.setBoldText(R.id.tv_gsname, ((Characters) item).getName());
                holder.setText(R.id.tv_miaoshu, ((Characters) item).getAvatar());
                holder.setText(R.id.tv_time, ((Characters) item).getName());
                holder.setImageView(R.id.img_logo, ((Characters) item).getAvatar());
//                holder.setClickListener(R.id.tv_delete, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getActivity(), "tv_delete", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        };
        recyclerView.setAdapter(mAdapter);

    }

    private void initData() {
        call = NetWork.getRetrofit().create(ExhibiterService.class).loadSectionCharacters();
        call.enqueue(new Callback<SectionCharacters>() {
            @Override
            public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                RpSuccess(response);
            }

            @Override
            public void onFailure(Call<SectionCharacters> call, Throwable t) {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                t.printStackTrace();
            }
        });
    }

    private void RpSuccess(Response<SectionCharacters> response) {
        mPageNum = 0;
        SectionCharacters sectionCharacters = response.body();
        mData = sectionCharacters.getCharacters();
        if (mData == null) {
            recyclerView.setEmptyView(findViewById(R.id.empty_view));
        }
        mAdapter.refreshdata(mData);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        //取消请求
        call.cancel();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 下拉刷新
     */
    private void dropdownrefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(RedPackageListActivity.this, "刷新", Toast.LENGTH_SHORT).show();
                call = NetWork.getRetrofit().create(ExhibiterService.class).loadSectionCharacters();
                call.enqueue(new Callback<SectionCharacters>() {
                    @Override
                    public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                        RefreshSuccess(response);
                    }

                    @Override
                    public void onFailure(Call<SectionCharacters> call, Throwable t) {
                        swipeRefreshLayout.setRefreshing(false);
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    private void RefreshSuccess(Response<SectionCharacters> response) {
        mPageNum = 0;
        SectionCharacters sectionCharacters = response.body();
        mData = sectionCharacters.getCharacters();
        if (mData == null) {
            recyclerView.setEmptyView(findViewById(R.id.empty_view));
        }
        mAdapter.refreshdata(mData);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }


    }

    /**
     * 上拉加载
     */
    private void upload() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                call = NetWork.getRetrofit().create(ExhibiterService.class).loadSectionCharacters();
                call.enqueue(new Callback<SectionCharacters>() {
                    @Override
                    public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                        LoadSuccess(response);
                    }

                    @Override
                    public void onFailure(Call<SectionCharacters> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    private void LoadSuccess(Response<SectionCharacters> response) {

        if (mPageNum < 3) {
            mPageNum++;
            SectionCharacters sectionCharacters = response.body();
            mData = sectionCharacters.getCharacters();
            if (mData == null) {
                recyclerView.setEmptyView(findViewById(R.id.empty_view));
            }
            mAdapter.addAll(mData);
        } else {
//            Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
