package com.spring.otlb.emp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.otlb.emp.model.dao.EmpDao;
import com.spring.otlb.emp.model.vo.Emp;

public class EmpServiceImpl implements EmpService{

	@Autowired
	private EmpDao empDao;
	
	@Override
	public Emp selectOneEmp(int no) {
		return null;
	}

	@Override
	public int updateEmp(Emp emp) {
		return 0;
	}

	@Override
	public List<Emp> selectAllBoard() {
		return null;
	}

	@Override
	public int insertEmp(Emp emp) {
		return 0;
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
