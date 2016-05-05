package com.eastfair.exhibitorapp.adapter.messageadapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.model.Characters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aspsine on 2015/9/9.
 */
public class RecyclerCharactersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private static final int TYPE_VIEWPAGER = 0;
//    private static final int TYPE_GROUP = 1;
//    private static final int TYPE_CHILD = 2;

    private final List<Characters> mHeroes;

//    private final List<Section> mSections;

//    private final List<Integer> mGroupPositions;

//    private LoopViewPagerAdapter mPagerAdapter;

//    private final int mType;

    public RecyclerCharactersAdapter() {
//        mType = type;
        mHeroes = new ArrayList<>();
//        mSections = new ArrayList<>();
//        mGroupPositions = new ArrayList<>();
    }

    public void setList(List<Characters> heroes) {
        mHeroes.clear();
//        mSections.clear();
        mHeroes.addAll(heroes);
//        append(sections);
    }

   /* public void append(List<Section> sections) {
        mSections.addAll(sections);
        notifyDataSetChanged();
//        initGroupPositions();
    }*/
/*
    public void initGroupPositions() {
//        mGroupPositions.clear();
        int groupPosition = 0;
        for (int i = 0; i < getGroupCount(); i++) {
            if (i == 0) {
                groupPosition = 0;
            } else {
                groupPosition += getChildCount(i - 1) + 1;
            }
            mGroupPositions.add(groupPosition + 1);
        }
    }*/
/*
    @Override
    public int getItemViewType(int position) {
//        if (position == 0) {
//            return TYPE_VIEWPAGER;
//        } else if (mGroupPositions.contains(position)) {
//            return TYPE_GROUP;
//        } else {
            return TYPE_CHILD;
//        }
    }*/

    @Override
    public int getItemCount() {
//        int count = mHeroes.size() == 0 ? 0 : 1;
        return mHeroes.size();
    }

 /*   public int getChildCount() {
//        List<Characters> characters = mSections.get(groupPosition).getCharacters();

//        return characters != null ? characters.size() : 0;
        return mHeroes.size();
    }*/

//    public int getGroupCount() {
//        return mSections.size();
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        int type = getItemViewType(i);
        View itemView = null;
//        switch (type) {
//            case TYPE_VIEWPAGER:
//                itemView = inflate(viewGroup, R.layout.layout_viewpager);
//                return new ViewPagerHolder(itemView);
//            case TYPE_GROUP:
//                if (mType == MessageFragment1.TYPE_LINEAR) {
//                    itemView = inflate(viewGroup, R.layout.item_header);
//                } else {
//                    itemView = inflate(viewGroup, R.layout.item_header_grid);
//                }
//                return new GroupHolder(itemView);
//            case TYPE_CHILD:
//                if (mType == MessageFragment1.TYPE_LINEAR) {
                    itemView = inflate(viewGroup, R.layout.item_hero);
//                } else {
//                    itemView = inflate(viewGroup, R.layout.item_hero_grid);
//                }
//                return new ChildHolder(itemView);
                return new ChildHolder(itemView);
//        }
//        throw new IllegalArgumentException("Wrong type!");
    }

    private View inflate(ViewGroup parent, int layoutRes) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        ((ChildHolder)viewHolder).tvName.setText(mHeroes.get(i).getName());
        Resources resources = ((ChildHolder)viewHolder).itemView.getResources();
        int size = resources.getDimensionPixelOffset(R.dimen.hero_avatar_size);
        int width = resources.getDimensionPixelOffset(R.dimen.hero_avatar_border);
        Glide.with(((ChildHolder)viewHolder).itemView.getContext())
                .load(mHeroes.get(i).getAvatar())
                .into(((ChildHolder)viewHolder).ivAvatar);

//        int type = getItemViewType(i);
//        switch (type) {
//            case TYPE_VIEWPAGER:
//                onBindViewPagerHolder((ViewPagerHolder) viewHolder);
//                break;
//            case TYPE_GROUP:
//                onBindGroupHolder((GroupHolder) viewHolder, getGroupPosition(i));
//                break;
//            case TYPE_CHILD:
//                onBindChildHolder((ChildHolder) viewHolder,  i);
//                break;
//        }
    }

//    private void onBindViewPagerHolder(ViewPagerHolder holder) {
//        if (holder.viewPager.getAdapter() == null) {
//            mPagerAdapter = new LoopViewPagerAdapter(holder.viewPager, holder.indicators);
//            holder.viewPager.setAdapter(mPagerAdapter);
//            holder.viewPager.addOnPageChangeListener(mPagerAdapter);
//            holder.viewPager.setBackgroundDrawable(holder.itemView.getResources().getDrawable(R.mipmap.bg_viewpager));
//            mPagerAdapter.setList(mHeroes);
//        } else {
//            mPagerAdapter.setList(mHeroes);
//        }
//    }

//    private void onBindGroupHolder(GroupHolder holder, int parentPosition) {
//        holder.tvGroup.setText(mSections.get(parentPosition).getName());
//    }

/*    private void onBindChildHolder(ChildHolder holder, int childPosition) {
//        Characters character = mSections.get(parentPosition).getCharacters().get(childPosition);
        holder.tvName.setText(mHeroes.get(childPosition).getName());
        Resources resources = holder.itemView.getResources();
        int size = resources.getDimensionPixelOffset(R.dimen.hero_avatar_size);
        int width = resources.getDimensionPixelOffset(R.dimen.hero_avatar_border);
        Glide.with(holder.itemView.getContext())
                .load(mHeroes.get(childPosition).getAvatar())
                .into(holder.ivAvatar);
//        Picasso.with(holder.itemView.getContext())
//                .load(character.getAvatar())
//                .resize(size, size)
//                .transform(new CircleTransformation(width))
//                .into(holder.ivAvatar);
    }*/

//    int getGroupPosition(int position) {
//        int groupPosition = 1;
//        for (int i = mGroupPositions.size() - 1; i >= 0; i--) {
//            if (position >= mGroupPositions.get(i)) {
//                groupPosition = i;
//                break;
//            }
//        }
//        return groupPosition;
//    }

    /*int getChildPosition(int position) {
//        int groupPosition = getGroupPosition(position);
//        int absGroupPosition = mGroupPositions.get(groupPosition);
        int childPositionInGroup = position - absGroupPosition - 1;
        return childPositionInGroup;
    }*/

//    public void start() {
//        if (mPagerAdapter != null) {
//            mPagerAdapter.start();
//        }
//    }
//
//    public void stop() {
//        if (mPagerAdapter != null) {
//            mPagerAdapter.stop();
//        }
//    }

/*    static class ViewPagerHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;
        ViewGroup indicators;

        public ViewPagerHolder(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewPager);
            indicators = (ViewGroup) itemView.findViewById(R.id.indicators);
        }
    }

    static class GroupHolder extends RecyclerView.ViewHolder {
        TextView tvGroup;

        public GroupHolder(View itemView) {
            super(itemView);
            tvGroup = (TextView) itemView.findViewById(R.id.tvHeader);
        }
    }*/

     class ChildHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvName;

        public ChildHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
        }
    }


}
