package com.eastfair.exhibiterapp.regist;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.eastfair.exhibiterapp.util.BitmapUtil;
import com.eastfair.exhibiterapp.util.SDUtil;
import com.eastfair.exhibiterapp.weight.Picture;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.xiaopan.android.content.FileUtils;
import me.xiaopan.android.content.IntentUtils;
import me.xiaopan.android.graphics.BitmapDecoder;

/**
 * 注册
 */
public class RegistActivity extends BaseActivity implements ActionSheet.ActionSheetListener{
    @Bind(R.id.img_photo)
    ImageView img_photo;
    @Bind(R.id.img_nocard)
    ImageView img_nocard;
    @Bind(R.id.regist_toolbar)
    Toolbar toolbar_title;
    @Bind(R.id.text_title)
    TextView text_Title;

    private static final int REQUEST_CODE_SELECT = 5765;
    private static final int REQUEST_CODE_TAKE = 5763;

    private Picture picture = null;
    private File file = null;
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
        setTheme(R.style.ActionSheetStyleiOS7);
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("拍照", "从相册选取")
                .setCancelableOnTouchOutside(true).setListener(this).show();
      /*  final String[] mData = {"拍照", "从相册选取"};
        DialogUtil.showDialog(RegistActivity.this, "", mData, new DialogUtil.OnClickItemListener() {
            @Override
            public void onClickItem(int which) {
                if (mData[0].equals("拍照")) {
                    openTuKu3();
                } else if (mData[1].equals("从相册选取")) {
                    openPhotoHead();
                }
            }
        });*/
    }

    @OnClick(R.id.img_nocard)
    public void nocard() {
//        Intent intent = new Intent(RegistActivity.this, VerifyPhoneActivity.class);
        Intent intent = new Intent(RegistActivity.this, CardInfoActivity.class);
        /**
         * verifytag = 1,有手机号，进入验证手机页面，直接发送验证码
         * verifytag = 2,无手机号，进入验证手机页面，需要填写手机号码，然后再发送验证码
         * */
//        intent.putExtra("verifytag", "2");
        startActivity(intent);
        finish();
    }


    private void initView() {
        text_Title.setText("注册");
        toolbar_title.setNavigationIcon(R.mipmap.back);

    }

    private void openXC() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_SELECT);

    }


    /**
     * 名片识别 native://eastfair/photo?uid= 原生相机拍名片不参与裁切 名片识别
     */
    public void openCameraCardRecognition() {
        if (file == null) {
            file = FileUtils.getFileFromDynamicFilesDir(getBaseContext(),
                    System.currentTimeMillis() + ".jpg");
        }
        try {
            me.xiaopan.java.io.FileUtils.createFile(file);
            startActivityForResult(
                    IntentUtils.getTakePhotosIntent(Uri.fromFile(file)),
                    REQUEST_CODE_TAKE);
        } catch (IOException e) {
            e.printStackTrace();
            toastS("启动相机失败，因为创建文件失败");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            /*case RESULT_OK:
                toastS("拍照完成");
                Intent intent = new Intent(RegistActivity.this, CardInfoActivity.class);
                *//**
                 * verifytag = 1,有手机号，进入验证手机页面，直接发送验证码
                 * verifytag = 2,无手机号，进入验证手机页面，需要填写手机号码，然后再发送验证码
                 * verifytag = 0,没什么大作用 只是可以正常进入CardInfoActivity页面
                 * *//*
                intent.putExtra("verifytag", "0");
                startActivity(intent);
                break;*/
            case REQUEST_CODE_SELECT:
                ContentResolver resolver = getContentResolver();
                Uri uri = data.getData();
                if (uri == null) {
                    // toastS("没有取到文件URI");
                    return;
                }
                picture = Picture.buildNormalPicture(uri.toString());
                new Thread(runnable5).start();
                break;
            case REQUEST_CODE_TAKE:
                if (file == null || !file.exists()) {
                    return;
                }
                picture = Picture.buildNormalPicture(Uri.fromFile(file).toString());
                new Thread(runnable).start();
                file = null;
                break;
        }
    }

    /**
     * 请求上传拍名片 识别名片，和展商推广
     */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(RegistActivity.this,"runnable",Toast.LENGTH_SHORT).show();
          /*  if (base64String() != null) {
                    submit(base64String());
            }*/
        }
    };

    /**
     * 预登记相册上传
     */
    Runnable runnable5 = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(RegistActivity.this,"runnable5",Toast.LENGTH_SHORT).show();
//                submit(base64CardString());
        }
    };

    /**
     * 拍照后对图片的base64转String处理
     */
    protected String base64String() {
        String path = picture.getUri().replace("file:///", "");
        Bitmap bitmap = BitmapUtil.getSmallBitmap(path);
        bitmap = BitmapUtil.reviewPicRotate(bitmap, path);
        Log.e("wy...log...base", bitmap.getWidth() + "*" + bitmap.getHeight());
        String string = BitmapUtil.bitmapToBase64(bitmap);
        return string;
    }
    /**
     * 图片转base64 识别名片
     *
     * @param pictures64
     */
   /* private void submit(String pictures64) {
        try {
            if (RAMconstant.uid != null) {
                handler.sendEmptyMessage(1);
                NameValuePair pair1 = new BasicNameValuePair("uid",
                        RAMconstant.uid);
                NameValuePair pair2 = new BasicNameValuePair("img", pictures64);
                List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                pairList.add(pair1);
                pairList.add(pair2);
                HttpEntity requestHttpEntity = new UrlEncodedFormEntity(
                        pairList);
                HttpParams httpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpParams, 25000); // 设置连接超时为5秒
                HttpPost httpPost = new HttpPost(isChargeBase() ? RAMconstant.Url + "" + RAMconstant.cardPicUrl : RAMconstant.Url
                        + "En/"
                        + RAMconstant.cardPicUrl);
                Log.d("ch", RAMconstant.Url + "" + RAMconstant.cardPicUrl);
                Log.d("en", RAMconstant.Url
                        + "En"
                        + RAMconstant.cardPicUrl);
                // 将请求体内容加入请求中
                httpPost.setEntity(requestHttpEntity);
                // 需要客户端对象来发送请求
                HttpClient httpClient = new DefaultHttpClient(httpParams);
                // 发送请求
                HttpResponse response = httpClient.execute(httpPost);
                // 显示响应
                showResponseResult(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            handler.sendEmptyMessage(16);
        }
    }*/


    /**
     * 选取相册，转base64String
     */
    protected String base64CardString() {
        // 读取缩小版的图片
        Bitmap bitmap = new BitmapDecoder(800 * 800).decodeUri(
                getBaseContext(), Uri.parse(picture.getUri()));
        // 得到图片的字节数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                bitmap.getRowBytes() * bitmap.getHeight());
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        bitmap.recycle();
        byte[] data = byteArrayOutputStream.toByteArray();
        // BASE64加密
        String string = Base64.encodeToString(data, Base64.DEFAULT);
        return string;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        switch (index){
            case 0://拍照
                openCameraCardRecognition();
                break;
            case 1://从相册选取
                openXC();
                break;
            default:
                break;
        }
    }

}