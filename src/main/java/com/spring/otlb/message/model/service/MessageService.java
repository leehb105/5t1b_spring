package com.otlb.semi.message.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static com.otlb.semi.common.JdbcTemplate.*;

import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.message.model.dao.MessageDao;
import com.otlb.semi.message.model.vo.Message;

public class MessageService {
	private MessageDao messageDao = new MessageDao();

	public List<Message> selectAllReceivedMessage(int empNo) {
		Connection conn = getConnection();
		List<Message> list = messageDao.selectAllReceivedMessage(conn, empNo);
		close(conn);
		
		return list;
	}
//	public List<Message> selectAllReceivedMessage(int empNo, Map<String, Integer> param) {
//		Connection conn = getConnection();
//		List<Message> list = messageDao.selectAllReceivedMessage(conn, empNo, param);
//		close(conn);
//		
//		return list;
//	}

	public List<Message> selectAllSentMessage(int empNo) {
		Connection conn = getConnection();
		List<Message> list = messageDao.selectAllSentMessage(conn, empNo);
		close(conn);
		
		return list;
	}

	public Message selectOneReceivedMessage(int no) {
		Connection conn = getConnection();
		Message message = messageDao.selectOneReceivedMessage(conn, no);
		close(conn);
		
		return message;
	}

	public Message selectOneSentMessage(int no) {
		Connection conn = getConnection();
		Message message = messageDao.selectOneSentMessage(conn, no);
		close(conn);
		
		return message;
	}

	public int insertMessage(Message message) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = messageDao.insertMessage(conn, message);
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public List<Emp> selectAllMember() {
		Connection conn = getConnection();
		List<Emp> list = messageDao.selectAllMember(conn);
		close(conn);
		
		return list;
	}

	public int updateReadDate(int no) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = messageDao.updateReadDate(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int updateReceiverDelYn(int no) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn= getConnection();
			result = messageDao.updateReceiverDelYn(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int updateSenderDelYn(int no) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn= getConnection();
			result = messageDao.updateSenderDelYn(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int selectSentMessageCount(int empNo) {
		Connection conn = getConnection();
		int count = messageDao.selectSentMessageCount(conn, empNo);
		close(conn);
		
		return count;
	}

	public Emp selectOneMember(int empNo) {
		Connection conn = getConnection();
		Emp emp = messageDao.selectOneMember(conn, empNo);
		close(conn);
		
		return emp;
	}

	public int selectTotalSentMessageount(int empNo) {
		Connection conn = getConnection();
		int totalCount = messageDao.selectTotalSentMessageount(conn, empNo);
		close(conn);
		return totalCount;
	}
	
	
	
	
	
	
	
	
	
	
}
