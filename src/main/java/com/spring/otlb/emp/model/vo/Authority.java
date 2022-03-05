package com.spring.otlb.emp.model.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Authority implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String authority;
	private int empNo;

}
