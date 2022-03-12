package com.otlb.semi.message.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.message.model.service.MessageService;

/**
 * Servlet implementation class MessageFormServlet
 */
@WebServlet("/message/messageForm")
public class MessageFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _receiverNo = request.getParameter("receiverNo");
		System.out.println(_receiverNo);
		//System.out.println("_empNo" + _senderNo);
		String receiver = null;
		if(_receiverNo != null) {
			Emp emp = messageService.selectOneMember(Integer.valueOf(_receiverNo));
			receiver = emp.getEmpNo() + "-" + emp.getEmpName();
			
		}
		System.out.println(receiver);
		request.setAttribute("receiver", receiver);
		
		request
			.getRequestDispatcher("/WEB-INF/views/message/messageForm.jsp")
			.forward(request, response);
	}


}
