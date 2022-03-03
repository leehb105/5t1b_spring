package com.spring.otlb.message.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
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
		return null;
	}

	@Override
	public Message selectOneSentMessage(int no) {
		return null;
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
		return 0;
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
	public int selectSentMessageCount(int empNo) {
		return 0;
	}

	@Override
	public Emp selectOneMember(int empNo) {
		return session.selectOne("message.selectOneMember", empNo);
	}

	@Override
	public int selectTotalSentMessageount(int empNo) {
		return 0;
	}

	
}
