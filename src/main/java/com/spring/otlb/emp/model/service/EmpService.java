package com.spring.otlb.emp.model.service;

import java.util.List;

import com.spring.otlb.emp.model.vo.Emp;

public interface EmpService {

//	public static final String EMP_ROLE = "U";
//	public static final String ADMIN_ROLE = "A";
//	public static final String hasQuit = "Y";
//	public static final String hasNotQuit = "N";
//	public static final String isBanned = "Y";
//	public static final String isNotBanned = "N";
	

	public Emp selectOneEmp(String empNo);

	public int updateEmp(Emp emp);

	public List<Emp> selectAllBoard();

	public int insertEmp(Emp emp);

	public int updatePassword(Emp emp);

	public int countEmpNo(int empNo);

	public int checkEmpInfo(int empNo, String empName, String email);



}
