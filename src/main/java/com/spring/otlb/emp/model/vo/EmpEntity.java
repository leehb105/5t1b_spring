package com.spring.otlb.emp.model.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int empNo;
	private String empName;
	private String password;
	private Date birthdate;
	private String deptCode;
	private String jobCode;
	private String empRole;
	private String gender;
	private String email;
	private String phone;
	private String quitYn;
	private String banYn;
	private String profileImage;
//	private int enabled;
		
		

}
