package com.spring.otlb.message.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.spring.otlb.emp.model.vo.Emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Message extends MessageEntity implements Serializable{

	static final long serialVersionUID = 1L;
	private Emp emp;


	public Message(int no, String content, int senderEmpNo, int receiverEmpNo, Timestamp sentDate, Timestamp readDate,
			String senderDelYn, String receiverDelYn, Emp emp) {
		super(no, content, senderEmpNo, receiverEmpNo, sentDate, readDate, senderDelYn, receiverDelYn);
		this.emp = emp;
	}

	
}
