package com.community.my.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class RestFile implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int i_user;
	private MultipartFile profile_img;

	public int getI_user() {
		return i_user;
	}

	public void setI_user(int i_user) {
		this.i_user = i_user;
	}

	public MultipartFile getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(MultipartFile profile_img) {
		this.profile_img = profile_img;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
