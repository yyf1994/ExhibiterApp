package com.eastfair.exhibiterapp.ui.activity.regist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.util.DialogUtil;
import com.eastfair.exhibiterapp.util.SDUtil;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegistActivity extends BaseActivity {
    @Bind(R.id.img_photo)
    ImageView img_photo;
    @Bind(R.id.img_nocard)
    ImageView img_nocard;
    @Bind(R.id.toolbar_title)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;

    private File tuTempFile;
    private File photoFile = new File(
            Environment.getExternalStorageDirectory(),
            SDUtil.getPhotoFileName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.img_photo)
    public void cardphoto() {
        final String[] mData = {"拍照", "从相册选取"};
        DialogUtil.showDialog(RegistActivity.this, "", mData, new DialogUtil.OnClickItemListener() {
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

    @OnClick(R.id.img_nocard)
    public void nocard() {
        Intent intent = new Intent(RegistActivity.this, VerifyPhoneActivity.class);
        /**
         * verifytag = 1,有手机号，进入验证手机页面，直接发送验证码
         * verifytag = 2,无手机号，进入验证手机页面，需要填写手机号码，然后再发送验证码
         * */
        intent.putExtra("verifytag", "2");
        startActivity(intent);
        finish();
    }


    private void initView() {
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("注册");
        toolbar_title.setNavigationIcon(R.mipmap.back);


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
                Intent intent = new Intent(RegistActivity.this, CardInfoActivity.class);
                /**
                 * verifytag = 1,有手机号，进入验证手机页面，直接发送验证码
                 * verifytag = 2,无手机号，进入验证手机页面，需要填写手机号码，然后再发送验证码
                 * verifytag = 0,没什么大作用 只是可以正常进入CardInfoActivity页面
                 * */
                intent.putExtra("verifytag", "0");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}