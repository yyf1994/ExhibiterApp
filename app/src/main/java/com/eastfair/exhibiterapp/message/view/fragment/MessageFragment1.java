/*
package com.eastfair.exhibiterapp.message.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.messageadapter.RecyclerCharactersAdapter;
import com.eastfair.exhibiterapp.adapter.messageadapter.SectionAdapter;
import com.eastfair.exhibiterapp.base.BaseFragment;
import com.eastfair.exhibiterapp.model.SectionCharacters;
import com.eastfair.exhibiterapp.net.NetWork;
import com.eastfair.exhibiterapp.service.ExhibiterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class MessageFragment1 extends BaseFragment implements OnRefreshListener, OnLoadMoreListener,
        SectionAdapter.OnChildItemClickListener<Character>,
        SectionAdapter.OnChildItemLongClickListener<Character> {
    private static final String TAG = MessageFragment1.class.getSimpleName();

//    public static final int TYPE_LINEAR = 0;

//    public static final int TYPE_GRID = 1;
//
//    public static final int TYPE_STAGGERED_GRID = 2;

    private SwipeToLoadLayout swipeToLoadLayout;

    private RecyclerView recyclerView;

    private RecyclerCharactersAdapter mAdapter;

//    private int mType;

    private int mPageNum;

    public static MessageFragment1 newInstance(String param1) {
        MessageFragment1 fragment = new MessageFragment1();
        Bundle bundle = new Bundle();
        bundle.putString("agrs1", param1);
        fragment.setArguments(bundle);
        return fragment;
    }

    public MessageFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mType = getArguments().getInt("LAYOUT_MANAGER_TYPE", TYPE_LINEAR);
        mAdapter = new RecyclerCharactersAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitter_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        RecyclerView.LayoutManager layoutManager = null;
//        if (mType == TYPE_LINEAR) {
            layoutManager = new LinearLayoutManager(getContext());
//        }
        */
/*else if (mType == TYPE_GRID) {
            layoutManager = new GridLayoutManager(getContext(), 2);
        } else if (mType == TYPE_STAGGERED_GRID) {
            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        }*//*

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        mAdapter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
//        App.getRequestQueue().cancelAll(TAG + "refresh" + mType);
//        App.getRequestQueue().cancelAll(TAG + "loadmore" + mType);
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
//        mAdapter.stop();
    }

    @Override
    public void onChildItemClick(int groupPosition, int childPosition, Character character, View view) {


    }

    @Override
    public boolean onClickItemLongClick(int groupPosition, int childPosition, Character character, View view) {
        return false;
    }

    @Override
    public void onLoadMore() {
        Call<SectionCharacters> call = NetWork.getRetrofit().create(ExhibiterService.class).loadSectionCharacters();
        call.enqueue(new Callback<SectionCharacters>() {
            @Override
            public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                RpSuccess(response);
            }

            @Override
            public void onFailure(Call<SectionCharacters> call, Throwable t) {
                swipeToLoadLayout.setLoadingMore(false);
                t.printStackTrace();
            }
        });
    }

    private void RpSuccess(Response<SectionCharacters> response) {

        if (mPageNum < 3) {
            mPageNum++;
            mAdapter.setList(response.body().getCharacters());
//            mAdapter.append(response.body().getSections().subList(mPageNum, mPageNum + 1));
        } else {
            Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
        }
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRefresh() {

        Call<SectionCharacters> call = NetWork.getRetrofit().create(ExhibiterService.class).loadSectionCharacters();
        call.enqueue(new Callback<SectionCharacters>() {
            @Override
            public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                RpSuccess1(response);
            }

            @Override
            public void onFailure(Call<SectionCharacters> call, Throwable t) {
                swipeToLoadLayout.setRefreshing(false);
                t.printStackTrace();
            }
        });
    }

    private void RpSuccess1(Response<SectionCharacters> response) {
        mPageNum = 0;
//        mAdapter.setList(response.body().getCharacters(), response.body().getSections().subList(0, mPageNum + 1));
        mAdapter.setList(response.body().getCharacters());
        swipeToLoadLayout.setRefreshing(false);
    }
}
*/
