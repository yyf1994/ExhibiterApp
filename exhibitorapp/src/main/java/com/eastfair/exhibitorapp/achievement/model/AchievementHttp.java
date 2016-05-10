package com.eastfair.exhibitorapp.achievement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyf on 2016/5/9.
 */
public class AchievementHttp<T> {
    private AchievementHttp(){};

    private static class InstanceHolder{
        private static final AchievementHttp instance = new AchievementHttp();
    }

    public static AchievementHttp getInstance()
    {
        return InstanceHolder.instance;
    }

    public List<String>  getParents() {
        List<String> parents = new ArrayList<>();
        parents.add("我关注的");
        parents.add("关注我的");
        parents.add("打过电话的");
        return parents;
    }
    public List<List<String>>  getChilds() {

        List<List<String>> childs = new ArrayList<>();
        for (int i = 0; i < getParents().size(); i++) {
            List<String> c = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                c.add("child:" + j + "");
            }
            childs.add(c);
        }
        return childs;
    }
}
