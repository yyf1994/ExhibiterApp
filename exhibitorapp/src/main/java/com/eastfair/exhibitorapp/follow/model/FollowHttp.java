package com.eastfair.exhibitorapp.follow.model;

/**
 * Created by yyf on 2016/5/9.
 */
public class FollowHttp<T> {
    private FollowHttp(){};

    private static class InstanceHolder{
        private static final FollowHttp instance = new FollowHttp();
    }

    public static FollowHttp getInstance()
    {
        return InstanceHolder.instance;
    }

    public String getData() {
        String tempData ="{\"groupName\":\"中国\",\"members\":[{\"id\":\"d1feb5db2\",\"username\":\"彭怡1\"},{\"id\":\"d1feb5db2\",\"username\":\"方谦\"},{\"id\":\"dd2feb5db2\",\"username\":\"谢鸣瑾\"},{\"id\":\"dd2478fb5db2\",\"username\":\"孔秋\"},{\"id\":\"dd24cd1feb5db2\",\"username\":\"曹莺安\"},{\"id\":\"dd2478eb5db2\",\"username\":\"酆有松\"},{\"id\":\"dd2478b5db2\",\"username\":\"姜莺岩\"},{\"id\":\"dd2eb5db2\",\"username\":\"谢之轮\"},{\"id\":\"dd2eb5db2\",\"username\":\"钱固茂\"},{\"id\":\"dd2d1feb5db2\",\"username\":\"潘浩\"},{\"id\":\"dd24ab5db2\",\"username\":\"花裕彪\"},{\"id\":\"dd24ab5db2\",\"username\":\"史厚婉\"},{\"id\":\"dd24a00d1feb5db2\",\"username\":\"陶信勤\"},{\"id\":\"dd24a5db2\",\"username\":\"水天固\"},{\"id\":\"dd24a5db2\",\"username\":\"柳莎婷\"},{\"id\":\"dd2d1feb5db2\",\"username\":\"冯茜\"},{\"id\":\"dd24a0eb5db2\",\"username\":\"吕言栋\"}]}";
        return tempData;
    }
}
