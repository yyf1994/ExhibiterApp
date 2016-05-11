package com.eastfair.exhibiterapp.message;

import com.eastfair.exhibiterapp.base.BasePresenter;
import com.eastfair.exhibiterapp.base.BaseView;
import com.eastfair.exhibiterapp.model.SectionCharacters;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by yyf on 2016/5/9.
 */
public class MessageContract {

    public interface View extends BaseView<Present> {
        void responseSuccess(Response<SectionCharacters> response, android.view.View view);
        void responseFailed(Call<SectionCharacters> call, Throwable t);
        void RefreshSuccess(Response<SectionCharacters> response);
        void LoadSuccess(Response<SectionCharacters> response);
    }

    public interface Present extends BasePresenter {
        void getData(android.view.View view);//获取数据
        void pulldowntorefresh();//下拉刷新
        void upload();//上啦加载
        void cancelRequest();
    }
}
