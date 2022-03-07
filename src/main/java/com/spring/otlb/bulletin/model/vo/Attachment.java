package com.spring.otlb.bulletin.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int no;
	private String originalFilename;		// 사용자가 업로드한 파일명
	private String renamedFilename;			// 서버컴퓨터에 저장된 파일명
	private Date regDate;
	private int boardNo;
	


}
