package com.spring.otlb.emp.model.dao;

import java.util.List;

import com.spring.otlb.emp.model.vo.Emp;

public class EmpDaoImpl implements EmpDao{

	@Override
	public Emp selectOneEmp(int no) {
		return null;
	}

	@Override
	public int updateEmp(Emp emp) {
		return 0;
	}

	@Override
	public int insertEmp(Emp emp) {
		return 0;
	}

	@Override
	public List<Emp> selectAllEmp() {
		return null;
	}

	@Override
	public int updatePassword(Emp emp) {
		return 0;
	}

	@Override
	public int countEmpNo(int empNo) {
		return 0;
	}

	@Override
	public int checkEmpInfo(int empNo, String empName, String email) {
		return 0;
	}

}
