package com.eastfair.exhibitorapp.show.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewAdapter;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibitorapp.base.BaseFragment;
import com.eastfair.exhibitorapp.capture.CaptureActivity;
import com.eastfair.exhibitorapp.demand.SendDemandActivity;
import com.eastfair.exhibitorapp.model.Characters;
import com.eastfair.exhibitorapp.model.SectionCharacters;
import com.eastfair.exhibitorapp.show.Presenter.ShowPresenter;
import com.eastfair.exhibitorapp.show.ShowContract;
import com.eastfair.exhibitorapp.show.view.activity.RedPackageListActivity;
import com.eastfair.exhibitorapp.weight.RecycleViewDivider;
import com.eastfair.exhibitorapp.weight.SupportRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 展会界面
 */
public class ShowFragment extends BaseFragment implements ShowContract.View {

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
    @Bind(R.id.img_search)
    ImageView img_search;


    private List<Characters> mData;
    //    private MessageAdapter mAdapter;
    private MyRecyclerviewAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int lastVisibleItem;
    private int mPageNum;
    private int mpageSize = 1;

    private ShowContract.Present mPresent;

    public static ShowFragment newInstance(String param1) {
        ShowFragment fragment = new ShowFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ShowFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");

        initView(view);
        initParams();
        //设置adapter
        setAdapter();
        //初始化数据
        initData(view);
        //下拉刷新
        dropdownrefresh(view);
        //上拉加载
        upload(view);
        setListener();
        return view;
    }

    private void initParams() {
        mPresent = new ShowPresenter(this);
    }

    private void initView(View view) {
        //设置recyclerview
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, 1, R.color.list_dicider_color));
        recyclerView.setHasFixedSize(true);

        //设置加载时进度条颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorPrimaryDark);

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        //初始化toolbar
        initToolbar((AppCompatActivity) getActivity(), R.id.message_toolbar);
        text_Title.setText("展会");
        //设置粗体
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        img_regist.setImageResource(R.mipmap.faxuqiu);
        toolbar_title.setNavigationIcon(R.mipmap.saoyisao);
        img_search.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresent.start();
    }

    /**
     * 发需求按钮点击事件
     */
    @OnClick(R.id.img_title)
    public void sendDeman() {
        Intent intent = new Intent(getActivity(), SendDemandActivity.class);
        startActivity(intent);
    }


    private void setListener() {

        mAdapter.setOnItemClickListener(new MyRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                //如果为普通消息，则跳转到消息详情页面
//                SkipActivity(MessageDetailActivity.class);
                //如果为红包消息，则跳转到红包列表页面
                SkipActivity(RedPackageListActivity.class);
            }
        });

        mAdapter.setOnItemLongClickListener(new MyRecyclerviewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int pos) {
                //此处直接new一个Dialog对象出来，在实例化的时候传入主题
                final Dialog dialog = new Dialog(getActivity(), R.style.MyDialog);
                //设置它的ContentView
                dialog.setContentView(R.layout.dialog);
                dialog.show();
                Button btn_ok = (Button) dialog.findViewById(R.id.dialog_button_ok);
                Button btn_cancel = (Button) dialog.findViewById(R.id.dialog_button_cancel);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
               /* DialogUtil.showDeleteDialog(getActivity(), new DialogUtil.OnClickListenerOk() {
                    @Override
                    public void onClickok(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"ok",Toast.LENGTH_SHORT).show();
                    }
                }, new DialogUtil.OnClickListenerCancel() {
                    @Override
                    public void onClickcancel(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });*/
            }
        });

        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void setAdapter() {

        mAdapter = new MyRecyclerviewAdapter(getActivity(), mData) {

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

    private void initData(final View view) {
       mPresent.getData(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        //取消请求
        mPresent.cancelRequest();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 下拉刷新
     */
    private void dropdownrefresh(final View view) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
             mPresent.pulldowntorefresh(view);
            }
        });
    }

    @Override
    public void responseSuccess(Response<SectionCharacters> response, View view) {

        mPageNum = 0;
        SectionCharacters sectionCharacters = response.body();
        mData = sectionCharacters.getCharacters();
        if (mData == null) {
            recyclerView.setEmptyView(view.findViewById(R.id.empty_view));
        }
        mAdapter.refreshdata(mData);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void responseFailed(Call<SectionCharacters> call, Throwable t) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        t.printStackTrace();

    }

    @Override
    public void RefreshSuccess(Response<SectionCharacters> response, View view) {
        mPageNum = 0;
        SectionCharacters sectionCharacters = response.body();
        mData = sectionCharacters.getCharacters();
        if (mData == null) {
            recyclerView.setEmptyView(view.findViewById(R.id.empty_view));
        }
        mAdapter.refreshdata(mData);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void LoadSuccess(Response<SectionCharacters> response, View view) {
        if (mPageNum < 3) {
            mPageNum++;
            SectionCharacters sectionCharacters = response.body();
            mData = sectionCharacters.getCharacters();
            if (mData == null) {
                recyclerView.setEmptyView(view.findViewById(R.id.empty_view));
            }
            mAdapter.addAll(mData);
        } else {
//            Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 上拉加载
     */
    private void upload(final View view) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mPresent.upload(view);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setPresenter(ShowContract.Present presenter) {

    }
}
