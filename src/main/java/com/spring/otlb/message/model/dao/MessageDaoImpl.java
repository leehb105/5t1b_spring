package com.spring.otlb.message.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.otlb.message.model.vo.Message;

@Repository
public class MessageDaoImpl implements MessageDao{

	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Message> selectAllSentMessage(Map<String, Object> param) {
		return session.selectList("message.selectAllSentMessage", param);
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
		return session.insert("message.insertMessage", message);
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
	public int selectReceivedMessageCount(String empNo) {
		return session.selectOne("message.selectReceivedMessageCount", empNo);
	}


//	@Override
//	public int selectTotalSentMessageount(int empNo) {
//		return 0;
//	}

	@Override
	public List<Message> selectAllReceivedMessage(Map<String, Object> param) {
		return session.selectList("message.selectAllReceivedMessage", param);
	}

	@Override
	public int deleteReceivedMessage(int no) {
		return session.update("message.deleteReceivedMessage", no);
	}

	@Override
	public int deleteSentMessage(int no) {
		return session.update("message.deleteSentMessage", no);
	}

	@Override
	public int selectSentMesssageCount(String empNo) {
		return session.selectOne("message.selectSentMesssageCount", empNo);
	}


}
