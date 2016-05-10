package com.eastfair.exhibiterapp.exhibitors;

import com.eastfair.exhibiterapp.base.BasePresenter;
import com.eastfair.exhibiterapp.base.BaseView;
import com.eastfair.exhibiterapp.model.ContactModel;

/**
 * Created by yyf on 2016/5/9.
 */
public class ExhibitorsContract {

    public interface View extends BaseView<Present> {
        void seperateLists(ContactModel mModel);
        void setUI();
        void deleteUser( int position);
//        void responseSuccess(Response<SectionCharacters> response, android.view.View view);
//        void responseFailed(Call<SectionCharacters> call, Throwable t);
//        void RefreshSuccess(Response<SectionCharacters> response, android.view.View view);
//        void LoadSuccess(Response<SectionCharacters> response, android.view.View view);
    }

    public interface Present extends BasePresenter {
        void getData(ContactModel mModel);//获取数据
//        void pulldowntorefresh(android.view.View view);//下拉刷新
//        void upload(android.view.View view);//上啦加载
//        void cancelRequest();
    }
}
