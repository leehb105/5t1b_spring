package com.spring.otlb.message.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
	
	
}

