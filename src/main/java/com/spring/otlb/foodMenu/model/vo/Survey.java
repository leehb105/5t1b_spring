package com.spring.otlb.foodMenu.model.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Survey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date surveyDate;
	private int empNo;
	private int answer1;
	private int answer2;
	private int answer3;
	private int answer4;
	private int answer5;
	private String shortAnswer;

}
