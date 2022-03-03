package com.otlb.semi.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.emp.model.service.EmpService;

/**
 * Servlet implementation class EmpNoCheckServlet
 */
@WebServlet("/emp/empNoCheck")
public class EmpNoCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpService empService = new EmpService();

	/**
	 * select count(*) from emp where emp_no = ?
	 * 이미 존재하는 사원번호라면 1 아니면 0
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 사용자입력값 처리
		int empNo = 0;
		try {
			empNo = Integer.parseInt(request.getParameter("empNo"));
		} catch (NumberFormatException e) {
			return;
		}
		
		// 2. 업무요청
		int empNoCount = empService.countEmpNo(empNo);
		
		// 3. 응답요청
		String result = String.valueOf(empNoCount);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(result);
	}

}
