package com.otlb.semi.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BoardCommentEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int no;
	private int commentLevel;
	private String content;
	private String reportYn;
	private int commentRef;
	private Timestamp regDate;
	private int boardNo;
	private int empNo;
	private String deleteYn;
	
	public BoardCommentEntity() {
		super();
	}

	public BoardCommentEntity(int no, int commentLevel, String content, String reportYn, int commentRef,
			Timestamp regDate, int boardNo, int empNo, String deleteYn) {
		super();
		this.no = no;
		this.commentLevel = commentLevel;
		this.content = content;
		this.reportYn = reportYn;
		this.commentRef = commentRef;
		this.regDate = regDate;
		this.boardNo = boardNo;
		this.empNo = empNo;
		this.deleteYn = deleteYn;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReportYn() {
		return reportYn;
	}

	public void setReportYn(String reportYn) {
		this.reportYn = reportYn;
	}

	public int getCommentRef() {
		return commentRef;
	}

	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	@Override
	public String toString() {
		return "BoardCommentEntity [no=" + no + ", commentLevel=" + commentLevel + ", content=" + content
				+ ", reportYn=" + reportYn + ", commentRef=" + commentRef + ", regDate=" + regDate + ", boardNo="
				+ boardNo + ", empNo=" + empNo + ", deleteYn=" + deleteYn + "]";
	}
	
}
