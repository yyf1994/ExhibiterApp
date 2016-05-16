package com.eastfair.exhibiterapp.message.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseFragment;
import com.eastfair.exhibiterapp.capture.CaptureActivity;
import com.eastfair.exhibiterapp.demand.view.SendDemandActivity;
import com.eastfair.exhibiterapp.message.MessageContract;
import com.eastfair.exhibiterapp.message.adapter.MessageAdapter;
import com.eastfair.exhibiterapp.message.presenter.MessagePresenter;
import com.eastfair.exhibiterapp.message.view.activity.MessageDetailActivity;
import com.eastfair.exhibiterapp.model.Characters;
import com.eastfair.exhibiterapp.model.SectionCharacters;
import com.eastfair.exhibiterapp.util.DialogUtil;
import com.eastfair.exhibiterapp.weight.RecycleViewDivider;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 消息界面
 */
public class MessageFragment extends BaseFragment implements MessageContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.message_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.img_title)
    ImageView img_regist;
    @Bind(R.id.img_search)
    ImageView img_search;


    private List<Characters> mData;
    private MessageAdapter mAdapter;

    private LinearLayoutManager linearLayoutManager;

    private static final int TOTAL_COUNTER = 18;

    private static final int PAGE_SIZE = 5;//正式使用的时候设置为10，让第一屏满屏

    private int mCurrentCounter = 0;

    private MessageContract.Present mPresent;

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
    public void onResume() {
        super.onResume();
        mPresent.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");
        initView(view);
        initParams();
        initAdapter();
        setListener();
        //初始化数据
        mPresent.getData(view);
//        recyclerView.setAdapter(mAdapter);
        return view;
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
        initToolbar((AppCompatActivity) getActivity(), R.id.message_toolbar, "消息");
        text_Title.setText("消息");
        //设置粗体
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        img_regist.setImageResource(R.mipmap.faxuqiu);
        toolbar_title.setNavigationIcon(R.mipmap.saoyisao);
        img_search.setVisibility(View.GONE);

    }

    private void initParams() {
        mPresent = new MessagePresenter(this);
    }

    private void initAdapter() {
        mAdapter = new MessageAdapter(getActivity(), mData);
        mAdapter.openLoadAnimation();
        recyclerView.setAdapter(mAdapter);
        mCurrentCounter = mAdapter.getItemCount();

        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadMore(PAGE_SIZE, true);
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), MessageDetailActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
        mAdapter.setOnRecyclerViewItemLongClickListener(new BaseQuickAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int i) {
                Toast.makeText(getActivity(), "long", Toast.LENGTH_SHORT).show();
               /* final Dialog dialog = new Dialog(getActivity(), R.style.MyDialog);
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
                });*/
                DialogUtil.showDeleteDialog(getActivity(), new DialogUtil.OnClickListenerOk() {
                    @Override
                    public void onClickok(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"ok",Toast.LENGTH_SHORT).show();
                    }
                }, new DialogUtil.OnClickListenerCancel() {
                    @Override
                    public void onClickcancel(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                return true;
            }
        });
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

        swipeRefreshLayout.setOnRefreshListener(this);
        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void responseSuccess(Response<SectionCharacters> response, View view) {
        SectionCharacters sectionCharacters = response.body();
        mData = sectionCharacters.getCharacters();
        mAdapter.setNewData(mData);
        mCurrentCounter = PAGE_SIZE;
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
    public void RefreshSuccess(Response<SectionCharacters> response) {
        SectionCharacters sectionCharacters = response.body();
        mData = sectionCharacters.getCharacters();
        mAdapter.setNewData(mData);
        mCurrentCounter = PAGE_SIZE;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void LoadSuccess(Response<SectionCharacters> response) {
        if (mCurrentCounter >= TOTAL_COUNTER) {
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyDataChangedAfterLoadMore(false);
                }
            });

        } else {
            SectionCharacters sectionCharacters = response.body();
            mData = sectionCharacters.getCharacters();
            mAdapter.addData(mData);
            mAdapter.notifyDataChangedAfterLoadMore(mData, true);
            mCurrentCounter = mAdapter.getItemCount();
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setPresenter(MessageContract.Present presenter) {

    }

    @Override
    public void onRefresh() {
        mPresent.pulldowntorefresh();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresent.upload();
    }
}
