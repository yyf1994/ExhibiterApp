package com.eastfair.exhibiterapp.ui.activity.regist;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.ui.activity.MainActivity;
import com.eastfair.exhibiterapp.util.DialogUtil;

/**
 * 名片信息
 */
public class CardInfoActivity extends BaseActivity implements View.OnClickListener {

    private EditText edit_name, edit_gsname, edit_job, edit_email, edit_phonenum, edit_pass;
    private TextView tv_choose_fenlei, tv_choose_purpose, tv_choose_property, tv_choose_area;
    private Button btn_yanzhengnum;
    private RelativeLayout rv_phonemun;

    private Toolbar toolbar_title;
    private TextView text_Title;

    private String message;
    private String verifytag;


    @Override
    public void findViews() {
        setContentView(R.layout.activity_cardinfo);
        initView();
    }

    @Override
    public void registerEvents() {
        tv_choose_fenlei.setOnClickListener(this);
        tv_choose_purpose.setOnClickListener(this);
        tv_choose_property.setOnClickListener(this);
        tv_choose_area.setOnClickListener(this);
        btn_yanzhengnum.setOnClickListener(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_choose_fenlei:
                message = "请选择您关注的展品分类";
                final String[] fenlei = {"床上用品区", "家居装饰用品区", "装饰布面料区", "辅料区"};
                DialogUtil.showDialog(CardInfoActivity.this, message, fenlei, new DialogUtil.OnClickItemListener() {
                    @Override
                    public void onClickItem(int which) {
                        Toast.makeText(CardInfoActivity.this, "选择的展品分类为：" + fenlei[which], Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.tv_choose_purpose:
                message = "请选择参展目的";
                final String[] mudi = {"洽谈合作", "询盘", "其他"};
                DialogUtil.showDialog(CardInfoActivity.this, message, mudi, new DialogUtil.OnClickItemListener() {
                    @Override
                    public void onClickItem(int which) {
                        Toast.makeText(CardInfoActivity.this, "选择的参展目的为：" + mudi[which], Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.tv_choose_property:
                message = "请选择业务性质";
                final String[] property = {"采购商", "加盟商", "代理商", "生产商"};
                DialogUtil.showDialog(CardInfoActivity.this, message, property, new DialogUtil.OnClickItemListener() {
                    @Override
                    public void onClickItem(int which) {
                        Toast.makeText(CardInfoActivity.this, "选择的业务性质为：" + property[which], Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.tv_choose_area:
                message = "请选择一个城市";
                final String[] cities = {"广州", "上海", "北京", "香港", "澳门"};
                DialogUtil.showDialog(CardInfoActivity.this, message, cities, new DialogUtil.OnClickItemListener() {
                    @Override
                    public void onClickItem(int which) {
                        Toast.makeText(CardInfoActivity.this, "选择的城市为：" + cities[which], Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_yanzhengnum:
                if(verifytag.equals("2")){
                    SkipActivity(MainActivity.class);
                }else{
                    Intent intent = new Intent(CardInfoActivity.this, VerifyPhoneActivity.class);
                    /**
                     * verifytag = 1,有手机号，进入验证手机页面，直接发送验证码
                     * verifytag = 2,无手机号，进入验证手机页面，需要填写手机号码，然后再发送验证码
                     * */
                    intent.putExtra("verifytag", "1");
                    startActivity(intent);
                }
                finish();
                break;

        }
    }

    private void initView() {
        verifytag = getIntent().getExtras().getString("verifytag");
        rv_phonemun = (RelativeLayout) findViewById(R.id.rv_phonemun);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_gsname = (EditText) findViewById(R.id.edit_gsname);
        edit_job = (EditText) findViewById(R.id.edit_job);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_phonenum = (EditText) findViewById(R.id.edit_phonenum);
        edit_pass = (EditText) findViewById(R.id.edit_pass);
        tv_choose_fenlei = (TextView) findViewById(R.id.tv_choose_fenlei);
        tv_choose_purpose = (TextView) findViewById(R.id.tv_choose_purpose);
        tv_choose_property = (TextView) findViewById(R.id.tv_choose_property);
        tv_choose_area = (TextView) findViewById(R.id.tv_choose_area);
        btn_yanzhengnum = (Button) findViewById(R.id.btn_yanzhengnum);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("名片信息");
        if(verifytag.equals("2")){
            rv_phonemun.setVisibility(View.GONE);
            btn_yanzhengnum.setText("立即体验");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}