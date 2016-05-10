package com.eastfair.exhibiterapp.regist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.main.view.MainActivity;
import com.eastfair.exhibiterapp.util.DialogUtil;
import com.eastfair.exhibiterapp.util.SDUtil;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 名片信息
 */
public class CardInfoActivity extends BaseActivity {

    @Bind(R.id.img_photo)
    ImageView img_photo;
    @Bind(R.id.edit_name)
    EditText edit_name;
    @Bind(R.id.edit_gsname)
    EditText edit_gsname;
    @Bind(R.id.edit_job)
    EditText edit_job;
    @Bind(R.id.edit_email)
    EditText edit_email;
//    @Bind(R.id.edit_phonenum)
//    EditText edit_phonenum;
//    @Bind(R.id.edit_pass)
//    EditText edit_pass;
    @Bind(R.id.tv_choose_fenlei)
    TextView tv_choose_fenlei;
    @Bind(R.id.tv_choose_purpose)
    TextView tv_choose_purpose;
    @Bind(R.id.tv_choose_property)
    TextView tv_choose_property;
    @Bind(R.id.tv_choose_area)
    TextView tv_choose_area;
    @Bind(R.id.btn_yanzhengnum)
    Button btn_yanzhengnum;
//    @Bind(R.id.rv_phonemun)
//    RelativeLayout rv_phonemun;
    @Bind(R.id.toolbar_title)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;

    private String message;
//    private String verifytag;
    private String[] mData;

    private File tuTempFile;
    private File photoFile = new File(
            Environment.getExternalStorageDirectory(),
            SDUtil.getPhotoFileName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardinfo);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.img_photo)
    public void cardphoto() {
        final String[] mData = {"拍照", "从相册选取"};
        DialogUtil.showDialog(CardInfoActivity.this, "", mData, new DialogUtil.OnClickItemListener() {
            @Override
            public void onClickItem(int which) {
                if (mData[0].equals("拍照")) {
                    openTuKu3();
                } else if (mData[1].equals("从相册选取")) {
                    openPhotoHead();
                }
            }
        });
    }
    @OnClick(R.id.tv_choose_fenlei)
    public void choosefenlei(){
        message = "请选择您关注的展品分类";
        mData = new String[]{"床上用品区", "家居装饰用品区", "装饰布面料区", "辅料区"};
        dialogShow(message,mData,"fenlei");
    }



    @OnClick(R.id.tv_choose_purpose)
    public void choosepurpose(){
        message = "请选择参展目的";
        mData = new String[]{"洽谈合作", "询盘", "其他"};
        dialogShow(message,mData,"mudi");
    }

    @OnClick(R.id.tv_choose_property)
    public void chooseproperty(){
        message = "请选择业务性质";
        mData = new String[]{"采购商", "加盟商", "代理商", "生产商"};
        dialogShow(message,mData,"property");
    }

    @OnClick(R.id.tv_choose_area)
    public void choosearea(){
        message = "请选择一个城市";
        mData= new String[]{"广州", "上海", "北京", "香港", "澳门"};
        dialogShow(message,mData,"city");
    }

    @OnClick(R.id.btn_yanzhengnum)
    public void yanzhengnum(){
       /* if (verifytag.equals("2")) {
            SkipActivity(MainActivity.class);
        } else {
            Intent intent = new Intent(CardInfoActivity.this, VerifyPhoneActivity.class);
            *//**
             * verifytag = 1,有手机号，进入验证手机页面，直接发送验证码
             * verifytag = 2,无手机号，进入验证手机页面，需要填写手机号码，然后再发送验证码
             * *//*
            intent.putExtra("verifytag", "1");
            startActivity(intent);
        }*/
        SkipActivity(MainActivity.class);
        finish();
    }

    /**
     * 相册(pop窗口选择相册，参与裁切)
     */
    public void openTuKu3() {
        tuTempFile = new File("/mnt/sdcard/", "tmp_pic_"
                + SystemClock.currentThreadTimeMillis() + ".jpg");
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                "image/*");
        intent.putExtra("output", Uri.fromFile(tuTempFile));
        intent.putExtra("crop", "true");
        // 裁剪框比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 输出图片大小
        intent.putExtra("outputX", 180);
        intent.putExtra("outputY", 180);
        startActivityForResult(intent, 100);
    }

    /**
     * pop拍头像有裁切功能
     */
    private void openPhotoHead() {
        // 选择拍照
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        startActivityForResult(cameraintent, 101);
    }


    private void dialogShow(String message, final String[] data, final String type) {
        DialogUtil.showDialog(CardInfoActivity.this, message, data, new DialogUtil.OnClickItemListener() {
            @Override
            public void onClickItem(int which) {
                if(type.equals("fenlei")){
                    Toast.makeText(CardInfoActivity.this, "选择的展品分类为：" + data[which], Toast.LENGTH_SHORT).show();
                }else if(type.equals("mudi")){
                    Toast.makeText(CardInfoActivity.this, "选择的参展目的为：" + data[which], Toast.LENGTH_SHORT).show();
                }else if(type.equals("property")){
                    Toast.makeText(CardInfoActivity.this, "选择的业务性质为：" + data[which], Toast.LENGTH_SHORT).show();
                }else if(type.equals("city")){
                    Toast.makeText(CardInfoActivity.this, "选择的城市为：" + data[which], Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void initView() {
//        verifytag = getIntent().getExtras().getString("verifytag");
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("名片信息");
        toolbar_title.setNavigationIcon(R.mipmap.back);
       /* if (verifytag.equals("2")) {
            rv_phonemun.setVisibility(View.GONE);
            btn_yanzhengnum.setText("立即体验");
        }*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                toastS("拍照完成");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}