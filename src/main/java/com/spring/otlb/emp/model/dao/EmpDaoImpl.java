package com.spring.otlb.emp.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.otlb.emp.model.vo.Emp;

@Repository
public class EmpDaoImpl implements EmpDao{

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public Emp selectOneEmp(String empNo) {
		return session.selectOne("emp.selectOneEmp", empNo);
	}

	@Override
	public int updateEmp(Emp emp) {
		return session.update("emp.updateEmp", emp);
	}

	@Override
	public int insertEmp(Emp emp) {
		return session.update("emp.insertEmp", emp);
	}

	@Override
	public List<Emp> selectAllEmp() {
		return null;
	}

	@Override
	public int updatePassword(Emp emp) {
		return session.update("emp.updatePassword", emp);
	}

	@Override
	public int countEmpNo(int empNo) {
		return 0;
	}

	@Override
	public int checkEmpInfo(int empNo, String empName, String email) {
		return 0;
	}

	@Override
	public List<Emp> selectAllMember() {
		return session.selectList("emp.selectAllMember");
	}

    @Override
    public Emp selectOneEmpInfo(String empNo) {
        return session.selectOne("emp.selectOneEmpInfo", empNo);
    }

	@Override
	public int updateEmpCode(Emp emp) {
		return session.update("emp.updateEmpCode", emp);
	}

	@Override
	public List<Emp> selectEmpListByKeyword(String searchKeyword) {
		return session.selectList("emp.selectEmpListByKeyword", searchKeyword);
	}


}
