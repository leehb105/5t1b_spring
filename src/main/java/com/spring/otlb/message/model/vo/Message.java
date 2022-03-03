package com.spring.otlb.message.model.vo;

import java.io.Serializable;

import com.spring.otlb.emp.model.vo.Emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message extends MessageEntity implements Serializable{

	static final long serialVersionUID = 1L;
	private Emp emp;
	
	
	
}
