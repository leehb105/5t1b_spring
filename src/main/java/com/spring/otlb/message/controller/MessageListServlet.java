package com.otlb.semi.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.common.DateFormatUtils;
import com.otlb.semi.common.EmpUtils;
import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.message.model.service.MessageService;
import com.otlb.semi.message.model.vo.Message;

/**
 * Servlet implementation class MessageList
 */
@WebServlet("/message/messageList")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//받은 메세지함
		HttpSession session = request.getSession();
		
		Emp emp = (Emp) session.getAttribute("loginEmp");
		
		int empNo = emp.getEmpNo();
		
		
		
		List<Message> list = messageService.selectAllReceivedMessage(empNo);
		List<String> titleList = new ArrayList<>();
		List<String> sentDateList = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			
			if(list.get(i).getContent().length() > 50) {
				//n자가 넘는 쪽지의 경우 n자만출력해줌
				titleList.add(list.get(i).getContent().substring(0, 40) + "...더보기");
			}else {
				titleList.add(list.get(i).getContent());
			}
			//쪽지 날짜 변형부
			sentDateList.add(DateFormatUtils.formatDate(list.get(i).getSentDate()));
		}
		int totalContent = messageService.selectTotalSentMessageount(empNo);
		String url = request.getRequestURI();
		
		request.setAttribute("list", list);
		request.setAttribute("titleList", titleList);
		request.setAttribute("sentDateList", sentDateList);
		
		request
			.getRequestDispatcher("/WEB-INF/views/message/messageList.jsp")
			.forward(request, response);
	}

}
