package com.otlb.semi.message.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.otlb.semi.emp.model.vo.Emp;

public class Message extends MessageEntity implements Serializable{

	static final long serialVersionUID = 1L;
	private Emp emp;
	
	public Message() {
		super();
	}

	public Message(int no, String content, int senderEmpNo, int receiverEmpNo, Timestamp sentDate, Timestamp readDate,
			String senderDelYn, String receiverDelYn) {
		super(no, content, senderEmpNo, receiverEmpNo, sentDate, readDate, senderDelYn, receiverDelYn);
	}

	public Message(Emp emp) {
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
		return "Message[" + super.toString() 
			+ ", emp=" + emp + "]";
	}
	
}
