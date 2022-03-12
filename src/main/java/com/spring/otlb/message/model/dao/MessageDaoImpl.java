package com.spring.otlb.message.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.otlb.emp.model.vo.Emp;
import com.spring.otlb.message.model.vo.Message;

@Repository
public class MessageDaoImpl implements MessageDao{

	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Message> selectAllReceivedMessage(int empNo) {
		return null;
	}

	@Override
	public List<Message> selectAllSentMessage(int empNo) {
		return null;
	}

	@Override
	public Message selectOneReceivedMessage(int no) {
		return session.selectOne("message.selectOneReceivedMessage", no);
	}

	@Override
	public Message selectOneSentMessage(int no) {
		return session.selectOne("message.selectOneSentMessage", no);
	}

	@Override
	public int insertMessage(Message message) {
		return 0;
	}

	@Override
	public List<Emp> selectAllMember() {
		return null;
	}

	@Override
	public int updateReadDate(int no) {
		return session.update("message.updateReadDate", no);
	}

	@Override
	public int updateReceiverDelYn(int no) {
		return 0;
	}

	@Override
	public int updateSenderDelYn(int no) {
		return 0;
	}

	@Override
	public int selectSentMessageCount(String empNo) {
		return session.selectOne("message.selectSentMessageCount", empNo);
	}

	@Override
	public Emp selectOneMember(int empNo) {
		return null;
	}

//	@Override
//	public int selectTotalSentMessageount(int empNo) {
//		return 0;
//	}

	@Override
	public List<Message> selectAllReceivedMessage(String empNo) {
		return session.selectList("message.selectAllReceivedMessage", empNo);
	}
	
	
}
