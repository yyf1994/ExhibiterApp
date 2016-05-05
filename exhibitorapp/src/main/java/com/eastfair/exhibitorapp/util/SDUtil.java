package com.eastfair.exhibitorapp.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

public class SDUtil {

	/**
	 * 获取sd卡状态。
	 * 
	 * @return true 可用，false 不可用。
	 */
	public static boolean SDCardState() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 获取sd卡的路径。
	 * 
	 * @return
	 */
	public static String SDCardPath() {
		if (SDCardState()) {
			String SDPATH = Environment.getExternalStorageDirectory().getPath();
			return SDPATH;
		} else {
			return null;
		}
	}

	/**
	 * 获取sd卡剩余空间
	 * 
	 * @return （以MB为单位）
	 */
	public static long SDCardFree() {
		if (null != SDCardPath() && !SDCardPath().equals("")) {
			StatFs statfs = new StatFs(SDCardPath());
			long availaBlocks = statfs.getAvailableBlocks();
			long blockSize = statfs.getBlockSize();
			long SDFreeSize = availaBlocks * blockSize / 1024 / 1024;
			return SDFreeSize;
		} else {
			return 0;
		}
	}
	// 使用系统当前日期加以调整作为照片的名称
	public static  String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}
/**
 * 清楚缓存webview
 */
    
   /** 
    * 清除WebView缓存 
    */ 
   public static void clearWebViewCache(Context context){  
          
       //清理Webview缓存数据库  
       try {  
    	   context.deleteDatabase("webview.db");   
    	   context.deleteDatabase("webviewCache.db");  
       } catch (Exception e) {  
           e.printStackTrace();  
       }  
          
       //WebView 缓存文件  
       File appCacheDir = new File(context.getFilesDir().getAbsolutePath()+"/webcache");  
       Log.e("web", "appCacheDir path="+appCacheDir.getAbsolutePath());  
          
       File webviewCacheDir = new File(context.getCacheDir().getAbsolutePath()+"/webviewCache");  
       Log.e("web", "webviewCacheDir path="+webviewCacheDir.getAbsolutePath());  
          
       //删除webview 缓存目录  
       if(webviewCacheDir.exists()){  
           deleteFile(webviewCacheDir);  
       }  
       //删除webview 缓存 缓存目录  
       if(appCacheDir.exists()){  
           deleteFile(appCacheDir);  
       }  
   }  
      
   /** 
    * 递归删除 文件/文件夹 
    *  
    * @param file 
    */ 
   public static void deleteFile(File file) {  
  
       Log.i("webview", "delete file path=" + file.getAbsolutePath());  
          
       if (file.exists()) {  
           if (file.isFile()) {  
               file.delete();  
           } else if (file.isDirectory()) {  
               File files[] = file.listFiles();  
               for (int i = 0; i < files.length; i++) {  
                   deleteFile(files[i]);  
               }  
           }  
           file.delete();  
       } else {  
           Log.e("webview", "delete file no exists " + file.getAbsolutePath());  
       }  
   }  

}
