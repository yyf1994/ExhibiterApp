package com.eastfair.exhibiterapp.weight;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 发帖时用的添加图片
 */
public class Picture {
	@Expose
	@SerializedName("uri")
	private String uri;

	private Picture() {

	}

	public String getUri() {
		return uri;
	}

	private void setUri(String uri) {
		this.uri = uri;
	}

	public static Picture buildNormalPicture(String uri) {
		Picture picture = new Picture();
		picture.setUri(uri);
		return picture;
	}
}
