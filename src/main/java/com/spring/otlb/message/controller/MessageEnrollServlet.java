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

import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.message.model.service.MessageService;
import com.otlb.semi.message.model.vo.Message;

/**
 * Servlet implementation class MessageEnrollServlet
 */
@WebServlet("/message/messageEnroll")
public class MessageEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//보내는사람
		HttpSession session = request.getSession();
		Emp emp = (Emp) session.getAttribute("loginEmp");
		int sender = emp.getEmpNo();
		
		//받는사람
		String _receiverList = request.getParameter("receiverList");
		System.out.println("_receiverList" + _receiverList);
		String[] receiverList = _receiverList.split(", ");
		System.out.println("receiverList" + receiverList);
		
		String content = request.getParameter("content");
		
		List<Integer> receiverNo = new ArrayList<>();
		List<String> receiverName = new ArrayList<>();
		for(String str : receiverList) {
			String[] temp = str.split("-");
			//앞부분 사번만 가져옴
			receiverNo.add(Integer.valueOf(temp[0]));
			receiverName.add(temp[1]);
		}
		
		int count = 0;
		String msg = null;
		//보낸사람 만큼 요청 보냄
		for(int i = 0; i < receiverNo.size(); i++) {
			Message message = new Message(0, content, sender, receiverNo.get(i), null, null, null, null);
			int result = messageService.insertMessage(message);
			if(result > 0) {
				count++;
				msg = count + "명에게 쪽지를 성공적으로 발송했습니다!";
			}else {
				msg = receiverName.get(i) + "의 쪽지 발송에 실패했습니다...";
				break;
			}
		}
		
		session.setAttribute("msg", msg);
		String location = request.getContextPath() + "/message/messageForm";
		response.sendRedirect(location);
		
	}

}
