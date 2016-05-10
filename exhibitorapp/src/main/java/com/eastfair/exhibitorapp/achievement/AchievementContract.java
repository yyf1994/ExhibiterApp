package com.eastfair.exhibitorapp.achievement;


import com.eastfair.exhibitorapp.base.BasePresenter;
import com.eastfair.exhibitorapp.base.BaseView;

import java.util.List;

/**
 * Created by yyf on 2016/5/9.
 */
public class AchievementContract {

    public interface View extends BaseView<Present> {
//        void responseSuccess(Response<SectionCharacters> response, android.view.View view);
//        void responseFailed(Call<SectionCharacters> call, Throwable t);
//        void RefreshSuccess(Response<SectionCharacters> response, android.view.View view);
//        void LoadSuccess(Response<SectionCharacters> response, android.view.View view);
    }

    public interface Present extends BasePresenter {
        List<String> getParents();//获取父数据
        List<List<String>> getChilds();//获取子数据
//        void pulldowntorefresh(android.view.View view);//下拉刷新
//        void upload(android.view.View view);//上啦加载
//        void cancelRequest();
    }
}
