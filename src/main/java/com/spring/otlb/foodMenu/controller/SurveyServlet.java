package com.otlb.semi.foodMenu.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otlb.semi.emp.model.vo.Emp;
import com.otlb.semi.foodMenu.model.service.SurveyService;
import com.otlb.semi.foodMenu.model.vo.Survey;

/**
 * Servlet implementation class surveyServlet
 */
@WebServlet("/foodMenu/survey")
public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SurveyService surveyService = new SurveyService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String curYear = request.getParameter("year");
		String curMonth = request.getParameter("month");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		java.util.Date curDate = new java.util.Date();
		
		Date date = null;
		if(curYear == null || curMonth == null) {
			date = Date.valueOf(formatter.format(curDate) + "-01");
		} else {
			date = Date.valueOf(curYear + "-" + curMonth + "-01");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/foodMenu/survey.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();
			Emp emp = (Emp) session.getAttribute("loginEmp");
			int empNo = emp.getEmpNo();

			int[] answers = new int[5];
			for(int i = 1; i < answers.length + 1; i++) {
				System.out.println(request.getParameter("q" + i));
				answers[i - 1] = Integer.parseInt(request.getParameter("q" + i));

			}
			String curYear = request.getParameter("year");
			String curMonth = request.getParameter("month");

			Date surveyDate = Date.valueOf(curYear + "-" + curMonth + "-01");
			System.out.println(surveyDate);
			Survey survey = new Survey(surveyDate, empNo, answers[0], answers[1], answers[2], answers[3], answers[4], null);
			
			int result = surveyService.insertSurvey(survey);
			
			String msg = result > 0 ? "설문조사 응답 완료" : "설문조사 오류";
			
			session.setAttribute("msg", msg);
			String location = request.getContextPath() + "/foodMenu/calendar";
			response.sendRedirect(location);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
