package com.eastfair.exhibitorapp.show.model;


import com.eastfair.exhibitorapp.net.NetWork;
import com.eastfair.exhibitorapp.service.ExhibiterService;

import retrofit2.Call;

/**
 * Created by yyf on 2016/5/9.
 */
public class ShowHttp<T> {
    private ShowHttp(){};

    private static class InstanceHolder{
        private static final ShowHttp instance = new ShowHttp();
    }

    public static ShowHttp getInstance()
    {
        return InstanceHolder.instance;
    }

    public Call<T> getData() {
        Call<T> call = (Call<T>) NetWork.getRetrofit().create(ExhibiterService.class).loadSectionCharacters();
        return call;
    }
    public Call<T> pulldowntorefresh() {
        Call<T> call = (Call<T>) NetWork.getRetrofit().create(ExhibiterService.class).loadSectionCharacters();
        return call;
    }
    public Call<T> upload() {
        Call<T> call = (Call<T>) NetWork.getRetrofit().create(ExhibiterService.class).loadSectionCharacters();
        return call;
    }
}
