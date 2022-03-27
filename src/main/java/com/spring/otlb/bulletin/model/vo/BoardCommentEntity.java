package com.spring.otlb.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardCommentEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int no;
	private int commentLevel;
	private String content;
	private String reportYn;
	private int commentRef;
	private Timestamp regDate;
	private int boardNo;
	private String empNo;
	private String deleteYn;



	
}
