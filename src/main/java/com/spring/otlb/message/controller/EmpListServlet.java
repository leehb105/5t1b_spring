package com.otlb.semi.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.message.model.service.MessageService;

/**
 * Servlet implementation class JsonEmpListServlet
 */
@WebServlet("/message/empList.do")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//업무로직: celeb목록조회
//		String input = request.getParameter("input");
//		System.out.println("[AutoCompleteServlet] term = " + input);
//		
//		List<Emp> list = messageService.selectAllMember();
//		Gson gson = new Gson();
//		String jsonStr = null;
//		for(Emp emp : list) {
//			if(emp.getEmpName().contains(input) || String.valueOf(emp.getEmpNo()).contains(input)) {
//				jsonStr = gson.toJson(emp);
//			}
//		}
//		
//		System.out.println("[JsonCelebListServlet] jsonStr = " + jsonStr);
//		
//		//응답메세지에 직접 출력: json형식
//		//null | {} | [] 
//		//{}
//		//	- {}의 속성은 항상 쌍따옴표로 감싸야 하고, {}의 속성값중 문자열은 쌍따옴표로 감싸야 한다.
//		//	- 숫자, boolean, {}, []이 올 수 있다.
//		response.setContentType("application/json; charset=utf-8");
//		response.getWriter().append(jsonStr);
		
//csv형식		
		System.out.println("/message/empList.do 호출");
		//사용자입력값 확인
		String term = request.getParameter("term");
		//System.out.println("[AutoCompleteServlet] term = " + term);
		
		List<Emp> list = messageService.selectAllMember();
		
		List<Emp> resultList = new ArrayList<Emp>();
		for(Emp emp : list) {
			if(emp.getEmpName().contains(term) || String.valueOf(emp.getEmpNo()).contains(term)) {
				resultList.add(emp);
				
			}
			
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < resultList.size(); i++) {
			sb.append(resultList.get(i).getEmpNo());
			sb.append("-");
			sb.append(resultList.get(i).getEmpName());
			sb.append(",");
		}
		response.setContentType("text/csv; charset=utf-8");
		response.getWriter().append(sb);

	}

}
