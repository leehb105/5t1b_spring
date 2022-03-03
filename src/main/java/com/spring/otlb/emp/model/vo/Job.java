package com.spring.otlb.emp.model.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Job implements Serializable{

	private static final long serialVersionUID = 1L;

	private String jobCode;
	private String jobName;
	
	
	
}
