package com.eastfair.exhibiterapp.ui.activity.capture;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import me.xiaopan.android.barcode.BarcodeScanner;
import me.xiaopan.android.barcode.BarcodeScanner.BarcodeScanCallback;
import me.xiaopan.android.barcode.widget.ScanAreaView;
import me.xiaopan.android.graphics.RectUtils;
import me.xiaopan.android.hardware.DeviceUtils;
import me.xiaopan.android.hardware.camera.BestPreviewSizeCalculator;
import me.xiaopan.android.hardware.camera.CameraManager;
import me.xiaopan.android.view.ViewUtils;
import me.xiaopan.android.view.WindowUtils;
import me.xiaopan.java.util.DateTimeUtils;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.eastfair.exhibiterapp.R;
import com.eastfair.exhibiterapp.base.BaseActivity;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;

/**
 * 条码扫描仪
 * 
 * @author wangyan
 * 
 */
public class BarcodeScannerActivity extends BaseActivity implements OnCheckedChangeListener {
	private static final String STATE_FLASH_CHECKED = "STATE_FLASH_CHECKED";
	private int beepId;// 哔哔音效
	private BarcodeScanner barcodeScanner; // 解码器
	private SoundPool soundPool;// 音效池
	private CameraManager cameraManager;
	private OpenCameraRunnable openCameraRunnable;
	private SurfaceView surfaceView; // 显示画面的视图
	private CheckBox flashButton;
	private ScanAreaView scanAreaView;// 扫描框（取景器）
	private String subUrl;
	private String codeDate;
	private Handler handlerScan;
	private String scanInforUrl;
//	FileHelper helper;
	private String codeOld;

	@Override
	public void findViews() {
		setContentView(R.layout.activity_barcode_scan);
		surfaceView = (SurfaceView) findViewById(R.id.surface_barcodeScanner);
		scanAreaView = (ScanAreaView) findViewById(R.id.scanningFrame_barcodeScanner);
		flashButton = (CheckBox) findViewById(R.id.checkBox_barcodeScanner_flash);
		TextView tv_sm = (TextView) findViewById(R.id.tv_sm);
		tv_sm.setText("请将条码置于扫描框内");
//		helper = new FileHelper(getApplicationContext());
	}

	@Override
	public void registerEvents() {
		flashButton.setOnCheckedChangeListener(this);
	}

	@Override
	public void init() {

		ScanMethod();

	}

	/**
	 * 开始扫码
	 */
	private void ScanMethod() {
		openCameraRunnable = new OpenCameraRunnable();
		cameraManager = new CameraManager(this, surfaceView.getHolder(), new MyCameraCallback());
		barcodeScanner = new BarcodeScanner(getBaseContext(), new MyBarcodeScanCallback());
		// 初始化音效
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		beepId = soundPool.load(getBaseContext(), R.raw.qrcode, 100);
		handlerScan = new Handler();

	}

	@Override
	protected void onResume() {
		super.onResume();
		handlerScan.postDelayed(openCameraRunnable, 100);
	}

	@Override
	protected void onPause() {
		super.onPause();
		handlerScan.removeCallbacks(openCameraRunnable);
		cameraManager.release();
	}

	@Override
	protected void onDestroy() {
		soundPool.release();
		barcodeScanner.release();
		super.onDestroy();
	}

	private class MyCameraCallback implements CameraManager.CameraCallback {
		@Override
		public void onInitCamera(Camera camera) {
			// 设置预览分辨率
			Camera.Parameters parameters = camera.getParameters();
			BestPreviewSizeCalculator bestPreviewSizeCalculator = new BestPreviewSizeCalculator(
					getBaseContext(), parameters.getSupportedPreviewSizes());
			Point screenSize = DeviceUtils.getScreenSize(getBaseContext());
			bestPreviewSizeCalculator
					.setMinPreviewSizePixels((int) ((screenSize.x * screenSize.y) * 0.8f));
			Size previewSize = bestPreviewSizeCalculator.getPreviewSize();
			if (previewSize != null) {
				parameters.setPreviewSize(previewSize.width, previewSize.height);
				camera.setParameters(parameters);
			} else {
				previewSize = parameters.getPreviewSize();
			}

			barcodeScanner.setCamera(camera);
			barcodeScanner.setScanAreaRectInPreview(RectUtils.mappingRect(ViewUtils
					.getRelativeRect(scanAreaView, surfaceView), new Point(surfaceView.getWidth(),
					surfaceView.getHeight()), new Point(previewSize.width, previewSize.height),
					WindowUtils.isPortrait(getBaseContext())));
		}

		@Override
		public void onStartPreview() {
			scanAreaView.startRefresh(); // 开始刷新扫描框
			barcodeScanner.start(); // 开始解码
		}

		@Override
		public void onStopPreview() {
			scanAreaView.stopRefresh(); // 停止刷新扫描框
			barcodeScanner.stop();// 停止解码器
			cameraManager.getLoopFocusManager().stop();

		}
	}

