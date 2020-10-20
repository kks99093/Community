package com.community.my.user.model;

public class UserParam extends UserVO{
	private String msg;
	private int auto_login;
		
	public int getAuto_login() {
		return auto_login;
	}

	public void setAuto_login(int auto_login) {
		this.auto_login = auto_login;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
