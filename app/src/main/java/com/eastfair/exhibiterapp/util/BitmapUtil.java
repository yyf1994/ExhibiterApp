package com.eastfair.exhibiterapp.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class BitmapUtil {
	public static class Size {
		public int width;
		public int height;

		public Size(int w, int h) {
			this.width = w;
			this.height = h;
		}
	}

	public static Bitmap getImage(String absPath) {
		Bitmap bitmap = BitmapFactory.decodeFile(absPath);
		return bitmap;
	}

	public static Size getImageSize(String absPath) {
		Options options = new Options();
		options.inPreferredConfig = Config.ALPHA_8;
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(absPath, options);
		Size size = new Size(options.outWidth, options.outHeight);
		return size;
	}

	public static Bitmap blur(Bitmap bitmap) {
		int iterations = 1;
		int radius = 8;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int[] inPixels = new int[width * height];
		int[] outPixels = new int[width * height];
		Bitmap blured = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		bitmap.getPixels(inPixels, 0, width, 0, 0, width, height);
		for (int i = 0; i < iterations; i++) {
			blur(inPixels, outPixels, width, height, radius);
			blur(outPixels, inPixels, height, width, radius);
		}
		blured.setPixels(inPixels, 0, width, 0, 0, width, height);
		return blured;
	}

	private static void blur(int[] in, int[] out, int width, int height,
			int radius) {
		int widthMinus1 = width - 1;
		int tableSize = 2 * radius + 1;
		int divide[] = new int[256 * tableSize];

		for (int index = 0; index < 256 * tableSize; index++) {
			divide[index] = index / tableSize;
		}

		int inIndex = 0;

		for (int y = 0; y < height; y++) {
			int outIndex = y;
			int ta = 0, tr = 0, tg = 0, tb = 0;

			for (int i = -radius; i <= radius; i++) {
				int rgb = in[inIndex + clamp(i, 0, width - 1)];
				ta += (rgb >> 24) & 0xff;
				tr += (rgb >> 16) & 0xff;
				tg += (rgb >> 8) & 0xff;
				tb += rgb & 0xff;
			}

			for (int x = 0; x < width; x++) {
				out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16)
						| (divide[tg] << 8) | divide[tb];

				int i1 = x + radius + 1;
				if (i1 > widthMinus1)
					i1 = widthMinus1;
				int i2 = x - radius;
				if (i2 < 0)
					i2 = 0;
				int rgb1 = in[inIndex + i1];
				int rgb2 = in[inIndex + i2];

				ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
				tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
				tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
				tb += (rgb1 & 0xff) - (rgb2 & 0xff);
				outIndex += height;
			}
			inIndex += width;
		}
	}

	private static int clamp(int x, int a, int b) {
		return (x < a) ? a : (x > b) ? b : x;
	}

	/**
	 * 获取图片文件的信息，是否旋转了90度，如果是则反转
	 * 
	 * @param bitmap
	 *            需要旋转的图片
	 * @param path
	 *            图片的路径
	 */
	public static Bitmap reviewPicRotate(Bitmap bitmap, String path) {
		int degree = getPicRotate(path);
		if (degree != 0) {
			Matrix m = new Matrix();
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			m.setRotate(degree); // 旋转angle度
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);// 从新生成图片
		}
		return bitmap;
	}

	/**
	 * 读取图片文件旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return 图片旋转的角度
	 */
	public static int getPicRotate(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 根据路径获得突破并压缩返回bitmap用于显示,传名片
	 * 
	 * @return
	 */
	public static Bitmap getSmallBitmap(String filePath) {
		final Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = calculateInSampleSize(options, 500, 500);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

	/**
	 * 我的名片--上传名片
	 * 
	 * @return
	 */
	public static Bitmap getSmallBitmapMP(String filePath) {
		final Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = calculateInSampleSize(options, 400, 690);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

	/**
	 * 根据路径获得突破并压缩返回bitmap用于显示---传头像
	 * 
	 * @return
	 */
	// public static Bitmap getSmallHeadBitmap(String filePath) {
	// final BitmapFactory.Options options = new BitmapFactory.Options();
	// options.inJustDecodeBounds = true;
	// BitmapFactory.decodeFile(filePath, options);
	// options.inSampleSize = calculateInSampleSize(options,
	// Integer.parseInt(RAMconstant.sizeHead),
	// Integer.parseInt(RAMconstant.sizeHead));
	// options.inJustDecodeBounds = false;
	// return BitmapFactory.decodeFile(filePath, options);
	// }
	/**
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 保存方法
	 */
	public static void saveBitmap(Bitmap bitmap, String path) {
		File f = new File(path);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
			out.flush();
			out.close();
			// bitmap1.recycle();
			Log.i("...log...saveBitmap", "已经保存: " + path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * bitmap转为base64
	 * 
	 * @param bitmap
	 * @return
	 */
	public static String bitmapToBase64(Bitmap bitmap) {

		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			if (bitmap != null) {
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.flush();
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * base64转为bitmap
	 * 
	 * @param base64Data
	 * @return
	 */
	public static Bitmap base64ToBitmap(String base64Data) {
		byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	/**
	 * 压缩图片
	 * 
	 * @param bitmap
	 *            源图片
	 * @param width
	 *            想要的宽度
	 * @param height
	 *            想要的高度
	 * @param isAdjust
	 *            是否自动调整尺寸, true图片就不会拉伸，false严格按照你的尺寸压缩
	 * @return Bitmap
	 */
	public static Bitmap reduce(Bitmap bitmap, int width, int height, boolean isAdjust) {
		// 如果想要的宽度和高度都比源图片小，就不压缩了，直接返回原图
		if (bitmap.getWidth() < width && bitmap.getHeight() < height) {
			return bitmap;
		}
		// 根据想要的尺寸精确计算压缩比例, 方法详解：public BigDecimal divide(BigDecimal divisor,
		// int scale, int roundingMode);
		// scale表示要保留的小数位, roundingMode表示如何处理多余的小数位，BigDecimal.ROUND_DOWN表示自动舍弃
		float sx = new BigDecimal(width).divide(
				new BigDecimal(bitmap.getWidth()), 4, BigDecimal.ROUND_DOWN)
				.floatValue();
		float sy = new BigDecimal(height).divide(
				new BigDecimal(bitmap.getHeight()), 4, BigDecimal.ROUND_DOWN)
				.floatValue();
		if (isAdjust) {// 如果想自动调整比例，不至于图片会拉伸
			sx = (sx < sy ? sx : sy);
			sy = sx;// 哪个比例小一点，就用哪个比例
		}
		Matrix matrix = new Matrix();
		matrix.postScale(sx, sy);// 调用api中的方法进行压缩，就大功告成了
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
	}

}
