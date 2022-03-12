package com.otlb.semi.message.model.dao;

import static com.otlb.semi.common.JdbcTemplate.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.message.model.exception.MessageException;
import com.otlb.semi.message.model.vo.Message;

public class MessageDao {
	
	private Properties prop = new Properties();
	
	public MessageDao() {
		File file = new File(MessageDao.class.getResource("/message-query.properties").getPath());
		
		try {
			prop.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public List<Message> selectAllReceivedMessage(Connection conn, int empNo, Map<String, Integer> param) {
	public List<Message> selectAllReceivedMessage(Connection conn, int empNo) {
		PreparedStatement pstmt = null;
		List<Message> list = new ArrayList<>();
		String sql = prop.getProperty("selectAllReceivedMessage");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
//			pstmt.setInt(2, param.get("start"));
//			pstmt.setInt(3, param.get("end"));

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Message message = new Message();
				message.setNo(rset.getInt("no"));
				message.setContent(rset.getString("content"));
				message.setSenderEmpNo(rset.getInt("sender_emp_no"));
				message.setReceiverEmpNo(rset.getInt("receiver_emp_no"));
				message.setSentDate(rset.getTimestamp("sent_date"));
				message.setReadDate(rset.getTimestamp("read_date"));
				message.setSenderDelYn(rset.getString("sender_del_yn"));
				message.setReceiverDelYn(rset.getString("receiver_del_yn"));
				
				Emp emp = new Emp();
				emp.setEmpName(rset.getString("sender_emp_name"));
				emp.setDeptName(rset.getString("sender_dept_name"));
				
				message.setEmp(emp);
				
				list.add(message);
			}
		} catch (SQLException e) {
			throw new MessageException("받은 쪽지 데이터 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<Message> selectAllSentMessage(Connection conn, int empNo) {
		PreparedStatement pstmt = null;
		List<Message> list = new ArrayList<>();
		String sql = prop.getProperty("selectAllSentMessage");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Message message = new Message();
				message.setNo(rset.getInt("no"));
				message.setContent(rset.getString("content"));
				message.setSenderEmpNo(rset.getInt("sender_emp_no"));
				message.setReceiverEmpNo(rset.getInt("receiver_emp_no"));
				message.setSentDate(rset.getTimestamp("sent_date"));
				message.setReadDate(rset.getTimestamp("read_date"));
				message.setSenderDelYn(rset.getString("sender_del_yn"));
				message.setReceiverDelYn(rset.getString("receiver_del_yn"));
				
				Emp emp = new Emp();
				emp.setEmpName(rset.getString("receiver_emp_name"));
				emp.setDeptName(rset.getString("receiver_dept_name"));
				
				message.setEmp(emp);
				
				list.add(message);
			}
		} catch (SQLException e) {
			throw new MessageException("보낸 쪽지 데이터 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public Message selectOneReceivedMessage(Connection conn, int no) {
		//받은 쪽지함 상세
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneReceivedMessage");
		Message message = new Message();
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				message.setNo(rset.getInt("no"));
				message.setSenderEmpNo(rset.getInt("sender_emp_no"));
				message.setSentDate(rset.getTimestamp("sent_date"));
				message.setContent(rset.getString("content"));
				
				Emp emp = new Emp();
				emp.setEmpName(rset.getString("sender_emp_name")); 
				emp.setDeptName(rset.getString("sender_dept_name")); 
				message.setEmp(emp);
			}
		} catch (SQLException e) {
			throw new MessageException("받은상세쪽지 조회 요류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return message;
	}

	public Message selectOneSentMessage(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneSentMessage");
		Message message = new Message();
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				message.setNo(rset.getInt("no"));
				message.setSentDate(rset.getTimestamp("sent_date"));
				message.setContent(rset.getString("content"));
//				message.setReadDate(rset.getDate("read_date"));
				
				Emp emp = new Emp();
				emp.setEmpName(rset.getString("receiver_emp_name"));
				emp.setDeptName(rset.getString("receiver_dept_name"));
				message.setEmp(emp);
			}
		} catch (SQLException e) {
			throw new MessageException("보낸상세쪽지 조회 요류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return message;
	}

	public int insertMessage(Connection conn, Message message) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMessage");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getContent());
			pstmt.setInt(2, message.getSenderEmpNo());
			pstmt.setInt(3, message.getReceiverEmpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MessageException("쪽지 발송 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Emp> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllMember");
		List<Emp> list = new ArrayList<Emp>();
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Emp emp = new Emp();
				emp.setEmpNo(rset.getInt("emp_no"));
				emp.setEmpName(rset.getString("emp_name"));
				
				list.add(emp);
			}
		} catch (SQLException e) {
			throw new MessageException("회원 이름, 사번 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateReadDate(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadDate");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MessageException("쪽지 읽음처리 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReceiverDelYn(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReceiverDelYn");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MessageException("받은 쪽지 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateSenderDelYn(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateSenderDelYn");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MessageException("보낸 쪽지 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectSentMessageCount(Connection conn, int empNo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String sql = prop.getProperty("selectSentMessageCount");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()){
				count = rset.getInt("count");
			}
		} catch (SQLException e) {
			throw new MessageException("받은 쪽지 갯수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}

	public Emp selectOneMember(Connection conn, int empNo) {
		PreparedStatement pstmt = null;
		Emp emp = new Emp();
		String sql = prop.getProperty("selectOneMember");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()){
				emp.setEmpNo(rset.getInt("emp_no"));
				emp.setEmpName(rset.getString("emp_name"));
			}
		} catch (SQLException e) {
			throw new MessageException("사원 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return emp;
	}

	public int selectTotalSentMessageount(Connection conn, int empNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalSentMessageount");
		ResultSet rset = null;
		int totalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalCount;
	}
}







