package com.otlb.semi.emp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.common.EmpUtils;
import com.otlb.semi.emp.model.service.EmpService;
import com.otlb.semi.emp.model.vo.Emp;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet("/emp/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpService empService = new EmpService();

	/**
	 * 비번변경 페이지
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/emp/updatePassword.jsp")
				.forward(request, response);
	}

	/**
	 * 비번 변경
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location = request.getContextPath();
		String msg = null;
		int result = 0;
		
		
		// 사용자입력값 처리
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		
		// 기존 비밀번호 비교		
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp) session.getAttribute("loginEmp");
		
		if(oldPassword.equals(loginEmp.getPassword())) {
			
			//업무로직
			loginEmp.setPassword(newPassword);
			result = empService.updatePassword(loginEmp);
			msg = (result > 0) ? "비밀번호 변경 성공!" : "비밀번호 변경 실패!";
			location += "/emp/empView";
		}
		else {
			msg = "비밀번호가 일치하지 않습니다.";
			location += "/emp/updatePassword";
		}
		// 리다이렉트처리
		session.setAttribute("msg", msg);
		
//		location = request.getContextPath() + "/emp/empView";
		response.sendRedirect(location);	
	
	}

}
