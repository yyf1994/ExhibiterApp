package com.eastfair.exhibitorapp.service;


import com.eastfair.exhibitorapp.model.SectionCharacters;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/3/24.
 */
public interface ExhibiterService {
    /**
     * https://api.douban.com/v2/book/1220562   暂时没用
     * */

//    @GET("/v2/book/1220562")
//    Call<Book> loadBook();

    /**
     * http://api.douban.com/labs/bubbler/user/ahbei 暂时没用
     * */
//    @GET("/labs/bubbler/user/{user}")
//    Call<UserInfo> loadUserInfo(@Path("user") String user);

    /**
     * http://api.douban.com/labs/bubbler/user/ahbei
     * */
//    @GET("/onebox/news/query")
//    Call<News> loadNews(
//            @Query("q") String q,
//            @Query("dtype") String dtype,
//            @Query("key") String key);


//    @FormUrlEncoded
//    @POST("/onebox/news/query")
//    Call<News> loadNewsPost(
//            @Field("q") String q,
//            @Field("dtype") String dtype,
//            @Field("key") String key);
//public static final String CHARACTERS = "https://raw.githubusercontent.com/Aspsine/SwipeToLoadLayout/dev/app/src/main/assets/characters.json";
//    public static final String ALL_CHARACTERS = "https://raw.githubusercontent.com/Aspsine/SwipeToLoadLayout/master/app/src/main/assets/all_characters.json";

    @GET("/Aspsine/SwipeToLoadLayout/dev/app/src/main/assets/characters.json")
    Call<SectionCharacters> loadSectionCharacters();

//    @GET("/Aspsine/SwipeToLoadLayout/master/app/src/main/assets/all_characters.json")
//    Call<SectionCharacters> loadSectionCharacters();

}
