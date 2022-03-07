package com.spring.otlb.bulletin.model.vo;

import java.io.Serializable;


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

}
