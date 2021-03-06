package com.eastfair.exhibiterapp.exhibits.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibiterapp.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.eastfair.exhibiterapp.base.BaseFragment;
import com.eastfair.exhibiterapp.capture.CaptureActivity;
import com.eastfair.exhibiterapp.demand.view.SendDemandActivity;
import com.eastfair.exhibiterapp.exhibitors.adapter.ContactAdapter;
import com.eastfair.exhibiterapp.exhibits.ExhibitsContract;
import com.eastfair.exhibiterapp.exhibits.presenter.ExhibitsPresenter;
import com.eastfair.exhibiterapp.exhibits.view.activity.ExhibitsDetailActivity;
import com.eastfair.exhibiterapp.exhibits.view.activity.ExhibitsSearchActivity;
import com.eastfair.exhibiterapp.export.view.ExportActivity;
import com.eastfair.exhibiterapp.model.ContactModel;
import com.eastfair.exhibiterapp.pinyin.CharacterParser;
import com.eastfair.exhibiterapp.pinyin.PinyinComparator;
import com.eastfair.exhibiterapp.weight.RecycleViewDivider;
import com.eastfair.exhibiterapp.weight.exhibitors.DividerDecoration;
import com.eastfair.exhibiterapp.weight.exhibitors.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展品界面
 */
public class ExhibitsFragment extends BaseFragment implements ExhibitsContract.View{

    @Bind(R.id.contact_sidebar)
    SideBar mSideBar;
    @Bind(R.id.contact_dialog)
    TextView mUserDialog;
    @Bind(R.id.tv_daochu)
    TextView tv_daochu;
    @Bind(R.id.tv_shaixuan)
    TextView tv_all;
    @Bind(R.id.contact_member)
    RecyclerView mRecyclerView;

    @Bind(R.id.exhibits_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.img_title)
    ImageView img_regist;
    @Bind(R.id.img_search)
    ImageView img_search;

    private ExhibitsContract.Present mPresent;
    private ContactModel mModel;
    private List<ContactModel.MembersEntity> mMembers = new ArrayList<>();
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private static ContactAdapter mAdapter;
    private List<ContactModel.MembersEntity> mAllLists = new ArrayList<>();

