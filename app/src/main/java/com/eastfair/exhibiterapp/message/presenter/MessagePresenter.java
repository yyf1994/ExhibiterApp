package com.eastfair.exhibiterapp.message.presenter;

import android.view.View;

import com.eastfair.exhibiterapp.message.MessageContract;
import com.eastfair.exhibiterapp.message.model.MessageHttp;
import com.eastfair.exhibiterapp.model.SectionCharacters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yyf on 2016/5/9.
 */
public class MessagePresenter implements MessageContract.Present {
    private final MessageContract.View mView;
    Call<SectionCharacters> call;
    public MessagePresenter(MessageContract.View view)
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
        call = MessageHttp.getInstance().getData();
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
    public void pulldowntorefresh() {
         call = MessageHttp.getInstance().pulldowntorefresh();
        call.enqueue(new Callback<SectionCharacters>() {
            @Override
            public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                mView.RefreshSuccess(response);
            }

            @Override
            public void onFailure(Call<SectionCharacters> call, Throwable t) {
                mView.responseFailed(call,t);
            }
        });
    }

    @Override
    public void upload() {
         call = MessageHttp.getInstance().upload();
        call.enqueue(new Callback<SectionCharacters>() {
            @Override
            public void onResponse(Call<SectionCharacters> call, Response<SectionCharacters> response) {
                mView.LoadSuccess(response);
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
