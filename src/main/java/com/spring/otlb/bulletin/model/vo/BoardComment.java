package com.otlb.semi.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.otlb.semi.emp.model.vo.Emp;

public class BoardComment extends BoardCommentEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private Emp emp;
	
	public BoardComment() {
		super();
	}

	public BoardComment(int no, int commentLevel, String content, String reportYn, int commentRef, Timestamp regDate,
			int boardNo, int empNo, String deleteYn) {
		super(no, commentLevel, content, reportYn, commentRef, regDate, boardNo, empNo, deleteYn);
	}

	public BoardComment(Emp emp) {
		super();
		this.emp = emp;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return super.toString() +  ", BoardComment [emp=" + emp + "]";
	}

}
