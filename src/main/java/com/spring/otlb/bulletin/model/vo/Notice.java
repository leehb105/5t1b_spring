package com.spring.otlb.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.spring.otlb.emp.model.vo.Emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Notice extends Bulletin implements Serializable {

	private int attachCount; // 첨부 파일수
	private List<Attachment> attachments;
	
	public Notice(int no, String title, String content, Timestamp regDate, int readCount, int empNo, String deleteYn,
			Emp emp, int attachCount) {
		super(no, title, content, regDate, readCount, empNo, deleteYn, emp);
	}

	
	
}
