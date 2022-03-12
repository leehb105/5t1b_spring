package com.spring.otlb.message.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.otlb.emp.model.vo.Emp;
import com.spring.otlb.message.model.dao.MessageDao;
import com.spring.otlb.message.model.vo.Message;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao messageDao;
	
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
	public int selectSentMessageCount(String empNo) {
		return messageDao.selectSentMessageCount(empNo);
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
		return messageDao.selectAllReceivedMessage(empNo);
	}

	

}
