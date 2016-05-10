package com.eastfair.exhibiterapp.message.model;

import com.eastfair.exhibiterapp.net.NetWork;
import com.eastfair.exhibiterapp.service.ExhibiterService;

import retrofit2.Call;

/**
 * Created by yyf on 2016/5/9.
 */
public class MessageHttp<T> {
    private MessageHttp(){};

    private static class InstanceHolder{
        private static final MessageHttp instance = new MessageHttp();
    }

    public static MessageHttp getInstance()
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
