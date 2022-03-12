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
 * Servlet implementation class SentMessageViewServlet
 */
@WebServlet("/message/sentMessageView")
public class SentMessageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//보낸쪽지함
		//글번호
		int no = Integer.valueOf(request.getParameter("no"));

		Message message = messageService.selectOneSentMessage(no);
		message.setContent(LineFormatUtils.formatLine(message.getContent()));
		
		String sentDate = DateFormatUtils.formatDate(message.getSentDate());
		
		request.setAttribute("message", message);
		request.setAttribute("sentDate", sentDate);
		
		request
			.getRequestDispatcher("/WEB-INF/views/message/sentMessageView.jsp")
			.forward(request, response);
	}

}
