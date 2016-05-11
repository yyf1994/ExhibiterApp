package com.eastfair.exhibiterapp.exhibitors.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.exhibitors.adapter.ContactAdapter;
import com.eastfair.exhibiterapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibiterapp.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.eastfair.exhibiterapp.base.BaseFragment;
import com.eastfair.exhibiterapp.exhibitors.ExhibitorsContract;
import com.eastfair.exhibiterapp.exhibitors.presenter.ExhibitorsPresenter;
import com.eastfair.exhibiterapp.exhibitors.view.activity.ExhibitorsDetailActivity;
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
 * 展商关注界面
 */
public class ExhibitorsFollowFragment extends BaseFragment implements ExhibitorsContract.View{

    @Bind(R.id.contact_sidebar)
    SideBar mSideBar;
    @Bind(R.id.contact_dialog)
    TextView mUserDialog;
    @Bind(R.id.tv_daochu)
    TextView tv_daochu;
    @Bind(R.id.contact_member)
    RecyclerView mRecyclerView;

    private ExhibitorsContract.Present mPresent;

    private ContactModel mModel;
    private List<ContactModel.MembersEntity> mMembers = new ArrayList<>();
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private static ContactAdapter mAdapter;
    private List<ContactModel.MembersEntity> mAllLists = new ArrayList<>();

    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String TAG = "ExhibitorsFollowFragment";

    public static ExhibitorsFollowFragment newInstance(int page) {
        ExhibitorsFollowFragment fragment = new ExhibitorsFollowFragment();
        Bundle args = new Bundle();
//        args.putString("agrs1", param1);
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }


    public ExhibitorsFollowFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exhibitors, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");
        initParams();
        initView();
        setListener();
        return view;
    }
    private void initParams() {
        mPresent = new ExhibitorsPresenter(this);
    }
    private void setListener() {
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

    private void initView() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mSideBar.setTextView(mUserDialog);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setHasFixedSize(true);

        mPresent.getData(mModel);
    }

    @OnClick(R.id.tv_daochu)
    public void daochu() {
        SkipActivity(ExportActivity.class);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setPresenter(ExhibitorsContract.Present presenter) {

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
                return R.layout.item_contact1;
            }

            @Override
            public void bindData(MyRecyclerviewHolder holder, final int position, Object item) {

                if(item ==null){
                    return;
                }
                String name = mAllLists.get(position).getUsername();
                holder.setBoldText(R.id.tv_gsname, name);
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
