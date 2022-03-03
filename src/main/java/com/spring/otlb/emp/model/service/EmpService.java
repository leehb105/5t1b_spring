package com.otlb.semi.emp.model.service;

import static com.otlb.semi.common.JdbcTemplate.close;
import static com.otlb.semi.common.JdbcTemplate.commit;
import static com.otlb.semi.common.JdbcTemplate.getConnection;
import static com.otlb.semi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.otlb.semi.emp.model.dao.EmpDao;
import com.otlb.semi.emp.model.vo.Department;
import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.emp.model.vo.Job;

public class EmpService {

	public static final String EMP_ROLE = "U";
	public static final String ADMIN_ROLE = "A";
	public static final String hasQuit = "Y";
	public static final String hasNotQuit = "N";
	public static final String isBanned = "Y";
	public static final String isNotBanned = "N";
	
	private EmpDao empDao = new EmpDao();

	public Emp selectOneEmp(int no) {
		Connection conn = getConnection();
		Emp emp = empDao.selectOneEmp(conn, no);
		close(conn);
		return emp;
	}

	public int updateEmp(Emp emp) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = empDao.updateEmp(conn, emp);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public List<Emp> selectAllBoard() {
		Connection conn = getConnection();
		List<Emp> list = empDao.selectAllEmp(conn);
		close(conn);
		return list;
	}

	public int insertEmp(Emp emp) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = empDao.insertEmp(conn, emp);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public int updatePassword(Emp emp) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = empDao.updatePassword(conn, emp);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int countEmpNo(int empNo) {
		Connection conn = getConnection();
		int result = empDao.countEmpNo(conn, empNo);
		close(conn);

		return result;
	}

	public int checkEmpInfo(int empNo, String empName, String email) {
		Connection conn = getConnection();
		int result = empDao.checkEmpInfo(conn, empNo, empName, email);
		close(conn);
		return result;
	}


}
