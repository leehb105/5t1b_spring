package com.otlb.semi.message.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.message.model.service.MessageService;

/**
 * Servlet implementation class SentMessageLoadServlet
 */
@WebServlet("/message/messageLoadCount.do")
public class SentMessageLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("@WebServlet(\"/message/messageLoadCount\") 호출성공");
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp) session.getAttribute("loginEmp");
		int empNo = loginEmp.getEmpNo();
//		System.out.println("empNO = " + empNo);
		
		int sentCount = messageService.selectSentMessageCount(empNo);
//		System.out.println("servlet = " + sentCount);
		
		StringBuilder sb = new StringBuilder();
		sb.append(sentCount);
//		System.out.println(sb);
		response.setContentType("text/csv; charset=utf-8");
		response.getWriter().append(sb);
	}

}
