package com.eastfair.exhibitorapp.show;


import com.eastfair.exhibitorapp.base.BasePresenter;
import com.eastfair.exhibitorapp.base.BaseView;
import com.eastfair.exhibitorapp.model.SectionCharacters;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by yyf on 2016/5/9.
 */
public class ShowContract {

    public interface View extends BaseView<Present> {
        void responseSuccess(Response<SectionCharacters> response, android.view.View view);
        void responseFailed(Call<SectionCharacters> call, Throwable t);
        void RefreshSuccess(Response<SectionCharacters> response, android.view.View view);
        void LoadSuccess(Response<SectionCharacters> response, android.view.View view);

    }

    public interface Present extends BasePresenter {
        void getData(android.view.View view);//获取数据
        void pulldowntorefresh(android.view.View view);//下拉刷新
        void upload(android.view.View view);//上啦加载
        void cancelRequest();
    }
}
