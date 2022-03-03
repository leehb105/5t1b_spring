package com.otlb.semi.bulletin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otlb.semi.bulletin.model.service.BulletinService;
import com.otlb.semi.bulletin.model.vo.Board;
import com.otlb.semi.common.DateFormatUtils;
import com.otlb.semi.common.EmpUtils;

/**
 * Servlet implementation class AnonymousBoardList
 */
@WebServlet("/board/anonymousBoardList")
public class AnonymousBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {}
		int start = (cPage - 1) * numPerPage + 1; 
		int end = cPage * numPerPage;
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		
		List<Board> list = bulletinService.selectAllAnonymousBoard(param);
		List<String> regDate = new ArrayList<>();
		System.out.println("list@servlet = " + list);
		for(Board board : list) {
			regDate.add(DateFormatUtils.formatDateBoard(board.getRegDate()));
		}

		int totalContent = bulletinService.selectTotalBoardCount();
		String url = request.getRequestURI();
		String pagebar = EmpUtils.getPagebar(cPage, numPerPage, totalContent, url);
		System.out.println("pagebar@servlet = " + pagebar);

		request.setAttribute("list", list);
		request.setAttribute("regDate", regDate);
		request.setAttribute("pagebar", pagebar);

		request
			.getRequestDispatcher("/WEB-INF/views/anonymousBoard/anonymousBoardList.jsp")
			.forward(request, response);
	}

}