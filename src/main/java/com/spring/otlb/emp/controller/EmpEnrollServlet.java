package com.otlb.semi.emp.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.emp.model.service.EmpService;
import com.otlb.semi.emp.model.vo.Emp;

/**
 * Servlet implementation class EmpEnrollServlet
 */
@WebServlet("/emp/empEnroll")
public class EmpEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpService empService = new EmpService();
	public static final String ERROR_MESSAGE = "오류 메세지";
	public static final String SUCCESS_MESSAGE = "성공 메세지";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
			.getRequestDispatcher("/WEB-INF/views/emp/empEnroll.jsp")
			.forward(request, response);
	}

	/**
	 * update emp set emp_name = ?, password = ?, birthdate = ?, 
	 * dept_code = ?, job_code = ?, emp_role = ?, gender = ?, 
	 * email = ?, phone = ?, quit_yn = ?, ban_yn = ? where no = ?
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// 1. 사용자입력값 처리
			int empNo = 0;
			try {
				empNo = Integer.parseInt(request.getParameter("empNo"));
			} catch (Exception e) {
				// 사원번호를 입력하지 않은 경우 modal에 전달할 메세지 작성
				modalMessage(request, response, ERROR_MESSAGE, "사원번호에는 숫자만 입력하세요");
				return;
			}
			
			String empName = request.getParameter("empName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String passwordCheck = request.getParameter("passwordCheck");
			String phone = request.getParameter("phone");
			// 위 내용을 입력하지 않은 경우 modal에 전달할 메세지 작성
			if("".equals(empName) || "".equals(email) || "".equals(password) || "".equals(passwordCheck) || "".equals(phone)) {
				modalMessage(request, response, ERROR_MESSAGE, "모든 내용을 입력하세요.");
				return;
			}

			// 입력되지 않는 경우가 발생하지 않음(select태그)
			String gender = request.getParameter("gender");
			int year = Integer.parseInt(request.getParameter("birthdayYear"));
			int month = Integer.parseInt(request.getParameter("birthdayMonth"));
			int day = Integer.parseInt(request.getParameter("birthdayDay"));
			Calendar cal = new GregorianCalendar(year, month - 1, day);
			Date birthdate = new Date(cal.getTimeInMillis());

			Emp emp = new Emp(empNo, empName, password, birthdate, null, null, EmpService.EMP_ROLE, gender, email, phone, EmpService.hasNotQuit, EmpService.isNotBanned);
			
			// 2. 업무로직
			int result = empService.insertEmp(emp);
			
			// 3. 응답처리
			if(result > 0) {
				// 회원가입 성공시 modal에 전달할 메세지 작성
				modalMessage(request, response, SUCCESS_MESSAGE, "회원가입에 성공하셨습니다.");
				return;
			}
			else {
				
				// 회원가입 실패시 modal에 전달할 메세지 작성
				modalMessage(request, response, ERROR_MESSAGE, "이미 존재하는 회원입니다.");
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // tomcat이 error.jsp로 위임한다.
		}
	}
	
	private void modalMessage(HttpServletRequest request, HttpServletResponse response, String messageType, String messageContent) {
		HttpSession session = request.getSession();
		session.setAttribute("messageType", messageType);
		session.setAttribute("messageContent", messageContent);
		
		try {
			request
				.getRequestDispatcher("/WEB-INF/views/emp/empEnroll.jsp")
				.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
