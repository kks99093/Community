package com.community.my.board.model;

public class BoardVO {
	private int i_board;
	private String title;
	private String content;
	private int i_user;
	private String r_dt;
	private int like_cnt;
	private int cnt;
	private int i_category;
	
	
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public int getI_board() {
		return i_board;
	}
	public void setI_board(int i_board) {
		this.i_board = i_board;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getI_category() {
		return i_category;
	}
	public void setI_category(int i_category) {
		this.i_category = i_category;
	}


	

	

}
