package com.eastfair.exhibiterapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.adapter.ContactAdapter;
import com.eastfair.exhibiterapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibiterapp.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.eastfair.exhibiterapp.base.BaseFragment;
import com.eastfair.exhibiterapp.model.ContactModel;
import com.eastfair.exhibiterapp.pinyin.CharacterParser;
import com.eastfair.exhibiterapp.pinyin.PinyinComparator;
import com.eastfair.exhibiterapp.ui.activity.ExportActivity;
import com.eastfair.exhibiterapp.ui.activity.exhibitors.ExhibitorsDetailActivity;
import com.eastfair.exhibiterapp.weight.RecycleViewDivider;
import com.eastfair.exhibiterapp.weight.SupportRecyclerView;
import com.eastfair.exhibiterapp.weight.exhibitors.DividerDecoration;
import com.eastfair.exhibiterapp.weight.exhibitors.SideBar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 展商感兴趣界面
 */
public class ExhibitorsInterestFragment extends BaseFragment implements View.OnClickListener{

    private SideBar mSideBar;
    private TextView mUserDialog;
    private TextView tv_daochu;
    private TextView tv_shaixuan;
    private SupportRecyclerView mRecyclerView;
    private ContactModel mModel;
    private List<ContactModel.MembersEntity> mMembers = new ArrayList<>();
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private static ContactAdapter mAdapter;
    private List<ContactModel.MembersEntity> mAllLists = new ArrayList<>();

    public static final String ARG_PAGE = "ARG_PAGE";

    public static ExhibitorsInterestFragment newInstance(int page) {
        ExhibitorsInterestFragment fragment = new ExhibitorsInterestFragment();
        Bundle args = new Bundle();
//        args.putString("agrs1", param1);
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }


    public ExhibitorsInterestFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exhibitors, container, false);
        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");
        initView(view);
        setListener();
        return view;
    }

    private void setListener() {
        tv_daochu.setOnClickListener(this);
        tv_shaixuan.setOnClickListener(this);
        mAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(getActivity(), "click " + pos, Toast.LENGTH_SHORT).show();
                SkipActivity(ExhibitorsDetailActivity.class);
            }
        });

        mAdapter.setOnItemLongClickListener(new ContactAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int pos) {
                Toast.makeText(getActivity(), "clickLONG " + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initView(View view) {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mSideBar = (SideBar) view.findViewById(R.id.contact_sidebar);
        mUserDialog = (TextView) view.findViewById(R.id.contact_dialog);
        mRecyclerView = (SupportRecyclerView) view.findViewById(R.id.contact_member);
        tv_daochu = (TextView) view.findViewById(R.id.tv_daochu);
        tv_shaixuan = (TextView) view.findViewById(R.id.tv_shaixuan);
        tv_shaixuan.setVisibility(View.VISIBLE);
        mSideBar.setTextView(mUserDialog);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setHasFixedSize(true);

        getNetData();

    }

    public void getNetData() {

        String tempData ="{\"groupName\":\"中国\",\"members\":[{\"id\":\"d1feb5db2\",\"username\":\"彭怡1\"},{\"id\":\"d1feb5db2\",\"username\":\"方谦\"},{\"id\":\"dd2feb5db2\",\"username\":\"谢鸣瑾\"},{\"id\":\"dd2478fb5db2\",\"username\":\"孔秋\"},{\"id\":\"dd24cd1feb5db2\",\"username\":\"曹莺安\"},{\"id\":\"dd2478eb5db2\",\"username\":\"酆有松\"},{\"id\":\"dd2478b5db2\",\"username\":\"姜莺岩\"},{\"id\":\"dd2eb5db2\",\"username\":\"谢之轮\"},{\"id\":\"dd2eb5db2\",\"username\":\"钱固茂\"},{\"id\":\"dd2d1feb5db2\",\"username\":\"潘浩\"},{\"id\":\"dd24ab5db2\",\"username\":\"花裕彪\"},{\"id\":\"dd24ab5db2\",\"username\":\"史厚婉\"},{\"id\":\"dd24a00d1feb5db2\",\"username\":\"陶信勤\"},{\"id\":\"dd24a5db2\",\"username\":\"水天固\"},{\"id\":\"dd24a5db2\",\"username\":\"柳莎婷\"},{\"id\":\"dd2d1feb5db2\",\"username\":\"冯茜\"},{\"id\":\"dd24a0eb5db2\",\"username\":\"吕言栋\"}]}";
        try {
            Gson gson = new GsonBuilder().create();
            mModel = gson.fromJson(tempData, ContactModel.class);
            setUI();
        } catch (Exception e) {

        }

    }

    private void setUI() {

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
                    return R.layout.item_contact1;
                }

                @Override
                public void bindData(MyRecyclerviewHolder holder, final int position, Object item) {

                    if(item ==null){
                        return;
                    }
                    String name = mAllLists.get(position).getUsername();
                    holder.setText(R.id.tv_gsname, name);
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
       /* else {
            mAdapter.notifyDataSetChanged();
        }*/
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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_daochu:
                SkipActivity(ExportActivity.class);
                break;
            case R.id.tv_shaixuan:
//                SkipActivity(ExportActivity.class);
                break;
        }
    }
}
