package com.eastfair.exhibiterapp.main;

import com.eastfair.exhibiterapp.base.BasePresenter;
import com.eastfair.exhibiterapp.base.BaseView;

/**
 * Created by yyf on 2016/5/9.
 */
public class MainContract {

    public interface View extends BaseView<Present> {

    }

    public interface Present extends BasePresenter {

    }
}