	private class MyBarcodeScanCallback implements BarcodeScanCallback {
		private boolean first = true;

		@Override
		public boolean onDecodeCallback(Result result, byte[] arg1, float arg2) {
			if (result != null) {
				scanAreaView.stopRefresh();
				cameraManager.getLoopFocusManager().stop();

				// 播放音效
				// AudioManager audioManager = (AudioManager)
				// getSystemService(AUDIO_SERVICE);
				// if (audioManager.getRingerMode() ==
				// AudioManager.RINGER_MODE_NORMAL) {
				// float volume = (float) (((float) audioManager
				// .getStreamVolume(AudioManager.STREAM_MUSIC) / 15) / 3.0);
				// soundPool.play(beepId, volume, volume, 100, 0, 1);
				// }

				AudioManager mgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_SYSTEM);
				float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
				float volume = streamVolumeCurrent / streamVolumeMax;
				soundPool.play(beepId, volume, volume, 100, 0, 1);
				// 发出震动提示
				((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(200);// 发出震动提示
				// 处理结果
				parse(result.getText());
				return false;
			} else {
				cameraManager.getLoopFocusManager().start(!first); // 延迟启动循环对焦
				first = false;
				return true; // 继续扫描
			}
		}

		@Override
		public void onFoundPossibleResultPoint(ResultPoint arg0) {
			scanAreaView.addResultPoint(arg0);
		}
	}

	private class OpenCameraRunnable implements Runnable {
		@Override
		public void run() {
			if (cameraManager != null) {
				try {
					cameraManager.openBackCamera();
					if (!cameraManager.setTorchFlash(flashButton.isChecked())) {
						toastS(R.string.toast_barcodeScanner_notSupport);
						flashButton.setChecked(!flashButton.isChecked());
					}
				} catch (Exception e) {
					e.printStackTrace();
					toastS("打开摄像头失败，请确保其他程序没有占用您的摄像头!");
					finish();
				}
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(STATE_FLASH_CHECKED, flashButton.isChecked());
		super.onSaveInstanceState(outState);
	}

	/**
	 * 处理扫码结果，将扫码后的字符串截取，将所需要的字符串拼接url，提交接口，接口返回互换名片成功，失败等
	 * 
	 * @param code
	 */
	private void parse(String code) {
		Log.d("cyd", code);
		codeDate = code;

		new Thread(runnable).start();
	}

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			saveDate(codeDate);
		}
	};

	private void saveDate(String code) {
		/*if (helper.hasSD()) {
			String dateN = DateTimeUtils.getCurrentYear() + "年" + (DateTimeUtils.getCurrentMonth())
					+ "月" + DateTimeUtils.getCurrentDay() + "日" + ".txt";
			File path = Environment.getExternalStorageDirectory();// 获得SD卡路径
			File[] files = path.listFiles();// 读取
			try {
				File fileName = helper.createSDFileDir("ScanGun");
				fileName = helper.createSDFile(fileName.getPath(), dateN);
				if (code.equals(codeOld)) {
					handler2.sendEmptyMessage(1);
				} else {
					helper.writeSDFile(
							"" + code + ","
									+ DateTimeUtils.getCurrentDateTimeByDefult24HourFormat(),
							fileName.getPath());
					codeOld = code;
					handler2.sendEmptyMessage(2);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} else {
			handler2.sendEmptyMessage(3);
		}*/
	}

	/**
	 * 播放音效
	 */
	private void playSound() {
		// 播放音效
		AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
			float volume = (float) (((float) audioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC) / 15) / 3.0);
			soundPool.play(beepId, volume, volume, 100, 0, 1);
		}
	}

	/**
	 * 震动
	 */
	private void playVibrator() {
		((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(200);// 发出震动提示
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (!cameraManager.setTorchFlash(isChecked)) {
			toastS("抱歉，您的设备不支持此功能!");
			flashButton.setChecked(!flashButton.isChecked());
		}

	}

	private void startDecode() {
		scanAreaView.startRefresh(); // 开始刷新扫描框
		barcodeScanner.start();
	}

	/**
	 * 点击时弹出popuwind，线程
	 */
	Handler handler2 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 1:
					toastS("已扫描过，无需重复扫描！");
					initHandle();
					break;
				case 2:
					toastS("已扫描成功！");

					initHandle();
					break;
				case 3:
					toastS("手机无内存空间，请插入外部SD存储卡");
					initHandle();
					break;

			}
		};
	};

	private void initHandle() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				scanAreaView.startRefresh(); // 开始刷新扫描框
				barcodeScanner.start(); // 开始解码
				// handlerScan.postDelayed(openCameraRunnable, 100);
			}
		}, 1000);
	}

	private static Boolean bExit = false;

	private void mainDestroy() {
		if (bExit == false) {
			final Timer TTimer = new Timer();
			TTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					TTimer.cancel();
					bExit = false;
				}
			}, 2000);
			Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
			bExit = true;
		} else {
			finish();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO 自动生成的方法存根
		// super.onBackPressed();
		mainDestroy();
	}
}