package com.spring.otlb.message.model.service;

import java.util.List;

import com.spring.otlb.emp.model.vo.Emp;
import com.spring.otlb.message.model.vo.Message;

public interface MessageService {

	public List<Message> selectAllSentMessage(int empNo);

	public Message selectOneReceivedMessage(int no);

	public Message selectOneSentMessage(int no);

	public int insertMessage(Message message);

	public List<Emp> selectAllMember();
	
	public int updateReadDate(int no);

	public int updateReceiverDelYn(int no);

	public int updateSenderDelYn(int no);

	public int selectSentMessageCount(String empNo);

	public Emp selectOneMember(int empNo);

//	public int selectTotalSentMessageount(int empNo);

	public List<Message> selectAllReceivedMessage(String empNo);
	
	
	
	
	
	
	
	
	
}
