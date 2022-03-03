package com.otlb.semi.report.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bulletinId;
	private int boardNo;
	private Date reportDate;
	private int reportTypeId;
	private String isSolved;
	private int reporterId;
	
	public Report() {
		super();
	}

	public Report(String bulletinId, int boardNo, Date reportDate, int reportTypeId, String isSolved, int reporterId) {
		super();
		this.bulletinId = bulletinId;
		this.boardNo = boardNo;
		this.reportDate = reportDate;
		this.reportTypeId = reportTypeId;
		this.isSolved = isSolved;
		this.reporterId = reporterId;
	}

	public String getBulletinId() {
		return bulletinId;
	}

	public void setBulletinId(String bulletinId) {
		this.bulletinId = bulletinId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public int getReportTypeId() {
		return reportTypeId;
	}

	public void setReportTypeId(int reportTypeId) {
		this.reportTypeId = reportTypeId;
	}

	public String getIsSolved() {
		return isSolved;
	}

	public void setIsSolved(String isSolved) {
		this.isSolved = isSolved;
	}

	public int getReporterId() {
		return reporterId;
	}

	public void setReporterId(int reporterId) {
		this.reporterId = reporterId;
	}

	@Override
	public String toString() {
		return "Report [bulletinId=" + bulletinId + ", boardNo=" + boardNo + ", reportDate=" + reportDate
				+ ", reportTypeId=" + reportTypeId + ", isSolved=" + isSolved + ", reporterId=" + reporterId + "]";
	}
	
}
