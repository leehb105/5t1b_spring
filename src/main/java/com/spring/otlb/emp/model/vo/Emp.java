package com.spring.otlb.emp.model.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Emp extends EmpEntity implements Serializable{


	private String deptName;
	private String jobName;
	
	


}
