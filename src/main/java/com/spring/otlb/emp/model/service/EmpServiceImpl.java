package com.spring.otlb.emp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.otlb.emp.model.dao.EmpDao;
import com.spring.otlb.emp.model.vo.Emp;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired
	private EmpDao empDao;
	
	@Override
	public Emp selectOneEmp(String empNo) {
		return empDao.selectOneEmp(empNo);
	}

	@Override
	public int updateEmp(Emp emp) {
		return empDao.updateEmp(emp);
	}

	@Override
	public List<Emp> selectAllBoard() {
		return null;
	}

	@Override
	public int insertEmp(Emp emp) {
		return empDao.insertEmp(emp);
	}

	@Override
	public int updatePassword(Emp emp) {
		return 0;
	}

	@Override
	public int countEmpNo(String empNo) {
		return 0;
	}

	@Override
	public int checkEmpInfo(String empNo, String empName, String email) {
		return 0;
	}

	@Override
	public List<Emp> selectAllMember() {
		return empDao.selectAllMember();
	}

	@Override
	public Emp selectOneEmpInfo(String empNo) {
		return empDao.selectOneEmpInfo(empNo);
	}


}
