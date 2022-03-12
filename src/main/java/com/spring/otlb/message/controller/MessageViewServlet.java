package com.otlb.semi.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.common.DateFormatUtils;
import com.otlb.semi.common.LineFormatUtils;
import com.otlb.semi.message.model.service.MessageService;
import com.otlb.semi.message.model.vo.Message;

/**
 * Servlet implementation class MessageViewServlet
 */
@WebServlet("/message/messageView")
public class MessageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//받은쪽지함
		
		//글번호
		int no = Integer.valueOf(request.getParameter("no"));
		
		int result = messageService.updateReadDate(no);
		String msg = result > 0 ? null : "쪽지 읽음처리 오류";
		
		Message message = messageService.selectOneReceivedMessage(no);
		message.setContent(LineFormatUtils.formatLine(message.getContent()));
		System.out.println("%%%%" + message);
	
		String date = DateFormatUtils.formatDate(message.getSentDate());
		
		request.setAttribute("message", message);
		request.setAttribute("date", date);
		request.setAttribute("msg", msg);
		
		request
			.getRequestDispatcher("/WEB-INF/views/message/messageView.jsp")
			.forward(request, response);
		
		
	}

}
