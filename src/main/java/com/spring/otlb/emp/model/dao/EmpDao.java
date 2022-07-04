package com.spring.otlb.emp.model.dao;

import java.util.List;

import com.spring.otlb.emp.model.vo.Emp;

public interface EmpDao {


	public Emp selectOneEmp(String empNo);

	public int updateEmp(Emp emp);

	public int insertEmp(Emp emp);

	public List<Emp> selectAllEmp();

	public int updatePassword(Emp emp);

	public int countEmpNo(int empNo);

	public int checkEmpInfo(int empNo, String empName, String email);

	public List<Emp> selectAllMember();


    Emp selectOneEmpInfo(String empNo);

	int updateEmpCode(Emp emp);

    List<Emp> selectEmpListByKeyword(String searchKeyword);
}
