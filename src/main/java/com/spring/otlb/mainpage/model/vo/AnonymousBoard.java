package com.otlb.semi.mainpage.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class AnonymousBoard implements Serializable {
	private static final long serialVersionUID = 1L;

	private int no;
	private String title;
	private String content;
	private String category;
	private Timestamp reg_date;
	private int read_count;
	private int like_count; // 추천수
	private int user_no;
	
	public AnonymousBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnonymousBoard(int no, String title, String content, String category, Timestamp reg_date, int read_count,
			int like_count, int user_no) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.category = category;
		this.reg_date = reg_date;
		this.read_count = read_count;
		this.like_count = like_count;
		this.user_no = user_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AnonymousBoard [no=" + no + ", title=" + title + ", content=" + content + ", category=" + category
				+ ", reg_date=" + reg_date + ", read_count=" + read_count + ", like_count=" + like_count + ", user_no="
				+ user_no + "]";
	}
	

}
