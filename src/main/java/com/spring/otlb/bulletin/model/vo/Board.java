package com.spring.otlb.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.spring.otlb.common.Criteria;
import org.springframework.web.multipart.MultipartFile;

import com.spring.otlb.emp.model.vo.Emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Board extends BoardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int attachCount; // 첨부 파일수
	private List<Attachment> attachments;
	private MultipartFile uploadFile;
	private int commentCount; //댓글 수
	private Criteria cri;

	public Board(int no, String title, String content, Timestamp regDate, int readCount, int likeCount, String reportYn,
			int empNo, String category, String deleteYn, Emp emp) {
		super(no, title, content, regDate, readCount, likeCount, reportYn, empNo, category, deleteYn, emp);
		// TODO Auto-generated constructor stub
	}
	public Board(int attachCount, List<Attachment> attachments) {
		super();
		this.attachCount = attachCount;
		this.attachments = attachments;
	}
	

	
}
