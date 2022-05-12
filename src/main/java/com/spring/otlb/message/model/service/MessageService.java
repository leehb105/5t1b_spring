package com.spring.otlb.message.model.service;

import java.util.List;
import java.util.Map;

import com.spring.otlb.message.model.vo.Message;

public interface MessageService {

	public List<Message> selectAllSentMessage(Map<String, Object> param);

	public Message selectOneReceivedMessage(int no);

	public Message selectOneSentMessage(int no);

	public int insertMessage(Message message);


	
	public int updateReadDate(int no);

	public int updateReceiverDelYn(int no);

	public int updateSenderDelYn(int no);

	public int selectReceivedMessageCount(String empNo);

//	public int selectTotalSentMessageount(int empNo);

	public List<Message> selectAllReceivedMessage(Map<String, Object> param);

    public int deleteReceivedMessage(int no);

	public int deleteSentMessage(int no);

	int selectSentMesssageCount(String empNo);
}
