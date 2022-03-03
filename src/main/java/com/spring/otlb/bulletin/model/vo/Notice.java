package com.otlb.semi.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.otlb.semi.emp.model.vo.Emp;

public class Notice extends Bulletin implements Serializable {

	private int attachCount; // 첨부 파일수
	private List<Attachment> attachments;
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Notice(int no, String title, String content, Timestamp regDate, int readCount, int empNo, String deleteYn,
			Emp emp, int attachCount) {
		super(no, title, content, regDate, readCount, empNo, deleteYn, emp);
	}

	public Notice(int attachCount, List<Attachment> attachments) {
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
	@Override
	public String toString() {
		return super.toString() + "Notice [attachCount=" + attachCount + ", attachments=" + attachments + "]";
	}
	
}
