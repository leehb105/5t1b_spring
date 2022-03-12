package com.otlb.semi.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.message.model.service.MessageService;

/**
 * Servlet implementation class MessageDeleteServlet
 */
@WebServlet("/message/receivedMessageDelete")
public class ReceivedMessageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//넘어온 글 번호
		String no = request.getParameter("no");
//		System.out.println(no);
		
		List<Integer> list = new ArrayList<>();
		//,가 넘어온 경우만 split처리
		if(no.contains(",")) {
			//,기준으로 문자열 split
			String[] noArr = no.split(",");
			
			//문자열 숫자를 정수형으로 형변환
			for(String str : noArr) {
				list.add(Integer.parseInt(str));
			}
			
		}else {
			//no가 단일값일경우
			list.add(Integer.parseInt(no));
		}
		String msg = "";
		for(int i = 0; i < list.size(); i++) {
			int result = messageService.updateReceiverDelYn(list.get(i));
			if(result > 0) {
				msg = (i+1) + "개의 쪽지 삭제에 성공하였습니다.";
			}else {
				msg = "일부 쪽지 삭제에 실패 하였습니다.";
				break;
			}
			
		}
		
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/message/messageList");
	}

}
