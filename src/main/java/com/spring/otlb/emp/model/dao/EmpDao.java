package com.otlb.semi.emp.model.dao;

import static com.otlb.semi.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import com.otlb.semi.emp.model.vo.Department;
import com.otlb.semi.emp.model.exception.EmpException;
import com.otlb.semi.emp.model.vo.Emp;

public class EmpDao {

	private Properties prop = new Properties();
	
	public EmpDao() {
		String filepath = EmpDao.class.getResource("/emp-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Emp selectOneEmp(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneEmp");
		ResultSet rset = null;
		Emp emp = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//System.out.println(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				emp = new Emp();
				emp.setEmpNo(rset.getInt("emp_no"));
				emp.setEmpName(rset.getString("emp_name"));
				emp.setPassword(rset.getString("password"));
				emp.setBirthdate(rset.getDate("birthdate"));
				emp.setDeptCode(rset.getString("dept_code"));
				emp.setJobCode(rset.getString("job_code"));
				emp.setEmpRole(rset.getString("emp_role"));
				emp.setGender(rset.getString("gender"));
				emp.setEmail(rset.getString("email"));
				emp.setPhone(rset.getString("phone"));
				emp.setQuitYn(rset.getString("quit_yn"));
				emp.setBanYn(rset.getString("ban_yn"));
				emp.setDeptName(rset.getString("dept_name"));
				emp.setJobName(rset.getString("job_name"));
			}
		} catch (SQLException e) {
			throw new EmpException("회원가입 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return emp;
	}

	public int updateEmp(Connection conn, Emp emp) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateEmp");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getGender());
			pstmt.setString(2, emp.getPhone());
			pstmt.setString(3, emp.getEmail());
			pstmt.setInt(4, emp.getEmpNo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new EmpException("회원정보 변경 실패", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertEmp(Connection conn, Emp emp) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertEmp");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getPassword());
			pstmt.setDate(3, emp.getBirthdate());
			pstmt.setString(4, emp.getEmpRole());
			pstmt.setString(5, emp.getGender());
			pstmt.setString(6, emp.getEmail());
			pstmt.setString(7, emp.getPhone());
			pstmt.setString(8, emp.getQuitYn());
			pstmt.setString(9, emp.getBanYn());
			pstmt.setInt(10, emp.getEmpNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//throw new EmpException("회원가입 오류", e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Emp> selectAllEmp(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllEmp");
		ResultSet rset = null;
		List<Emp> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Emp emp = new Emp();
				
				emp.setEmpNo(rset.getInt("emp_no"));
				emp.setEmpName(rset.getString("emp_name"));
				emp.setBirthdate(rset.getDate("birthdate"));
				emp.setGender(rset.getString("gender"));
				// deptcode, jobcode가 아니라 deptname, jobname이 맞지만 설계가 이래서 일단 어쩔 수 없음
				emp.setDeptCode(rset.getString("dept_name"));
				emp.setJobCode(rset.getString("job_name"));
				emp.setEmpRole(rset.getString("emp_role"));
				emp.setEmail(rset.getString("email"));
				emp.setPhone(rset.getString("phone"));
				emp.setQuitYn(rset.getString("quit_yn"));
				emp.setBanYn(rset.getString("ban_yn"));
				
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int updatePassword(Connection conn, Emp emp) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getPassword());
			pstmt.setInt(2, emp.getEmpNo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new EmpException("비번 변경 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int countEmpNo(Connection conn, int empNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String sql = prop.getProperty("countEmpNo");
		// 만들어진 사원번호를 발급할때 birthdate를 1000101로 세팅해서 준다.
		Calendar cal = new GregorianCalendar(1000, 00,01);
		Date initialDate = new Date(cal.getTimeInMillis());
		System.out.println(initialDate);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			pstmt.setDate(2, initialDate);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				result = rset.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int checkEmpInfo(Connection conn, int empNo, String empName, String email) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String sql = prop.getProperty("checkEmpInfo");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			pstmt.setString(2, empName);
			pstmt.setString(3, email);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				result = rset.getInt(1);
			
			System.out.println("EmpDao result = " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}



}
