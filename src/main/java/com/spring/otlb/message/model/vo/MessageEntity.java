package com.otlb.semi.message.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int no;
	private String content;
	private int senderEmpNo;
	private int receiverEmpNo;
	private Timestamp sentDate;
	private Timestamp readDate;
	private String senderDelYn;
	private String receiverDelYn;
	
	public MessageEntity() {
		super();
	}

	public MessageEntity(int no, String content, int senderEmpNo, int receiverEmpNo, Timestamp sentDate, Timestamp readDate,
			String senderDelYn, String receiverDelYn) {
		super();
		this.no = no;
		this.content = content;
		this.senderEmpNo = senderEmpNo;
		this.receiverEmpNo = receiverEmpNo;
		this.sentDate = sentDate;
		this.readDate = readDate;
		this.senderDelYn = senderDelYn;
		this.receiverDelYn = receiverDelYn;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSenderEmpNo() {
		return senderEmpNo;
	}

	public void setSenderEmpNo(int senderEmpNo) {
		this.senderEmpNo = senderEmpNo;
	}

	public int getReceiverEmpNo() {
		return receiverEmpNo;
	}

	public void setReceiverEmpNo(int receiverEmpNo) {
		this.receiverEmpNo = receiverEmpNo;
	}

	public Timestamp getSentDate() {
		return sentDate;
	}

	public void setSentDate(Timestamp sentDate) {
		this.sentDate = sentDate;
	}

	public Timestamp getReadDate() {
		return readDate;
	}

	public void setReadDate(Timestamp readDate) {
		this.readDate = readDate;
	}

	public String getSenderDelYn() {
		return senderDelYn;
	}

	public void setSenderDelYn(String senderDelYn) {
		this.senderDelYn = senderDelYn;
	}

	public String getReceiverDelYn() {
		return receiverDelYn;
	}

	public void setReceiverDelYn(String receiverDelYn) {
		this.receiverDelYn = receiverDelYn;
	}

	@Override
	public String toString() {
		return "no=" + no + ", content=" + content + ", senderEmpNo=" + senderEmpNo + ", receiverEmpNo="
				+ receiverEmpNo + ", sentDate=" + sentDate + ", readDate=" + readDate + ", senderDelYn=" + senderDelYn
				+ ", receiverDelYn=" + receiverDelYn;
	}
	
}

