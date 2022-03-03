package com.otlb.semi.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.otlb.semi.emp.model.vo.Emp;

public class Board extends BoardEntity implements Serializable {

	private int attachCount; // 첨부 파일수
	private List<Attachment> attachments;
	private int commentCount; //댓글 수
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int no, String title, String content, Timestamp regDate, int readCount, int likeCount, String reportYn,
			int empNo, String category, String deleteYn, Emp emp) {
		super(no, title, content, regDate, readCount, likeCount, reportYn, empNo, category, deleteYn, emp);
		// TODO Auto-generated constructor stub
	}
	public Board(int attachCount, List<Attachment> attachments) {
		super();
		this.attachCount = attachCount;
		this.attachments = attachments;
	}
	public int getAttachCount() {
		return attachCount;
	}
	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return super.toString() + ", Board [attachCount=" + attachCount + ", attachments=" + attachments + ", commentCount=" + commentCount
				+ "]";
	}

	
}
