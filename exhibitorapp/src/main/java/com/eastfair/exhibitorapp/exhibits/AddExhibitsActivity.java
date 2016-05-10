package com.eastfair.exhibitorapp.exhibits;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibitorapp.R;
import com.eastfair.exhibitorapp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExhibitsActivity extends BaseActivity {

    /**
     * 添加展品
     */
    @Bind(R.id.img_addphoto)
    ImageView img_addphoto;
    @Bind(R.id.edit_name)
    EditText edit_name;
    @Bind(R.id.edit_fenlei)
    EditText edit_fenlei;//选择分类
    @Bind(R.id.edit_persent)
    EditText edit_persent;

    @Bind(R.id.addexhibits_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;
    @Bind(R.id.btn_ok)
    Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexhibits);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    private void initView() {
        text_Title.setText("添加展品");
        TextPaint tp = text_Title.getPaint();
        tp.setFakeBoldText(true);
        toolbar_title.setNavigationIcon(R.mipmap.back);
    }
    private void setListener() {
        toolbar_title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * ok
     */
    @OnClick(R.id.btn_ok)
    public void queding() {
//        String name = edit_name.getText().toString();
//        String job = edit_fenlei.getText().toString();
//        String phonenum = edit_persent.getText().toString();
//        RegexChk ck = new RegexChk();
//        if(ck.checkPhone(phonenum)){
//            Toast.makeText(AddExhibitsActivity.this,"手机号输入不正确",Toast.LENGTH_SHORT).show();
//        }else if(job.equals("")){
//            Toast.makeText(AddExhibitsActivity.this,"请输入职位",Toast.LENGTH_SHORT).show();
//        }else if(name.equals("")){
//            Toast.makeText(AddExhibitsActivity.this,"请输入姓名",Toast.LENGTH_SHORT).show();
//        }else{
            SkipActivity(ExhibitsListActivity.class);
            finish();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
