package com.eastfair.exhibiterapp.exhibitors.presenter;

import com.eastfair.exhibiterapp.exhibitors.ExhibitorsContract;
import com.eastfair.exhibiterapp.exhibitors.model.ExhibitorsHttp;
import com.eastfair.exhibiterapp.model.ContactModel;
import com.eastfair.exhibiterapp.model.SectionCharacters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;

/**
 * Created by yyf on 2016/5/9.
 */
public class ExhibitorsPresenter implements ExhibitorsContract.Present {
    private final ExhibitorsContract.View mView;
    Call<SectionCharacters> call;
    public ExhibitorsPresenter(ExhibitorsContract.View view)
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
            mModel = gson.fromJson(ExhibitorsHttp.getInstance().getData(), ContactModel.class);
            mView.setUI();
            mView.seperateLists(mModel);
        } catch (Exception e) {

        }
    }

}
