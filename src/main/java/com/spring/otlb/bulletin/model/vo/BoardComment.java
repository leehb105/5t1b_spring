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
public class BoardComment extends BoardCommentEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private Emp emp;
	public BoardComment(int no, int commentLevel, String content, String reportYn, int commentRef, Timestamp regDate,
			int boardNo, int empNo, String deleteYn, Emp emp) {
		super(no, commentLevel, content, reportYn, commentRef, regDate, boardNo, empNo, deleteYn);
		this.emp = emp;
	}

	
}
