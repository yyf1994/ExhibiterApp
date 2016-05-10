package com.eastfair.exhibitorapp.demand;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewAdapter;
import com.eastfair.exhibitorapp.adapter.MyRecyclerviewHolder;
import com.eastfair.exhibitorapp.base.BaseActivity;
import com.eastfair.exhibitorapp.weight.RecycleViewDivider;
import com.eastfair.exhibitorapp.weight.SupportRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendDemandActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    /**
     * 发需求
     */
    @Bind(R.id.senddemand_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.img_title)
    ImageView img_send;
    @Bind(R.id.tv_jieshouduixiang)
    TextView tv_jieshouduixiang;
    @Bind(R.id.tv_choose)
    TextView tv_choose;//您选择了：
    @Bind(R.id.edit_miaoshu)
    EditText edit_miaoshu;
    @Bind(R.id.layout_addphoto)
    LinearLayout layout_addphoto;
    @Bind(R.id.img_photo)
    ImageView img_photo;
    @Bind(R.id.scanrecord)
    SupportRecyclerView recyclerView;

    @Bind(R.id.tv_title)
    TextView tv_title;

    private MyRecyclerviewAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senddemand);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    public void setListener() {
//        radioGroup.setOnCheckedChangeListener(this);
        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //选择接收对象
    @OnClick(R.id.tv_jieshouduixiang)
    public void choose() {

    }

    @OnClick(R.id.layout_addphoto)
    public void addphoto() {
        img_photo.setVisibility(View.VISIBLE);
        img_photo.setImageResource(R.mipmap.ic_launcher);
    }

    @OnClick(R.id.img_title)
    public void send() {
       Toast.makeText(SendDemandActivity.this,"发送",Toast.LENGTH_SHORT).show();
    }


    private void initView() {
        text_Title.setText("发需求");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        tp = tv_title.getPaint();
        tp.setFakeBoldText(true);
        toolbar_title.setNavigationIcon(R.mipmap.back);
        img_send.setImageResource(R.mipmap.send);
        getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SendDemandActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                SendDemandActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
    }

    private void getData() {
        mData = new ArrayList<>();
        for(int i = 0;i<100;i++){
            mData.add("data"+i);
        }
        if(mData == null){
            recyclerView.setEmptyView(findViewById(R.id.empty_view));
        }
        mAdapter = new MyRecyclerviewAdapter(SendDemandActivity.this,mData){

            @Override
            public void onBindViewHolder(MyRecyclerviewHolder holder, int position) {
                bindData(holder, position, mData.get(position));
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.scanrecord_item;
            }

            @Override
            public void bindData(MyRecyclerviewHolder holder, int position, Object item) {
                if(item ==null){
                    return;
                }
                holder.setText(R.id.tv_gsname,  item.toString());
            }
        };
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(SendDemandActivity.this,"onItemClick",Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnItemLongClickListener(new MyRecyclerviewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View itemView, int pos) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //获取变更后的选中项的ID
        int radioButtonId = group.getCheckedRadioButtonId();
        //根据ID获取RadioButton的实例
        RadioButton rb = (RadioButton) SendDemandActivity.this.findViewById(radioButtonId);
        //更新文本内容，以符合选中项
        Toast.makeText(SendDemandActivity.this, "您选择的是：" + rb.getText(), Toast.LENGTH_SHORT).show();
    }
}
