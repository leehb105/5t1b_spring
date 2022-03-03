package com.otlb.semi.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String originalFilename;		// 사용자가 업로드한 파일명
	private String renamedFilename;			// 서버컴퓨터에 저장된 파일명
	private Date regDate;
	private int boardNo;
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Attachment(int no, String originalFilename, String renamedFilename, Date regDate, int boardNo) {
		super();
		this.no = no;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.regDate = regDate;
		this.boardNo = boardNo;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	@Override
	public String toString() {
		return "Attachment [no=" + no + ", originalFilename=" + originalFilename + ", renamedFilename="
				+ renamedFilename + ", regDate=" + regDate + ", boardNo=" + boardNo + "]";
	}

}
