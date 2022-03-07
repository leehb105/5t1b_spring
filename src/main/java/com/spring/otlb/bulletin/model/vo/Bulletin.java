package com.spring.otlb.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.spring.otlb.emp.model.vo.Emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



	/**
	 *	VO 클래스 상속 관계도  
	 *	Bulletin
	 *  	|_ NoticeEntity
	 *  	|	|_ Notice
	 *  	|_ BoardEntity
	 *  		|_ Board
	 * 삭제 							|_ AnonymousBoard
	 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
abstract public class Bulletin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String title;
	private String content;
	private Timestamp regDate;
	private int readCount;
	private int empNo;
	private String deleteYn;
	private Emp emp;
	
}
