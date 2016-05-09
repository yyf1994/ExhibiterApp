/**
 * created by jiang, 12/3/15
 * Copyright (c) 2015, jyuesong@gmail.com All Rights Reserved.
 * *                #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */

package com.eastfair.exhibitorapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibitorapp.R;

import java.util.List;

/**
 * Created by jiang on 12/3/15.
 * 根据当前权限进行判断相关的滑动逻辑
 */
public class AchievementAdapter extends BaseExpandableListAdapter {
    private List<String> parents;
    private List<List<String>> childs;
    private Context mContext = null;

    public AchievementAdapter() {
    }
    public AchievementAdapter(List<String> parents,List<List<String>> childs,Context context) {
        this.parents = parents;
        this.childs = childs;
        this.mContext= context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        String a = childs.get(groupPosition).get(childPosition);
        AchievementHolder achievementHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.mContext);
            convertView = inflater.inflate(R.layout.item_contact1, null);

            achievementHolder = new AchievementHolder();
            achievementHolder.img_logo = (ImageView) convertView
                    .findViewById(R.id.img_logo);
            achievementHolder.tv_companyname = (TextView) convertView
                    .findViewById(R.id.tv_companyname);
            achievementHolder.tv_name = (TextView) convertView
                    .findViewById(R.id.tv_name);
            achievementHolder.tv_job = (TextView) convertView
                    .findViewById(R.id.tv_job);
            achievementHolder.tv_follow = (TextView) convertView
                    .findViewById(R.id.tv_follow);
            convertView.setTag(achievementHolder);
        }else{
            achievementHolder = (AchievementHolder) convertView.getTag();
        }
        achievementHolder.tv_name.setText(a);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parents.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return parents.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.parent_item_expandlistview, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.textview1);
        ImageView iv = (ImageView) convertView
                .findViewById(R.id.imageview1);
        tv.setText(parents.get(groupPosition));
        /** 判断当前是展开还是闭合，为了显示不同的箭头 */
        if (isExpanded) {
            iv.setImageResource(R.mipmap.iv_right);
        } else {
            iv.setImageResource(R.mipmap.iv_down);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        /** 注意这里必须返回true 不然点击不到子item */
        return true;
    }
}
class AchievementHolder {
    public ImageView img_logo;
    public TextView tv_name;
    public TextView tv_job;
    public TextView tv_companyname;
    public TextView tv_follow;
}