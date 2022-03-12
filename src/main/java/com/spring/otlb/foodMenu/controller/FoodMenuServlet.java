package com.otlb.semi.foodMenu.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.foodMenu.model.service.FoodMenuService;
import com.otlb.semi.foodMenu.model.vo.FoodMenu;

/**
 * Servlet implementation class foodMenuServlet
 */
@WebServlet("/foodMenu/calendar")
public class FoodMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FoodMenuService foodMenuService = new FoodMenuService();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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

		List<FoodMenu> list = foodMenuService.selectYearMonth(date);
		request.setAttribute("foodMenu", list);
		request.getRequestDispatcher("/WEB-INF/views/foodMenu/calendar.jsp").forward(request, response);
	}

}