package com.eastfair.exhibitorapp.achievement.presenter;

import com.eastfair.exhibitorapp.achievement.AchievementContract;
import com.eastfair.exhibitorapp.achievement.model.AchievementHttp;
import com.eastfair.exhibitorapp.model.SectionCharacters;

import java.util.List;

import retrofit2.Call;


/**
 * Created by yyf on 2016/5/9.
 */
public class AchievementPresenter implements AchievementContract.Present {
    private final AchievementContract.View mView;
    Call<SectionCharacters> call;
    public AchievementPresenter(AchievementContract.View view)
    {
        this.mView = view;

        //fragment 要加上这句话  activity不需要
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public List<String> getParents() {
        return AchievementHttp.getInstance().getParents();
    }

    @Override
    public List<List<String>> getChilds() {
        return  AchievementHttp.getInstance().getChilds();
    }
}
