package com.eastfair.exhibiterapp.ui.activity.regist;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import android.view.WindowManager;
import android.widget.ImageView;

import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.util.DialogUtil;
import com.eastfair.exhibiterapp.util.SDUtil;

import java.io.File;

/**
 * 注册
 * */
public class RegistActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_photo;
    private TextView text_nocard;
    private Toolbar toolbar_title;
    private TextView text_Title ;

//    TextView tv_photonew, tv_pic, tv_no;
//    private PopupWindow p2;
//    private View popView2;
    private File tuTempFile;
    private File photoFile = new File(
            Environment.getExternalStorageDirectory(),
            SDUtil.getPhotoFileName());

    @Override
    public void findViews() {
        setContentView(R.layout.activity_regist);
        /**
         * 点击头像按钮弹出的窗口
         */
    /*    popView2 = getLayoutInflater().inflate(R.layout.photo_pop, null);
        p2 = new PopupWindow(popView2, WindowManager.LayoutParams.FILL_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        p2.setBackgroundDrawable(new BitmapDrawable());
        p2.setOutsideTouchable(true);
        p2.setFocusable(true);
        tv_photonew = (TextView) popView2.findViewById(R.id.tv_photonew);
        tv_pic = (TextView) popView2.findViewById(R.id.tv_pic);
        tv_no = (TextView) popView2.findViewById(R.id.tv_no);
        setTextPOP();*/
        initView();
    }

    @Override
    public void registerEvents() {
        img_photo.setOnClickListener(this);
        text_nocard.setOnClickListener(this);
       /* tv_photonew.setOnClickListener(this);
        tv_pic.setOnClickListener(this);
        tv_no.setOnClickListener(this);*/
    }

    @Override
    public void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_photo:
//                displaywindow();
                final String[] mData = {"拍照", "从相册选取"};
                DialogUtil.showDialog(RegistActivity.this, "", mData, new DialogUtil.OnClickItemListener() {
                    @Override
                    public void onClickItem(int which) {
                        if(mData[0].equals("拍照")){
                            openTuKu3();
                        }else if(mData[1].equals("从相册选取")){
                            openPhotoHead();
                        }
                    }
                });
                break;
            case R.id.text_nocard:
                Intent intent = new Intent(RegistActivity.this,VerifyPhoneActivity.class);
                /**
                 * verifytag = 1,有手机号，进入验证手机页面，直接发送验证码
                 * verifytag = 2,无手机号，进入验证手机页面，需要填写手机号码，然后再发送验证码
                 * */
                intent.putExtra("verifytag","2");
                startActivity(intent);
                finish();
                break;
       /*     case R.id.tv_pic:
                openTuKu3();
                p2.dismiss();
                break;
            case R.id.tv_photonew:
                openPhotoHead();
                p2.dismiss();
                break;
            case R.id.tv_no:
                p2.dismiss();
                break;*/

        }
    }

    private void initView() {
        img_photo = (ImageView) findViewById(R.id.img_photo);
        text_nocard = (TextView) findViewById(R.id.text_nocard);
        toolbar_title = (Toolbar) findViewById(R.id.toolbar_title);
        text_Title = (TextView) toolbar_title.findViewById(R.id.text_title);
        setSupportActionBar(toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        text_Title.setText("注册");


    }
//    public void setTextPOP() {
//        tv_photonew.setText("拍照");
//        tv_pic.setText("从相册选取");
//        tv_no.setText( "取消");
//    }
    /**
     * 弹出pop
     */
//    private void displaywindow() {
//        p2.showAtLocation(img_photo, Gravity.BOTTOM, 0, 0);
//        p2.setAnimationStyle(R.style.PopupAnimation);
//    }

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
                Intent intent = new Intent(RegistActivity.this,CardInfoActivity.class);
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
}