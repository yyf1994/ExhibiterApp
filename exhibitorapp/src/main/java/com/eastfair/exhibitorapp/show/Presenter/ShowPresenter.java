package com.eastfair.exhibitorapp.show.Presenter;

import android.view.View;

import com.eastfair.exhibitorapp.model.SectionCharacters;
import com.eastfair.exhibitorapp.show.ShowContract;
import com.eastfair.exhibitorapp.show.model.ShowHttp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yyf on 2016/5/9.
 */
public class ShowPresenter implements ShowContract.Present {
    private final ShowContract.View mView;
    Call<SectionCharacters> call;
    public ShowPresenter(ShowContract.View view)
    {
        this.mView = view;

        //fragment 要加上这句话  activity不需要
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getData(final View view) {
        call = ShowHttp.getInstance().getData();
        call.enqueue(new Callback<SectionCharacters>() {
            @Override
            public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                mView.responseSuccess(response,view);
            }

            @Override
            public void onFailure(Call<SectionCharacters> call, Throwable t) {
                mView.responseFailed(call,t);
            }
        });
    }

    @Override
    public void pulldowntorefresh(final View view) {
         call = ShowHttp.getInstance().pulldowntorefresh();
        call.enqueue(new Callback<SectionCharacters>() {
            @Override
            public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                mView.RefreshSuccess(response,view);
            }

            @Override
            public void onFailure(Call<SectionCharacters> call, Throwable t) {
                mView.responseFailed(call,t);
            }
        });
    }

    @Override
    public void upload(final View view) {
         call = ShowHttp.getInstance().upload();
        call.enqueue(new Callback<SectionCharacters>() {
            @Override
            public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                mView.LoadSuccess(response,view);
            }

            @Override
            public void onFailure(Call<SectionCharacters> call, Throwable t) {
                mView.responseFailed(call,t);
            }
        });
    }

    @Override
    public void cancelRequest() {
        call.cancel();
    }

}
