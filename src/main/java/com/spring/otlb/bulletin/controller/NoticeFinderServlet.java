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

/**
 * Servlet implementation class NoticeFinderServlet
 */
@WebServlet("/board/noticeFinder")
public class NoticeFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinService bulletinService = new BulletinService();

	/**
	 * select * from notice where title like ?
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param@servlet = " + param);
		
		List<Board> list = bulletinService.searchNotice(param);
		List<String> regDate = new ArrayList<>();
		System.out.println("list : " + list);
		
		for(Board board : list) {
			regDate.add(DateFormatUtils.formatDateBoard(board.getRegDate()));
		}

		request.setAttribute("list", list);
		request.setAttribute("regDate", regDate);
		request
			.getRequestDispatcher("/WEB-INF/views/notice/noticeList.jsp")
			.forward(request, response);
	}

}
