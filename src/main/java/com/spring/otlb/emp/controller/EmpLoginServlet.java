package com.otlb.semi.emp.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.emp.model.service.EmpService;
import com.otlb.semi.emp.model.vo.Emp;

/**
 * Servlet implementation class EmpLoginServlet
 */
@WebServlet("/emp/login")
public class EmpLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpService empService = new EmpService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
			.getRequestDispatcher("/WEB-INF/views/emp/empLogin.jsp")
			.forward(request, response);
	}
	
	/**
	 * select * from emp where no = ?
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값처리
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		String password = request.getParameter("password");
		String saveNo = request.getParameter("saveNo"); // 값으로 on or null
		//System.out.println("saveId = " + saveNo);
		
		// 2. 업무로직
		Emp emp = empService.selectOneEmp(empNo);
		System.out.println("[EmpLoginServlet] emp = " + emp);
		
		HttpSession session = request.getSession();
		//String empNoString = String.valueOf(emp.getEmpNo());

		String filepath = UpdateProfileImgServlet.class.getResource("/../../img/profile").getPath();
		File ownProfileImage = new File(filepath + empNo + ".png");
		if(ownProfileImage.exists()) session.setAttribute("ownProfileImageExists", true);
		else session.setAttribute("ownProfileImageExists", false);
		
		// 로그인 성공여부
		if(emp != null && password.equals(emp.getPassword())) {
			//로그인 성공
			
			// 로그인객체를 session에 저장
			session.setAttribute("loginEmp", emp);
			
			// 아이디저장 체크박스 처리
			Cookie cookie = new Cookie("saveEmpNo", String.valueOf(empNo));
			cookie.setPath(request.getContextPath()); // context path로 시작하는 모든 경로에서 쿠키사용
			if(saveNo != null) {
				cookie.setMaxAge(7 * 24 * 60 * 60); // 7일				
			}
			else {
				cookie.setMaxAge(0); // 즉시 삭제
			}
			response.addCookie(cookie);
			
			// 3. 응답처리
			// 새로고침을 통한 오류 방지
			String location = request.getContextPath() + "/";
			response.sendRedirect(location);
			
		}
		else {
			// 로그인 실패
			session.setAttribute("modalHeader", "로그인 실패");
			session.setAttribute("modalBody", "아이디 또는 비밀번호가 일치하지 않습니다.");
			String location = request.getContextPath() + "/emp/login";
			response.sendRedirect(location);
			
		}
		
		
		
		
			
	}

}
