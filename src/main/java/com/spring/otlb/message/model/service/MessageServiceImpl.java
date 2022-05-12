package com.spring.otlb.message.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.otlb.message.model.dao.MessageDao;
import com.spring.otlb.message.model.vo.Message;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao messageDao;
	
	@Override
	public List<Message> selectAllSentMessage(Map<String, Object> param) {
		return messageDao.selectAllSentMessage(param);
	}

	@Override
	public Message selectOneReceivedMessage(int no) {
		return messageDao.selectOneReceivedMessage(no);
	}

	@Override
	public Message selectOneSentMessage(int no) {
		return messageDao.selectOneSentMessage(no);
	}

	@Override
	public int insertMessage(Message message) {
		return messageDao.insertMessage(message);
	}


	@Override
	public int updateReadDate(int no) {
		return messageDao.updateReadDate(no);
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
		return messageDao.selectReceivedMessageCount(empNo);
	}

//	@Override
//	public int selectTotalSentMessageount(int empNo) {
//		return 0;
//	}

	@Override
	public List<Message> selectAllReceivedMessage(Map<String, Object> param) {
		return messageDao.selectAllReceivedMessage(param);
	}

	@Override
	public int deleteReceivedMessage(int no) {
		return messageDao.deleteReceivedMessage(no);
	}

	@Override
	public int deleteSentMessage(int no) {
		return messageDao.deleteSentMessage(no);
	}

	@Override
	public int selectSentMesssageCount(String empNo) {
		return messageDao.selectSentMesssageCount(empNo);
	}


}
