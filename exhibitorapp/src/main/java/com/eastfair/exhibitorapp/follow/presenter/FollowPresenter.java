package com.eastfair.exhibitorapp.follow.presenter;

import com.eastfair.exhibitorapp.follow.FollowContract;
import com.eastfair.exhibitorapp.follow.model.FollowHttp;
import com.eastfair.exhibitorapp.model.ContactModel;
import com.eastfair.exhibitorapp.model.SectionCharacters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;


/**
 * Created by yyf on 2016/5/9.
 */
public class FollowPresenter implements FollowContract.Present {
    private final FollowContract.View mView;
    Call<SectionCharacters> call;
    public FollowPresenter(FollowContract.View view)
    {
        this.mView = view;

        //fragment 要加上这句话  activity不需要
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getData(ContactModel mModel) {
        try {
            Gson gson = new GsonBuilder().create();
            mModel = gson.fromJson(FollowHttp.getInstance().getData(), ContactModel.class);
            mView.setUI();
            mView.seperateLists(mModel);
        } catch (Exception e) {

        }
    }

}