    public static ExhibitsFragment newInstance(String param1) {
        ExhibitsFragment fragment = new ExhibitsFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ExhibitsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exhibits, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
       /* String agrs1 = bundle.getString("agrs1");
        TextView tv = (TextView)view.findViewById(R.id.tv_location);
        tv.setText(agrs1);*/
        initParams();
        initView(view);
        setListener();
        return view;
    }
    private void initParams() {
        mPresent = new ExhibitsPresenter(this);
    }
    private void setListener() {
        mAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(getActivity(), "click " + pos, Toast.LENGTH_SHORT).show();
                SkipActivity(ExhibitsDetailActivity.class);
            }
        });

        mAdapter.setOnItemLongClickListener(new ContactAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int pos) {
                Toast.makeText(getActivity(), "clickLONG " + pos, Toast.LENGTH_SHORT).show();
            }
        });

        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }


    private void initView(View view) {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mSideBar.setTextView(mUserDialog);
        tv_all.setVisibility(View.VISIBLE);
        tv_all.setText("显示全部");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setHasFixedSize(true);

        //初始化toolbar
        initToolbar((AppCompatActivity) getActivity(),R.id.toolbar_title);
        text_Title.setText("展品");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        img_regist.setImageResource(R.mipmap.faxuqiu);
        toolbar_title.setNavigationIcon(R.mipmap.saoyisao);
        img_search.setVisibility(View.VISIBLE);

        mPresent.getData(mModel);
//        getNetData();

    }

    /*public void getNetData() {

        String tempData = "{\"groupName\":\"中国\",\"members\":[{\"id\":\"d1feb5db2\",\"username\":\"彭怡1\"},{\"id\":\"d1feb5db2\",\"username\":\"方谦\"},{\"id\":\"dd2feb5db2\",\"username\":\"谢鸣瑾\"},{\"id\":\"dd2478fb5db2\",\"username\":\"孔秋\"},{\"id\":\"dd24cd1feb5db2\",\"username\":\"曹莺安\"},{\"id\":\"dd2478eb5db2\",\"username\":\"酆有松\"},{\"id\":\"dd2478b5db2\",\"username\":\"姜莺岩\"},{\"id\":\"dd2eb5db2\",\"username\":\"谢之轮\"},{\"id\":\"dd2eb5db2\",\"username\":\"钱固茂\"},{\"id\":\"dd2d1feb5db2\",\"username\":\"潘浩\"},{\"id\":\"dd24ab5db2\",\"username\":\"花裕彪\"},{\"id\":\"dd24ab5db2\",\"username\":\"史厚婉\"},{\"id\":\"dd24a00d1feb5db2\",\"username\":\"陶信勤\"},{\"id\":\"dd24a5db2\",\"username\":\"水天固\"},{\"id\":\"dd24a5db2\",\"username\":\"柳莎婷\"},{\"id\":\"dd2d1feb5db2\",\"username\":\"冯茜\"},{\"id\":\"dd24a0eb5db2\",\"username\":\"吕言栋\"}]}";
        try {
            Gson gson = new GsonBuilder().create();
            mModel = gson.fromJson(tempData, ContactModel.class);
            setUI();
        } catch (Exception e) {

        }

    }*/

    /**
     * 发需求按钮点击事件
     */
    @OnClick(R.id.img_title)
    public void sendDeman() {
        Intent intent = new Intent(getActivity(), SendDemandActivity.class);
        startActivity(intent);
    }

    /**
     * 搜索按钮点击事件
     */
    @OnClick(R.id.img_search)
    public void search() {
        //展品搜索
        Intent intent = new Intent(getActivity(), ExhibitsSearchActivity.class);
        startActivity(intent);

    }

   /* private void setUI() {

        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                if (mAdapter != null) {
                }
                int position = mAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mRecyclerView.scrollToPosition(position);
                }

            }
        });
        seperateLists(mModel);

//        if (mAdapter == null) {
        mAdapter = new ContactAdapter(getActivity(), mAllLists) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.exhibits_item;
            }

            @Override
            public void bindData(MyRecyclerviewHolder holder, final int position, Object item) {

                if (item == null) {
                    return;
                }
                String name = mAllLists.get(position).getUsername();
                holder.setBoldText(R.id.tv_zpname, name);
            }

            @Override
            public long getHeaderIds(int position) {
                return mAllLists.get(position).getSortLetters().charAt(0);
            }

            @Override
            public void BindHeaderViewHolder(MyRecyclerviewHolder holder, int position) {
                TextView textView = (TextView) holder.itemView;
                String showValue = String.valueOf(mAllLists.get(position).getSortLetters().charAt(0));
                textView.setText(showValue);
            }

            @Override
            public int PositionForSection(char section) {
                for (int i = 0; i < getItemCount(); i++) {
                    String sortStr = mAllLists.get(i).getSortLetters();
                    char firstChar = sortStr.toUpperCase().charAt(0);
                    if (firstChar == section) {
                        return i;
                    }
                }
                return -1;
            }

            @Override
            public void onBindViewHolder(MyRecyclerviewHolder holder, int position) {
                bindData(holder, position, mAllLists.get(position));
            }
        };
        mRecyclerView.setAdapter(mAdapter);

        //添加头部
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecyclerView.addItemDecoration(headersDecor);
        mRecyclerView.addItemDecoration(new DividerDecoration(getActivity()));

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });
//        }
       *//* else {
            mAdapter.notifyDataSetChanged();
        }*//*
    }


    private void seperateLists(ContactModel mModel) {
        //members;
        if (mModel.getMembers() != null && mModel.getMembers().size() > 0) {
            for (int i = 0; i < mModel.getMembers().size(); i++) {
                ContactModel.MembersEntity entity = new ContactModel.MembersEntity();
                entity.setId(mModel.getMembers().get(i).getId());
                entity.setUsername(mModel.getMembers().get(i).getUsername());
                String pinyin = characterParser.getSelling(mModel.getMembers().get(i).getUsername());
                String sortString = pinyin.substring(0, 1).toUpperCase();

                if (sortString.matches("[A-Z]")) {
                    entity.setSortLetters(sortString.toUpperCase());
                } else {
                    entity.setSortLetters("#");
                }
                mMembers.add(entity);
            }
            Collections.sort(mMembers, pinyinComparator);
            mAllLists.addAll(mMembers);
        }


    }


    public void deleteUser(final int position) {
//        mAdapter.remove(mAdapter.getItem(position));
        showToast("删除成功");

    }

    public void showToast(String value) {
        Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();

    }*/

    @OnClick(R.id.tv_daochu)
    public void daochu() {
        SkipActivity(ExportActivity.class);
    }

    @OnClick(R.id.tv_shaixuan)
    public void shaixuan() {
        Toast.makeText(getActivity(), "quanbu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setPresenter(ExhibitsContract.Present presenter) {

    }

    @Override
    public void seperateLists(ContactModel mModel) {
        if (mModel.getMembers() != null && mModel.getMembers().size() > 0) {
            for (int i = 0; i < mModel.getMembers().size(); i++) {
                ContactModel.MembersEntity entity = new ContactModel.MembersEntity();
                entity.setId(mModel.getMembers().get(i).getId());
                entity.setUsername(mModel.getMembers().get(i).getUsername());
                String pinyin = characterParser.getSelling(mModel.getMembers().get(i).getUsername());
                String sortString = pinyin.substring(0, 1).toUpperCase();

                if (sortString.matches("[A-Z]")) {
                    entity.setSortLetters(sortString.toUpperCase());
                } else {
                    entity.setSortLetters("#");
                }
                mMembers.add(entity);
            }
            Collections.sort(mMembers, pinyinComparator);
            mAllLists.addAll(mMembers);
        }
    }

    @Override
    public void setUI() {

        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                if (mAdapter != null) {
                }
                int position = mAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mRecyclerView.scrollToPosition(position);
                }

            }
        });

        mAdapter = new ContactAdapter(getActivity(), mAllLists) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.exhibits_item;
            }

            @Override
            public void bindData(MyRecyclerviewHolder holder, final int position, Object item) {

                if (item == null) {
                    return;
                }
                String name = mAllLists.get(position).getUsername();
                holder.setBoldText(R.id.tv_zpname, name);
            }

            @Override
            public long getHeaderIds(int position) {
                return mAllLists.get(position).getSortLetters().charAt(0);
            }

            @Override
            public void BindHeaderViewHolder(MyRecyclerviewHolder holder, int position) {
                TextView textView = (TextView) holder.itemView;
                String showValue = String.valueOf(mAllLists.get(position).getSortLetters().charAt(0));
                textView.setText(showValue);
            }

            @Override
            public int PositionForSection(char section) {
                for (int i = 0; i < getItemCount(); i++) {
                    String sortStr = mAllLists.get(i).getSortLetters();
                    char firstChar = sortStr.toUpperCase().charAt(0);
                    if (firstChar == section) {
                        return i;
                    }
                }
                return -1;
            }

            @Override
            public void onBindViewHolder(MyRecyclerviewHolder holder, int position) {
                bindData(holder, position, mAllLists.get(position));
            }
        };
        mRecyclerView.setAdapter(mAdapter);

        //添加头部
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecyclerView.addItemDecoration(headersDecor);
        mRecyclerView.addItemDecoration(new DividerDecoration(getActivity()));

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

    }

    @Override
    public void deleteUser(int position) {
        Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
    }
}
