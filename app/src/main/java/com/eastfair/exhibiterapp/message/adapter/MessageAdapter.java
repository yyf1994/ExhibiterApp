package com.eastfair.exhibiterapp.message.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.model.Characters;
import com.eastfair.exhibiterapp.util.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public class MessageAdapter extends BaseQuickAdapter<Characters> {

    public MessageAdapter(Context context, List<Characters> datas) {
        super(context, R.layout.message_item, datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, Characters item) {
        helper.setText(R.id.tv_gsname, item.getName())
                .setText(R.id.tv_miaoshu, item.getAvatar())
                .setText(R.id.tv_time, item.getName())
                .setImageUrl(R.id.img_logo, item.getAvatar(), R.mipmap.ic_launcher, new GlideCircleTransform(mContext))
                .linkify(R.id.tv_gsname);
    }

}

