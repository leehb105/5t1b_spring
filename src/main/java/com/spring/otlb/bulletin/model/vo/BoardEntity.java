package com.spring.otlb.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.spring.otlb.emp.model.vo.Emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class BoardEntity extends Bulletin implements Serializable {

	private static final long serialVersionUID = 1L;

	private int likeCount;
	private String reportYn;
	private String category;

	public BoardEntity(int no, String title, String content, Timestamp regDate, int readCount, 
			int likeCount, String reportYn, int empNo, String category, String deleteYn, Emp emp) {
		super(no, title, content, regDate, readCount, empNo, deleteYn, emp);
		this.likeCount = likeCount;
		this.reportYn = reportYn;
		this.category = category;
	}
	
	
}
