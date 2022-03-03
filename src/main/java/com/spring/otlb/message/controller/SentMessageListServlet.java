package com.otlb.semi.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.common.DateFormatUtils;
import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.message.model.service.MessageService;
import com.otlb.semi.message.model.vo.Message;

/**
 * Servlet implementation class SentMessageListServlet
 */
@WebServlet("/message/sentMessageList")
public class SentMessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
//		System.out.println(session.getAttribute("loginEmp"));
		Emp emp = (Emp) session.getAttribute("loginEmp");
		
		int empNo = emp.getEmpNo();
		List<Message> list = messageService.selectAllSentMessage(empNo);
		List<String> titleList = new ArrayList<>();
		List<String> sentDateList = new ArrayList<>();
		List<String> readDateList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getContent().length() > 50) {
				//n자가 넘는 쪽지의 경우 n자만출력해줌
				titleList.add(list.get(i).getContent().substring(0, 40) + "...더보기");
			}else {
				titleList.add(list.get(i).getContent());
			}
			//날짜처리
			sentDateList.add(DateFormatUtils.formatDate(list.get(i).getSentDate()));
			readDateList.add(DateFormatUtils.formatDate(list.get(i).getReadDate()));
		}
		request.setAttribute("list", list);
		request.setAttribute("titleList", titleList);
		request.setAttribute("sentDateList", sentDateList);
		request.setAttribute("readDateList", readDateList);
		request
			.getRequestDispatcher("/WEB-INF/views/message/sentMessageList.jsp")
			.forward(request, response);
	}

}
