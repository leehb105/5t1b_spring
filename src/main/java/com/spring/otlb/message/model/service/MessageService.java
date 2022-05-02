package com.spring.otlb.message.model.service;

import java.util.List;

import com.spring.otlb.emp.model.vo.Emp;
import com.spring.otlb.message.model.vo.Message;

public interface MessageService {

	public List<Message> selectAllSentMessage(String empNo);

	public Message selectOneReceivedMessage(int no);

	public Message selectOneSentMessage(int no);

	public int insertMessage(Message message);


	
	public int updateReadDate(int no);

	public int updateReceiverDelYn(int no);

	public int updateSenderDelYn(int no);

	public int selectReceivedMessageCount(String empNo);

//	public int selectTotalSentMessageount(int empNo);

	public List<Message> selectAllReceivedMessage(String empNo);

    public int deleteReceivedMessage(int no);

	public int deleteSentMessage(int no);
}
